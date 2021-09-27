package se1_projekt.controller;

import se1_projekt.commands.ICommand;
import se1_projekt.commands.Undoable;
import se1_projekt.model.CommandHistory;
import java.util.HashMap;


public class Invoker {
    private final HashMap<String, ICommand> commandMap = new HashMap<>();

    public void register(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }

    public void execute(String commandName){
        ICommand command = commandMap.get(commandName);
        if(command == null){
            System.out.println("Unknown command!");
            System.out.println("For list of commands, type 'help'");
        }else {
            command.execute();
            if(command instanceof Undoable) CommandHistory.getInstance().push(command);
        }
    }

    public void undo(){
        if(CommandHistory.getInstance().isEmpty()) System.out.println("Nothing to undo!");
        else{
            Undoable command = (Undoable) CommandHistory.getInstance().pop();
            command.undo();
        }
    }
}
