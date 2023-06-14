package Commands;

import CollectionData.LabWork;
import Program.Command;
import Program.Lab5;

import java.io.IOException;

/**
 * Класс для вывода справки по доступным командам
 * @author Arina Nikitochkina
 */

public class Add extends Command {
    public Add() {
        this.description = "добавить новый элемент в коллекцию";
    }

    /**
     * Метод для выполнения команды Add
     */

    @Override
    public void execute() {
        LabWork labWork = LabWork.create();
        String serialized = gson.toJson(labWork);
        try {
            Lab5.client.send(Lab5.username + "::" + Lab5.password + "::add " + serialized);
        } catch (IOException ignored) {}
    }
}
