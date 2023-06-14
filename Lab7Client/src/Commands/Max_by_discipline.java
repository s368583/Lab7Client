package Commands;

import CollectionData.LabWork;
import Program.Command;
import Program.Lab5;

import java.io.IOException;
import java.util.Collections;

/**
 * Класс для вывода любого объекта из коллекции, значение поля discipline которого является максимальным
 * @author Arina Nikitochkina
 */

public class Max_by_discipline extends Command {

    public Max_by_discipline() throws IOException {
        this.description = "вывести любой объект из коллекции, значение поля discipline которого является максимальным";
    }

    /**
     * Метод для выполнения команды Max_by_discipline
     */

    @Override
    public void execute() {
        try {
            Lab5.client.send(Lab5.username + "::" + Lab5.password + "::max_by_discipline");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}