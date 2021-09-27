package se1_projekt.model;

import se1_projekt.commands.ICommand;
import java.util.Stack;

public class CommandHistory {
    Stack<ICommand> hist;
    private static CommandHistory commandHistory = null;

    private CommandHistory(){
        hist = new Stack<>();
    }

    public static CommandHistory getInstance(){
        if(commandHistory == null){
            commandHistory = new CommandHistory();
        }
        return commandHistory;
    }

    public void push(ICommand com){
        hist.push(com);
    }

    public ICommand pop(){
        return hist.pop();
    }

    public boolean isEmpty(){
        return hist.isEmpty();
    }
}
