package Commands;

import Program.Command;
import Program.Lab5;

import java.io.IOException;

/**
 * Класс для удаления первого элемента из коллекции
 * @author Arina Nikitochkina
 */

public class Remove_first extends Command {
    public Remove_first() {
        this.description = "удалить первый элемент из коллекции";
    }

    /**
     * Метод для выполнения команды Remove_first
     */

    @Override
    public void execute() {
        try {
            Lab5.client.send("remove_first");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}