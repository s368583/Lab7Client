package Commands;

import Program.Command;

/**
 * Класс для завершения программы (без сохранения в файл)
 * @author Arina Nikitochkina
 */

public class Exit extends Command {
    public Exit() {
        this.description = "завершить программу (без сохранения в файл)";
    }

    /**
     * Метод для выполнения команды Exit
     */

    @Override
    public void execute() {

        System.exit(0);
    }
}
