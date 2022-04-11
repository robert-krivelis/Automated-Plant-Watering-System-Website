package edu.ucalgary.ensf409;

public class Food {
    // contains information about a food item
    private String name;
    // percent of each catagory
    private int grainContent;
    private int fvContent;
    private int proContent;
    private int other;
    // calories of item
    private int calories;

    // constructor
    public Food(String iniName, int iniGrainContent, int iniFvContent, int iniProContent, int iniOther,
            int iniCalories) {
        this.name = iniName;
        this.grainContent = iniGrainContent;
        this.fvContent = iniFvContent;
        this.proContent = iniProContent;
        this.other = iniOther;
        this.calories = iniCalories;
    }

    // format food data
    public String formatFoodData() {
        return this.name + "\n(whole grains: " + this.getWholeGrains() + ", fruit veggies: " + this.getFruitVeggies()
                + ", protein: " + this.getProtein() + ", other: " + this.getOther() + ", calories: "
                + this.getCalories() + ")";
    }

    // getters for food calories
    public String getName() {
        return name;
    }

    public int getWholeGrains() {
        return (int) (((double) grainContent / 100) * calories);
    }

    public int getFruitVeggies() {
        return (int) (((double) fvContent / 100) * calories);
    }

    public int getProtein() {
        return (int) (((double) proContent / 100) * calories);
    }

    public int getOther() {
        return (int) (((double) other / 100) * calories);
    }

    public int getCalories() {
        return calories;
    }

    // getters for food calories percent
    public int getWholeGrainsPer() {
        return grainContent;
    }

    public int getFruitVeggiesPer() {
        return fvContent;
    }

    public int getProteinPer() {
        return proContent;
    }

    public int getOtherPer() {
        return other;
    }
}
