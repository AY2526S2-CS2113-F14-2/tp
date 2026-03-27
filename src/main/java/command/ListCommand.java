package command;

import java.util.logging.Logger;
import java.util.logging.Level;
import model.FoodList;
import ui.UserInterface;
import seedu.bitbites.BitbitesResponses;

public class ListCommand extends Command {
	private static final Logger logger = Logger.getLogger(ListCommand.class.getName());

	@Override
	public boolean execute(FoodList foodList, UserInterface ui) {
		System.out.println(BitbitesResponses.listMessage);
		for (int i = 0; i < foodList.size(); i++) {
			System.out.println((i + 1) + ". " + foodList.get(i));
		}
		return false;
	}
}