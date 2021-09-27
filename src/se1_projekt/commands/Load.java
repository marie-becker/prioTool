package se1_projekt.commands;

import se1_projekt.entities.UserStory;
import se1_projekt.model.Container;

import java.util.ArrayList;
import java.util.List;

public class Load implements ICommand, Undoable {

    List<UserStory> oldStories = new ArrayList<>();
    List<String> oldActors = new ArrayList<>();

    public void execute(){
        oldStories = Container.getInstance().getCurrentList();
        oldActors = Container.getInstance().getActors();
        Container.getInstance().load();
    }

    public void undo() {
        Container.getInstance().replaceUS(oldStories);
        Container.getInstance().replaceAct(oldActors);
        System.out.println("Zuvor aktueller Zustand wiederhergestellt.");
    }
}
