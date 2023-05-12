package Commands;

import CollectionData.LabWork;
import Program.Command;
import Program.Lab5;

import java.io.IOException;

/**
 * Класс для обновления значения элемента коллекции, id которого равен заданному
 * @author Arina Nikitochkina
 */

public class Update extends Command {
    public Update() {
        this.description = "обновить значение элемента коллекции, id которого равен заданному";
        this.argument = true;
    }

    /**
     * Метод для выполнения команды Update
     */

    @Override
    public void execute(Object arguments) {
        int id = 0;
        try{
            id = Integer.parseInt(((String) arguments));
            try {
                Lab5.client.send("update " + arguments + "%%%" + gson.toJson(LabWork.create()));
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
