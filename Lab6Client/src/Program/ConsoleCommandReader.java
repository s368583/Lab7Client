package Program;

import java.io.IOException;
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
            System.out.println("\nДля просмотра доступных команд введите help");
            System.out.println("Введите команду:");

            String consoleLine = consoleScanner.nextLine();

            if(consoleLine.isEmpty()) {
                continue;
            }
            else {
                if(consoleLine.contains(" ")) {
                    String[] consoleIn = consoleLine.split(" ");
                    String command = consoleIn[0].substring(0, 1).toUpperCase() + consoleIn[0].substring(1);
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
                                    System.out.println(Lab5.client.receive());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    break;
                                }
                            }
                        }

                    } catch (ClassNotFoundException e) {
                        System.out.println("\nКоманда не найдена");
                        continue;
                    }
                }
                else {
                    String command = consoleLine.substring(0, 1).toUpperCase() + consoleLine.substring(1);

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
                                System.out.println(Lab5.client.receive());
                            } catch (IOException e) {
                                e.printStackTrace();
                                break;
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
