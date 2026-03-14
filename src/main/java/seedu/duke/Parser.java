/**
 * Parser.java
 * 
 * This file contains the Parser class responsible for interpreting user commands.
 * Routes user input to appropriate command handlers (list, add, exit).
 * 
 * Dependencies:
 * - FoodList: For accessing and modifying the food items list
 * - UserInterface: For providing feedback to users
 * - BitbitesException: For error handling
 * - BitbitesResponses: For error messages
 */
package seedu.duke;

/**
 * Parser interprets user commands and executes the appropriate handlers.
 * Supports commands: list, list d/<date>, add, and exit.
 * Throws BitbitesException for unknown commands.
 */
public class Parser {

    public Parser() {
        // No implementation needed for the constructor as of now
    }

    /**
     * Parses the user input and executes the corresponding command.
     * Returns true if the command is "exit", false otherwise.
     */
    public static boolean parse(String fullCommand, FoodList foodList, UserInterface ui) {
        fullCommand = fullCommand.trim();

        if (fullCommand.startsWith("list d/")) {
            handleListFromDate(fullCommand, foodList, ui);
        } else if (fullCommand.equals("list")) {
            handleListAll(fullCommand, foodList, ui);
        } else if (fullCommand.startsWith("add ")) {
            handleAdd(fullCommand, foodList, ui);
        } else if (fullCommand.equals("exit")) {
            ui.showExit();
            return true;
        } else {
            throw new BitbitesException(BitbitesResponses.unknownCommand);
        }

        return false;
    }

    // TODO: List all food items
    private static void handleListAll(String fullCommand, FoodList foodList, UserInterface ui) {
        // Implementation for listing all food items
    }

    // TODO: List food items for a specific date
    private static void handleListFromDate(String fullCommand, FoodList foodList, UserInterface ui) {
        // Implementation for listing food items
    }

    private static void handleAdd(String fullCommand, FoodList foodList, UserInterface ui) {
        // fullCommand looks like: "add n/Chicken Rice c/500 p/30.5 d/2026-03-14"
        // Check that all required prefixes exist
        String correctFormat = BitbitesResponses.addFormatReminder;
        if (!fullCommand.contains("n/") || !fullCommand.contains("c/") ||
                !fullCommand.contains("p/") || !fullCommand.contains("d/")) {
            System.out.println(correctFormat);
            return;
        }

        // Extract each field by finding the prefix and grabbing text until the next prefix
        String name = fullCommand.substring(
                fullCommand.indexOf("n/") + 2,
                fullCommand.indexOf("c/")
        ).trim();

        String caloriesStr = fullCommand.substring(
                fullCommand.indexOf("c/") + 2,
                fullCommand.indexOf("p/")
        ).trim();

        String proteinStr = fullCommand.substring(
                fullCommand.indexOf("p/") + 2,
                fullCommand.indexOf("d/")
        ).trim();

        String date = fullCommand.substring(
                fullCommand.indexOf("d/") + 2
        ).trim();

        // Convert calories and protein from String to their correct types
        int calories = Integer.parseInt(caloriesStr);
        double protein = Double.parseDouble(proteinStr);

        // Create the Food object and add it to the list
        Food newFood = new Food(name, calories, protein, date);
        foodList.addFood(newFood);

        System.out.println(BitbitesResponses.addMessage);
    }
}
