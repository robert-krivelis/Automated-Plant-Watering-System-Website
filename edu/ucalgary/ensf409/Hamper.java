package edu.ucalgary.ensf409;

import java.util.*;

public class Hamper {
    // food in hamper
    private ArrayList<Food> hamperFood = new ArrayList<Food>();
    // calories in hamper
    private int totWholeGrainsInHamper = 0;
    private int totFruitVeggiesInHamper = 0;
    private int totProteinInHamper = 0;
    private int totOtherInHamper = 0;
    private int totCaloriesInHamper = 0;

    public Hamper() {
    }

    public Hamper(ArrayList<Food> hamperFood) { // for cloning the hamper
        for (Food food: hamperFood){
            this.addFood(food);
        }
        
    }

    // @Override
    // public Object clone() throws CloneNotSupportedException {
    // // This is a deep clone used for FoodSelectionAlgorithm- though i think i
    // didn't have to do it this way, going to remove later.

    // // Assign the shallow copy to new reference variable t
    // Hamper temp = (Hamper) super.clone();

    // // ArrayList<RobotDataLine> list = new ArrayList<>();
    // ArrayList<Food> hamperFoodClone = new ArrayList<>(this.hamperFood.size());
    // Iterator<Food> iterator = this.hamperFood.iterator();

    // while (iterator.hasNext()) {
    // // Add the object clones
    // hamperFoodClone.add((Food)iterator.next().clone());
    // }
    // temp.hamperFood = hamperFoodClone;
    // return temp;
    // }

    // adds food and updates calorie information
    public void addFood(Food addedFood) {
        hamperFood.add(addedFood);
        totWholeGrainsInHamper += addedFood.getWholeGrains();
        totFruitVeggiesInHamper += addedFood.getFruitVeggies();
        totProteinInHamper += addedFood.getProtein();
        totOtherInHamper += addedFood.getOther();
        totCaloriesInHamper += addedFood.getCalories();
    }

    public void removeFood(Food removedFood) {
        hamperFood.remove(removedFood);
        totWholeGrainsInHamper -= removedFood.getWholeGrains();
        totFruitVeggiesInHamper -= removedFood.getFruitVeggies();
        totProteinInHamper -= removedFood.getProtein();
        totOtherInHamper -= removedFood.getOther();
        totCaloriesInHamper -= removedFood.getCalories();

    }

    // returns a list of food items in the hamper
    public String formattedFoodInHamper() {
        String temp = "";
        for (int i = 0; i < hamperFood.size(); i++) {
            temp += hamperFood.get(i).formatFoodData() + "\n";
        }
        return temp;
    }

    // returns information about calories in each category in hamper
    public String formattedCaloriesInHamper() {
        return "Calories by Category in Hamper:\nwhole grains: " + this.getTotWholeGrainsInHamper()
                + ", fruit veggies: " + this.getTotFruitVeggiesInHamper()
                + ", protein: " + this.getTotProteinInHamper() + ", other: " + this.getTotOtherInHamper()
                + ", calories: " + this.getTotCaloriesInHamper() + "\n";
    }

    public ArrayList<Food> getHamperFood() {
        return hamperFood;
    }

    public int getTotCaloriesInHamper() {
        return totCaloriesInHamper;
    }

    public int getTotOtherInHamper() {
        return totOtherInHamper;
    }

    public int getTotProteinInHamper() {
        return totProteinInHamper;
    }

    public int getTotFruitVeggiesInHamper() {
        return totFruitVeggiesInHamper;
    }

    public int getTotWholeGrainsInHamper() {
        return totWholeGrainsInHamper;
    }
}
