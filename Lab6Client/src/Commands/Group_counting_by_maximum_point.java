package Commands;

import CollectionData.LabWork;
import Program.Command;
import Program.Lab5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Класс для группировки элементов коллекции по значению поля maximumPoint и вывода количества элементов в каждой группе
 * @author Arina Nikitochkina
 */

public class Group_counting_by_maximum_point extends Command {
    public Group_counting_by_maximum_point() {
        this.description = "сгруппировать элементы коллекции по значению поля maximumPoint, вывести количество элементов в каждой группе";
    }

    /**
     * Метод для выполнения команды Group_counting_by_maximum_point
     */

    @Override
    public void execute() {
        try {
            Lab5.client.send("group_counting_by_maximum_point");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
