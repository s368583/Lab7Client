package Commands;

import CollectionData.Discipline;
import Program.Command;
import Program.Lab5;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс для вывода количества элементов, значение поля discipline которых меньше заданного
 * @author Arina Nikitochkina
 */

public class Count_less_than_discipline extends Command {
    public Count_less_than_discipline() {
        this.description = "вывести количество элементов, значение поля discipline которых меньше заданного";
    }

    /**
     * Метод для выполнения команды Count_less_than_discipline
     */
    @Override
    public void execute() {
        try {
            Lab5.client.send(Lab5.username + "::" + Lab5.password + "::count_less_than_discipline " + gson.toJson(Discipline.create()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
