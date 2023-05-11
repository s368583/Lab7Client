package Commands;

import CollectionData.LabWork;
import Program.Command;
import Program.Lab5;

import java.io.IOException;
import java.util.Iterator;

/**
 * Класс для удаления из коллекции всех элементов, превышающих заданный
 * @author Arina Nikitochkina
 */

public class Remove_greater extends Command {

    public Remove_greater() throws IOException {
        this.description = "удалить из коллекции все элементы, превышающие заданный";
    }

    /**
     * Метод для выполнения команды Remove_greater
     */

    @Override
    public void execute() {
        try {
            Lab5.client.send("remove_greater " + gson.toJson(LabWork.create()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}