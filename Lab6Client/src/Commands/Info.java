package Commands;

import Program.Command;
import Program.Lab5;

import java.io.IOException;

/**
 * Класс для вывода информации о коллекции
 * @author Arina Nikitochkina
 */

public class Info extends Command {
    public Info() {
        this.description = "вывести информацию о коллекции";
    }

    /**
     * Метод для выполнения команды Info
     */

    @Override
    public void execute() {
        try {
            Lab5.client.send("info");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
