package Commands;

import Program.Command;
import Program.Lab5;

import java.io.IOException;

/**
 * Класс для вывода первого элемента коллекции
 * @author Arina Nikitochkina
 */

public class Head extends Command {
    public Head() {
        this.description = "вывести первый элемент коллекции";
    }

    /**
     * Метод для выполнения команды Head
     */

    @Override
    public void execute() {
        try {
            Lab5.client.send("head");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
