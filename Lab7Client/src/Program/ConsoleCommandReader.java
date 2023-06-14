package Program;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.nio.channels.ClosedChannelException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс для чтения команд из консоли
 * @author Arina Nikitochkina
 */
public class ConsoleCommandReader {
    public ConsoleCommandReader() {}

    /**
     * Метод для чтения команд из консоли
     * @throws Exception
     */
    public void start() {
        Lab5.script = false;
        Scanner consoleScanner = new Scanner(System.in);

        while(true) {
            if(!Lab5.loggedIn) {
                System.out.println("Зарегистрируйтесь или войдите в систему. Введите register или login");
            }
            else {
                System.out.println("\nДля просмотра доступных команд введите help");
                System.out.println("Введите команду:");
            }

            String consoleLine = consoleScanner.nextLine().toLowerCase();
            if((!consoleLine.equals("register") && !consoleLine.equals("login")) && !Lab5.loggedIn) {
                System.out.println("Зарегистрируйтесь или войдите в систему. Введите register или login");
            }

            if(consoleLine.isEmpty()) {
                continue;
            }
            else {
                if(consoleLine.contains(" ")) {
                    String[] consoleIn = consoleLine.split(" ");
                    String command = consoleIn[0].substring(0, 1).toUpperCase() + consoleIn[0].substring(1).toLowerCase();
                    String argument = null;
                    try{ argument = consoleIn[1];}
                    catch (Exception e) {
                        System.out.println("Вы не ввели аргумент");
                        continue;

                    }

                    Class<?> commandClass = null;
                    try {
                        commandClass = Class.forName("Commands." + command);
                        Object commandClassObject = null;
                        try {
                            commandClassObject = commandClass.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        if (((Command) Objects.requireNonNull(commandClassObject)).isArgument()){
                            ((Command) Objects.requireNonNull(commandClassObject)).execute(argument);
                            if(!command.equals("Execute_script") && !command.equals("Help")) {
                                try {
                                    try {
                                        String response = Lab5.client.receive();
                                        if(response.equals("Выполнен вход в систему") || response.equals("Вы успешно зарегистрированы. Выполнен вход в систему")) {
                                            Lab5.loggedIn = true;
                                        } else if(response.equals("Пользователь с таким именем уже существует") || response.equals("Неверный пароль") || response.equals("Пользователя с таким именем не существует. Повторите попытку")) {
                                            Lab5.loggedIn = false;
                                            Lab5.username = "";
                                            Lab5.password = "";
                                        }
                                        System.out.println(response);
                                    } catch (PortUnreachableException | ClosedChannelException e) {
                                        System.out.println("\nСервер недоступен");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    } catch (ClassNotFoundException e) {
                        System.out.println("\nКоманда не найдена");
                        continue;
                    }
                }
                else {
                    String command = consoleLine.substring(0, 1).toUpperCase() + consoleLine.substring(1).toLowerCase();

                    Class<?> commandClass = null;
                    try {
                        commandClass = Class.forName("Commands." + command);
                        Object commandClassObject = null;
                        try {
                            commandClassObject = commandClass.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        if (!((Command) Objects.requireNonNull(commandClassObject)).isArgument()){
                            ((Command) Objects.requireNonNull(commandClassObject)).execute();
                            if(!command.equals("Execute_script") && !command.equals("Help")) {
                                try {
                                    try {
                                        String response = Lab5.client.receive();
                                        if(response.equals("Выполнен вход в систему") || response.equals("Вы успешно зарегистрированы. Выполнен вход в систему")) {
                                            Lab5.loggedIn = true;
                                        } else if(response.equals("Пользователь с таким именем уже существует") || response.equals("Неверный пароль") || response.equals("Пользователя с таким именем не существует. Повторите попытку")) {
                                            Lab5.loggedIn = false;
                                            Lab5.username = "";
                                            Lab5.password = "";
                                        }
                                        System.out.println(response);
                                    } catch (PortUnreachableException | ClosedChannelException e) {
                                        System.out.println("\nСервер недоступен");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else{
                            System.out.println("Вы не ввели аргумент");
                        }
                    } catch (ClassNotFoundException e) {
                        System.out.println("\nКоманда не найдена");
                        continue;
                    }
                }
            }
        }
    }
}
