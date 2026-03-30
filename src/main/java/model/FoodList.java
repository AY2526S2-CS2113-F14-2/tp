/**
 * FoodList.java
 * 
 * This file defines the FoodList container class that manages a collection of Food items.
 * Provides methods to add and retrieve food items.
 * 
 * Dependencies:
 * - Food: Data model for individual food items
 * - java.util.ArrayList: For storing food items
 */
package model;


import java.util.ArrayList;
import java.util.List;
import seedu.bitbites.BitbitesException;
import seedu.bitbites.BitbitesResponses;

/**
 * FoodList manages a collection of Food objects.
 * Provides functionality to add, retrieve, and manage food items in the application.
 */
public class FoodList {
    private java.util.ArrayList<Food> foodList;

    public FoodList() {
        this.foodList = new java.util.ArrayList<Food>();
    }

    public void addFood(Food food) {
        this.foodList.add(food);
    }

    public Food deleteFood(int index) {
        if (index < 0 || index >= foodList.size()) {
            throw new BitbitesException(BitbitesResponses.deleteErrorMessage);
        }
        return foodList.remove(index);
    }

    public java.util.ArrayList<Food> getFoodList() {
        return this.foodList;
    }

    public Food getFood(int index) {
        if (index < 0 || index >= foodList.size()) {
            throw new BitbitesException(BitbitesResponses.deleteErrorMessage);
        }
        return foodList.get(index);
    }

    public int size() {
        return foodList.size();
    }

    public Food get(int i) {
        return foodList.get(i);
    }


    // ── Summary ───────────────────────────────────────────
    public List<Food> getItemsByDate(String date) {
        assert date != null && !date.isEmpty() : "Date should not be null or empty";
        List<Food> result = new ArrayList<>();
        for (Food food : foodList) {
            if (food.getDate().equals(date)) {
                result.add(food);
            }
        }
        return result;
    }

    public List<Food> getItemsInRange(String fromDate, String toDate) {
        assert fromDate != null && !fromDate.isEmpty() : "From date should not be null or empty";
        assert toDate != null && !toDate.isEmpty() : "To date should not be null or empty";
        List<Food> result = new ArrayList<>();
        for (Food food : foodList) {
            String date = food.getDate();
            if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                result.add(food);
            }
        }
        return result;
    }

    public int getTotalCalories() {
        int total = 0;
        for (Food food : foodList) {
            total += food.getCalories();
        }
        assert total >= 0 : "Total calories should not be negative";
        return total;
    }

    public double getTotalProtein() {
        double total = 0;
        for (Food food : foodList) {
            total += food.getProtein();
        }
        assert total >= 0 : "Total protein should not be negative";
        return total;
    }

    public int getTotalCaloriesByDate(String date) {
        assert date != null && !date.isEmpty() : "Date should not be null or empty";
        int total = 0;
        for (Food food : foodList) {
            if (food.getDate().equals(date)) {
                total += food.getCalories();
            }
        }
        assert total >= 0 : "Total calories should not be negative";
        return total;
    }

    public double getTotalProteinByDate(String date) {
        assert date != null && !date.isEmpty() : "Date should not be null or empty";
        double total = 0;
        for (Food food : foodList) {
            if (food.getDate().equals(date)) {
                total += food.getProtein();
            }
        }
        assert total >= 0 : "Total protein should not be negative";
        return total;
    }

    public int getItemCountByDate(String date) {
        assert date != null && !date.isEmpty() : "Date should not be null or empty";
        int count = 0;
        for (Food food : foodList) {
            if (food.getDate().equals(date)) {
                count++;
            }
        }
        return count;
    }

    public List<String> getUniqueDates() {
        List<String> dates = new ArrayList<>();
        for (Food food : foodList) {
            if (!dates.contains(food.getDate())) {
                dates.add(food.getDate());
            }
        }
        java.util.Collections.sort(dates);
        return dates;
    }

    public NutritionSummary getSummaryByDate(String date) {
        assert date != null && !date.isEmpty() : "Date should not be null or empty";
        List<Food> items = getItemsByDate(date);
        int totalCalories = getTotalCaloriesByDate(date);
        double totalProtein = getTotalProteinByDate(date);
        return new NutritionSummary(date, totalCalories, totalProtein, items.size(), items);
    }

    public List<NutritionSummary> getAllDailySummaries() {
        List<NutritionSummary> summaries = new ArrayList<>();
        for (String date : getUniqueDates()) {
            summaries.add(getSummaryByDate(date));
        }
        return summaries;
    }

    public List<NutritionSummary> getSummariesInRange(String fromDate, String toDate) {
        assert fromDate != null && !fromDate.isEmpty() : "From date should not be null or empty";
        assert toDate != null && !toDate.isEmpty() : "To date should not be null or empty";
        assert fromDate.compareTo(toDate) <= 0 : "From date should not be after to date";
        List<NutritionSummary> summaries = new ArrayList<>();
        for (String date : getUniqueDates()) {
            if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                summaries.add(getSummaryByDate(date));
            }
        }
        return summaries;
    }

    public List<NutritionSummary> getTopDaysByCalories(int n) {
        List<NutritionSummary> summaries = new ArrayList<>(getAllDailySummaries());
        summaries.sort((a, b) -> b.getTotalCalories() - a.getTotalCalories());
        return summaries.subList(0, Math.min(n, summaries.size()));
    }

    public List<NutritionSummary> getBestDaysByCalories(int n) {
        List<NutritionSummary> summaries = new ArrayList<>(getAllDailySummaries());
        summaries.sort((a, b) -> a.getTotalCalories() - b.getTotalCalories());
        return summaries.subList(0, Math.min(n, summaries.size()));
    }

    public int getLongestStreak() {
        List<String> dates = getUniqueDates();
        if (dates.isEmpty()) {
            return 0;
        }
        int longestStreak = 1;
        int currentStreak = 1;
        for (int i = 1; i < dates.size(); i++) {
            if (isConsecutive(dates.get(i - 1), dates.get(i))) {
                currentStreak++;
                longestStreak = Math.max(longestStreak, currentStreak);
            } else {
                currentStreak = 1;
            }
        }
        assert longestStreak >= 1 : "Streak should be at least 1 if dates exist";
        return longestStreak;
    }

    public int getCurrentStreak() {
        List<String> dates = getUniqueDates();
        if (dates.isEmpty()) {
            return 0;
        }
        int currentStreak = 1;
        for (int i = dates.size() - 1; i > 0; i--) {
            if (isConsecutive(dates.get(i - 1), dates.get(i))) {
                currentStreak++;
            } else {
                break;
            }
        }
        return currentStreak;
    }

    private boolean isConsecutive(String date1, String date2) {
        assert date1 != null && date2 != null : "Dates should not be null";
        try {
            java.time.LocalDate d1 = java.time.LocalDate.parse(date1);
            java.time.LocalDate d2 = java.time.LocalDate.parse(date2);
            return d1.plusDays(1).equals(d2);
        } catch (Exception e) {
            return false;
        }
    }

}
