package seedu.duke;

public class FoodList {
    private java.util.ArrayList<Food> foodList;

    public FoodList() {
        this.foodList = new java.util.ArrayList<Food>();
    }

    public void addFood(Food food) {
        this.foodList.add(food);
    }

    public java.util.ArrayList<Food> getFoodList() {
        return this.foodList;
    }
}
