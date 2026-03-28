package command;

import model.FoodList;
import ui.UserInterface;

public abstract class Command {
    public abstract boolean execute(FoodList foodList, UserInterface ui);
}
