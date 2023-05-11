package Commands;

import CollectionData.LabWork;
import Program.Command;
import Program.Lab5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс для сортировки коллекции в порядке, обратном нынешнему
 * @author Arina Nikitochkina
 */

public class Reorder extends Command {
    public Reorder() {
        this.description = "отсортировать коллекцию в порядке, обратном нынешнему";
    }

    /**
     * Метод для выполнения команды Reorder
     */

    @Override
    public void execute() {
        try {
            Lab5.client.send("reorder");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
