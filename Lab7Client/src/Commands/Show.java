package Commands;

import Program.Command;
import Program.Lab5;

import java.io.IOException;

/**
 * Класс для вывода всех элементов коллекции в строковом представлении
 * @author Arina Nikitochkina
 */

public class Show extends Command {
    public Show() {
        this.description = "вывести все элементы коллекции в строковом представлении";
    }

    /**
     * Метод для выполнения команды Show
     */

    @Override
    public void execute() {
        try {
            Lab5.client.send(Lab5.username + "::" + Lab5.password + "::show");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
