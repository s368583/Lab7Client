package Commands;

import Program.Command;
import Program.Lab5;

import java.io.IOException;

/**
 * Класс для очистки коллекции
 * @author Arina Nikitochkina
 */
public class Clear extends Command {
    public Clear() {
        this.description = "очистить коллекцию";
    }

    /**
     * Метод для выполнения команды Clear
     */
    @Override
    public void execute() {
        try {
            Lab5.client.send("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
