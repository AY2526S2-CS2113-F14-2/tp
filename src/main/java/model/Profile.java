package model;

/**
 * Profile.java
 *
 * Represents a user's personal profile containing physical attributes.
 * Supports computation of BMI (Body Mass Index) and BMR (Basal Metabolic Rate)
 * based on the stored values, providing health context for food tracking.
 *
 * BMR is calculated using the Mifflin-St Jeor formula.
 */
// @@author bryanyeo3125
public class Profile {
    private String name;
    private int age;
    private double weight;
    private double height;

    /**
     * Constructs a Profile with the given personal attributes.
     *
     * @param name   The user's name.
     * @param age    The user's age in years.
     * @param weight The user's weight in kilograms.
     * @param height The user's height in centimetres.
     */
    public Profile(String name, int age, double weight, double height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    /**
     * Returns the user's name.
     *
     * @return The name string.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name The new name string.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's age.
     *
     * @return The age in years.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the user's age.
     *
     * @param age The new age in years.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the user's weight.
     *
     * @return The weight in kilograms.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the user's weight.
     *
     * @param weight The new weight in kilograms.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns the user's height.
     *
     * @return The height in centimetres.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the user's height.
     *
     * @param height The new height in centimetres.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Calculates and returns the user's Body Mass Index (BMI).
     * BMI = weight (kg) / height (m)^2
     *
     * @return The BMI as a double.
     */
    public double getBmi() {
        double heightInMetres = height / 100.0;
        return weight / (heightInMetres * heightInMetres);
    }

    /**
     * Returns a string category based on the user's BMI value.
     * Categories: Underweight (< 18.5), Normal (18.5-24.9),
     * Overweight (25-29.9), Obese (>= 30).
     *
     * @return The BMI category as a string.
     */
    public String getBmiCategory() {
        double bmi = getBmi();
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25.0) {
            return "Normal";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    /**
     * Calculates and returns the user's Basal Metabolic Rate (BMR)
     * using the Mifflin-St Jeor formula (male).
     * BMR = (10 * weight) + (6.25 * height) - (5 * age) + 5
     *
     * @return The BMR in kcal/day as an integer.
     */
    public int getBmr() {
        return (int) ((10 * weight) + (6.25 * height) - (5 * age) + 5);
    }

    /**
     * Returns a string representation of the profile.
     *
     * @return A formatted string showing name, age, weight and height.
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Age: %d | Weight: %.1fkg | Height: %.1fcm",
                name, age, weight, height);
    }
}
// @@author
