package Commands;

import CollectionData.LabWork;
import Program.Command;
import Program.Lab5;

import java.io.IOException;


/**
 * Класс для удаления элемента из коллекции по его id
 * @author Arina Nikitochkina
 */

public class Remove_by_id extends Command {
    public Remove_by_id() {
        this.description = "удалить элемент из коллекции по его id";
        this.argument = true;
    }

    /**
     * Метод для выполнения команды Remove_by_id
     */

    @Override
    public void execute(Object arguments) {
        int id = 0;
        try{
            id = Integer.parseInt((String) arguments);
            try {
                Lab5.client.send("remove_by_id " + arguments);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(Exception e) {
            System.out.println("\nДанные введены неверно\n");
            /*try {
                Lab5.client.send("");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }*/
        }
    }
}
