package Commands;

import Program.*;

import java.io.IOException;
import java.util.Scanner;

public class Login extends Command {

    public Login() throws IOException {
        this.description = "войти в систему";
    }

    @Override
    public void execute() {
        Scanner usernameIn = new Scanner(System.in);
        System.out.println("\nВведите имя пользователя:");
        String username = usernameIn.nextLine();
        while(username.equals("")) {
            System.out.println("\nПоле не может быть пустым");
            System.out.println("Введите имя пользователя:");
            username = usernameIn.nextLine();
        }

        Scanner passwordIn = new Scanner(System.in);
        System.out.println("\nВведите пароль:");
        String password = passwordIn.nextLine();
        while(password.equals("")) {
            System.out.println("\nПоле не может быть пустым");
            System.out.println("Введите пароль:");
            password = passwordIn.nextLine();
        }

        Lab5.username = username;
        Lab5.password = Client.encodePassword(password);
        Lab5.loggedIn = true;
        try {
            Lab5.client.send("login " + Lab5.username + "::" + Lab5.password);
        } catch (IOException ignored) {}
    }
}