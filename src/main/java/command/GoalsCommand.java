package command;

import model.FoodList;
import model.Food;
import model.PresetList;
import ui.UserInterface;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import seedu.bitbites.AppContext;

/**
 * GoalsCommand.java
 *
 * Handles the setting and viewing of daily and weekly nutritional goals.
 * Users can set calorie and protein targets, and view their current progress
 * against those goals. Also provides a static method for other commands
 * to display a quick daily progress summary after adding or deleting food.
 *
 * Supported commands:
 *   goals: View daily and weekly progress
 *   goals set dc/CAL dp/PROT wc/CAL wp/PROT: Set goals
 */
// @@author bryanyeo3125
public class GoalsCommand extends Command {

    private static int dailyCalorieGoal = 2000;
    private static double dailyProteinGoal = 50.0;
    private static int weeklyCalorieGoal = 14000;
    private static double weeklyProteinGoal = 350.0;

    private final String fullCommand;

    /**
     * Constructs a GoalsCommand with the full user input string.
     *
     * @param fullCommand The raw command string entered by the user.
     */
    public GoalsCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the goals command, either displaying progress or updating goal values.
     *
     * @param context The application context containing FoodList and UserInterface.
     * @return false always, as this command does not trigger application exit.
     */
    @Override
    public boolean execute(AppContext context) {
        FoodList foodList = context.getFoodList();
        UserInterface ui = context.getUi();
        PresetList presetList = context.getPresetList();

        if (fullCommand.equals("goals")) {
            showGoalsMenu(foodList);
        } else if (fullCommand.startsWith("goals set")) {
            if (fullCommand.trim().equals("goals set")) {
                System.out.println("Please specify at least one goal to set.");
                System.out.println("Format: goals set dc/CALORIES dp/PROTEIN wc/CALORIES wp/PROTEIN");
                System.out.println("Example: goals set dc/2500 dp/60 wc/17500 wp/420");
            } else if (!hasValidGoalPrefix(fullCommand)) {
                System.out.println("Unknown goal prefix detected.");
                System.out.println("Valid prefixes: dc/ (daily calories), dp/ (daily protein), " +
                        "wc/ (weekly calories), wp/ (weekly protein)");
                System.out.println("Example: goals set dc/2500 dp/60");
            } else {
                handleSetGoals(fullCommand);
            }
        } else {
            System.out.println("Unknown goals command: " + fullCommand);
            System.out.println("Available goals commands:");
            System.out.println("  goals                          - View your progress");
            System.out.println("  goals set dc/CAL dp/PROT       - Set daily goals");
            System.out.println("  goals set wc/CAL wp/PROT       - Set weekly goals");
        }
        return false;
    }

    /**
     * Checks whether the command contains at least one valid goal prefix.
     *
     * @param command The command string to check.
     * @return true if a valid prefix is found, false otherwise.
     */
    private boolean hasValidGoalPrefix(String command) {
        return command.contains("dc/") || command.contains("dp/") ||
                command.contains("wc/") || command.contains("wp/");
    }

    /**
     * Displays the full goals menu showing daily and weekly progress
     * against the user's set targets.
     *
     * @param foodList The current list of food items.
     */
    private void showGoalsMenu(FoodList foodList) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate now = LocalDate.now();
        LocalDate weekStart = now.minusDays(now.getDayOfWeek().getValue() - 1);
        LocalDate weekEnd = weekStart.plusDays(6);

        int dailyCalories = 0;
        double dailyProtein = 0.0;
        int weeklyCalories = 0;
        double weeklyProtein = 0.0;

        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            LocalDate foodDate = LocalDate.parse(food.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            if (food.getDate().equals(today)) {
                dailyCalories += food.getCalories();
                dailyProtein += food.getProtein();
            }

            if (!foodDate.isBefore(weekStart) && !foodDate.isAfter(weekEnd)) {
                weeklyCalories += food.getCalories();
                weeklyProtein += food.getProtein();
            }
        }

        System.out.println("========== YOUR GOALS ==========");
        System.out.println("--- Daily (" + today + ") ---");
        System.out.printf("Calories : %d / %d kcal %s%n",
                dailyCalories, dailyCalorieGoal,
                dailyCalories >= dailyCalorieGoal ? "(Goal reached!)" : "");
        System.out.printf("Protein  : %.1f / %.1fg %s%n",
                dailyProtein, dailyProteinGoal,
                dailyProtein >= dailyProteinGoal ? "(Goal reached!)" : "");

        System.out.println("--- Weekly (" + weekStart + " to " + weekEnd + ") ---");
        System.out.printf("Calories : %d / %d kcal %s%n",
                weeklyCalories, weeklyCalorieGoal,
                weeklyCalories >= weeklyCalorieGoal ? "(Goal reached!)" : "");
        System.out.printf("Protein  : %.1f / %.1fg %s%n",
                weeklyProtein, weeklyProteinGoal,
                weeklyProtein >= weeklyProteinGoal ? "(Goal reached!)" : "");
        System.out.println("================================");
    }

    /**
     * Parses and updates one or more goal values from the command string.
     * Supports setting daily calories (dc/), daily protein (dp/),
     * weekly calories (wc/), and weekly protein (wp/).
     *
     * @param fullCommand The full command string containing goal prefixes and values.
     */
    private void handleSetGoals(String fullCommand) {
        String[] prefixes = {"dc/", "dp/", "wc/", "wp/"};
        try {
            if (fullCommand.contains("dc/")) {
                int val = Integer.parseInt(extractValue(fullCommand, "dc/", nextPrefix(fullCommand,
                        "dc/", prefixes)));
                if (val < 0) {
                    throw new NumberFormatException();
                }
                dailyCalorieGoal = val;
                System.out.println("Daily calorie goal set to " + val + " kcal.");
            }
            if (fullCommand.contains("dp/")) {
                double val = Double.parseDouble(extractValue(fullCommand, "dp/", nextPrefix(fullCommand,
                        "dp/", prefixes)));
                if (val < 0) {
                    throw new NumberFormatException();
                }
                dailyProteinGoal = val;
                System.out.println("Daily protein goal set to " + val + "g.");
            }
            if (fullCommand.contains("wc/")) {
                int val = Integer.parseInt(extractValue(fullCommand, "wc/", nextPrefix(fullCommand,
                        "wc/", prefixes)));
                if (val < 0) {
                    throw new NumberFormatException();
                }
                weeklyCalorieGoal = val;
                System.out.println("Weekly calorie goal set to " + val + " kcal.");
            }
            if (fullCommand.contains("wp/")) {
                double val = Double.parseDouble(extractValue(fullCommand, "wp/", nextPrefix(fullCommand,
                        "wp/", prefixes)));
                if (val < 0) {
                    throw new NumberFormatException();
                }
                weeklyProteinGoal = val;
                System.out.println("Weekly protein goal set to " + val + "g.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid value. Goals must be non-negative numbers.");
        }
    }

    /**
     * Displays a brief summary of today's calorie and protein intake
     * relative to the user's daily goals. Called after adding or deleting
     * a food item to give immediate feedback on goal progress.
     *
     * @param foodList The current list of food items.
     */
    public static void showDailyProgress(FoodList foodList) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        int dailyCalories = 0;
        double dailyProtein = 0.0;

        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            if (food.getDate().equals(today)) {
                dailyCalories += food.getCalories();
                dailyProtein += food.getProtein();
            }
        }

        int calRemaining = dailyCalorieGoal - dailyCalories;
        double proteinRemaining = dailyProteinGoal - dailyProtein;

        System.out.println("--- Today's Progress ---");
        if (calRemaining > 0) {
            System.out.printf("Calories : %d / %d kcal (%d more to goal)%n",
                    dailyCalories, dailyCalorieGoal, calRemaining);
        } else {
            System.out.printf("Calories : %d / %d kcal (Goal reached!)%n",
                    dailyCalories, dailyCalorieGoal);
        }
        if (proteinRemaining > 0) {
            System.out.printf("Protein  : %.1f / %.1fg (%.1fg more to goal)%n",
                    dailyProtein, dailyProteinGoal, proteinRemaining);
        } else {
            System.out.printf("Protein  : %.1f / %.1fg (Goal reached!)%n",
                    dailyProtein, dailyProteinGoal);
        }
    }
}
// @@author
