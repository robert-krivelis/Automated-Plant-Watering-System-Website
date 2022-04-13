package edu.ucalgary.ensf409;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HamperCreator {
    // number of each client type in the order
    private int adultMales;
    private int adultFemales;
    private int childOverEight;
    private int childUnderEight;
    // database access
    private FoodInventoryDatabaseAccess foodInventoryAccess = new FoodInventoryDatabaseAccess();
    // days required for hamper to provode for
    private int numOfDays = 7;
    // calorie requirements per category for the client group
    private int totWholeGrains = 0;
    private int totFruitVeggies = 0;
    private int totProtein = 0;
    private int totOther = 0;
    private int totCalories = 0;
    // client type information (index 0 for adult male, 1 for adult female, 2 for
    // child
    // over 8, 3 for child under 8)
    private ArrayList<ClientTypes> clientRequirementsByType = new ArrayList<ClientTypes>();
    // all food from the database
    public static ArrayList<Food> availableFood = new ArrayList<Food>();
    // hamper
    private Hamper hamper = new Hamper();
    private int minExcessCalories;
    private Integer[] minExcessFoodIndexes;
    private ArrayList<Integer[]> possibleFoodCombinations;
    private Integer[] availableFoodIndexes;

    public HamperCreator(int adultMales, int adultFemales, int childOverEight, int childUnderEight) 
            throws InsufficientCaloriesForHamperException {
        this.adultMales = adultMales;
        this.adultFemales = adultFemales;
        this.childOverEight = childOverEight;
        this.childUnderEight = childUnderEight;

        foodInventoryAccess.initializeConnection();
        foodInventoryAccess.selectAllClientTypes();
        clientRequirementsByType = foodInventoryAccess.getClientRequirementsByType();
        foodInventoryAccess.close();

        // needed whole grains
        totWholeGrains += clientRequirementsByType.get(0).getWholeGrains() * adultMales
                + clientRequirementsByType.get(1).getWholeGrains() * adultFemales
                + clientRequirementsByType.get(2).getWholeGrains() * childOverEight
                + clientRequirementsByType.get(3).getWholeGrains() * childUnderEight;
        // needed fruit veggies
        totFruitVeggies += clientRequirementsByType.get(0).getFruitVeggies() * adultMales
                + clientRequirementsByType.get(1).getFruitVeggies() * adultFemales
                + clientRequirementsByType.get(2).getFruitVeggies() * childOverEight
                + clientRequirementsByType.get(3).getFruitVeggies() * childUnderEight;
        // needed protein
        totProtein += clientRequirementsByType.get(0).getProtein() * adultMales
                + clientRequirementsByType.get(1).getProtein() * adultFemales
                + clientRequirementsByType.get(2).getProtein() * childOverEight
                + clientRequirementsByType.get(3).getProtein() * childUnderEight;
        // needed other
        totOther += clientRequirementsByType.get(0).getOther() * adultMales + clientRequirementsByType.get(1).getOther() * adultFemales
                + clientRequirementsByType.get(2).getOther() * childOverEight
                + clientRequirementsByType.get(3).getOther() * childUnderEight;
        // needed total calories
        totCalories += clientRequirementsByType.get(0).getCalories() * adultMales
                + clientRequirementsByType.get(1).getCalories() * adultFemales
                + clientRequirementsByType.get(2).getCalories() * childOverEight
                + clientRequirementsByType.get(3).getCalories() * childUnderEight;

        totWholeGrains = totWholeGrains * numOfDays;
        totFruitVeggies = totFruitVeggies * numOfDays;
        totProtein = totProtein * numOfDays;
        totOther = totOther * numOfDays;
        totCalories = totCalories * numOfDays;

        // add all food to availableFood
        foodInventoryAccess.initializeConnection();
        foodInventoryAccess.selectAllFoodData();
        availableFood = foodInventoryAccess.getAllFood();
        foodInventoryAccess.close();

        this.availableFoodIndexes = IntStream.range(0, availableFood.size()).boxed().collect(Collectors.toList())
                .toArray(Integer[]::new); // creates an integer range from 0 up to length availableFood.size()
        this.minExcessCalories = totCalories;
        this.minExcessFoodIndexes = availableFoodIndexes;

    }

    public int getMinExcessCalories() {
        return this.minExcessCalories;
    }


    private int calculateExcess(Integer[] foodCombinationIndexes) {
        // given an array of foodIndexes, calculate and return how many more calories
        // are in that combination of fodo than required.
        int grainsSum = 0;
        int fruitVegSum = 0;
        int proteinSum = 0;
        int otherSum = 0;
        Food food;
        for (int idx : foodCombinationIndexes) {
            food = availableFood.get(idx);
            grainsSum += food.getWholeGrains();
            fruitVegSum += food.getFruitVeggies();
            proteinSum += food.getProtein();
            otherSum += food.getOther();
        }
        int foodCombinationCalories = grainsSum + fruitVegSum + proteinSum + otherSum;
        int excess = foodCombinationCalories - totCalories;
        return excess;
    }

    private boolean satisfiesConstraints(Integer[] foodCombinationIndexes) {
        int grainsSum = 0;
        int fruitVegSum = 0;
        int proteinSum = 0;
        int otherSum = 0;
        Food food;
        for (int idx : foodCombinationIndexes) {

            food = this.availableFood.get(idx);
            // System.out.println(food.getName());
            grainsSum += food.getWholeGrains();
            fruitVegSum += food.getFruitVeggies();
            proteinSum += food.getProtein();
            otherSum += food.getOther();
        }
        boolean grainsSatisfied = grainsSum >= totWholeGrains;
        boolean fruitVegSatisfied = fruitVegSum >= totFruitVeggies;
        boolean proteinSatisfied = proteinSum >= totProtein;
        boolean otherSatisfied = otherSum >= totOther;

        return grainsSatisfied && fruitVegSatisfied && proteinSatisfied && otherSatisfied;

    }

    

    // 2. check if combination satisfies requirements
    public boolean isConstraintsSatisfied() {
        return (hamper.getTotWholeGrainsInHamper() >= totWholeGrains
                && hamper.getTotFruitVeggiesInHamper() >= totFruitVeggies
                && hamper.getTotProteinInHamper() >= totProtein
                && hamper.getTotOtherInHamper() >= totOther);
    }

    public void addFoodItemsToHamper() {
    

        while (true) {
            // checks if all categories equal overshoot and break if they do
            if (isConstraintsSatisfied()) {
                break;
            } else {
                // calulates needed calories in each category
                int neededWholeGrains = totWholeGrains - hamper.getTotWholeGrainsInHamper();
                int neededFruitsVeggies = totFruitVeggies - hamper.getTotFruitVeggiesInHamper();
                int neededProtein = totProtein - hamper.getTotProteinInHamper();
                int neededOther = totOther - hamper.getTotOtherInHamper();

                // add item with highest percentage in category furthest from overshoot to
                // hamper
                // grains:
                if (neededWholeGrains >= neededFruitsVeggies && neededWholeGrains >= neededProtein
                        && neededWholeGrains >= neededOther) {
                    int maxWholeGrainsPer = 0;
                    int maxWholeGrainIndx = 0;
                    for (int i = 0; i < availableFood.size(); i++) { // wait why are we using the percentage instead of
                                                                     // cals? does it matter
                        if (availableFood.get(i).getWholeGrainsPer() > maxWholeGrainsPer) {
                            maxWholeGrainsPer = availableFood.get(i).getWholeGrainsPer();
                            maxWholeGrainIndx = i;
                        }
                    }
                    hamper.addFood(availableFood.get(maxWholeGrainIndx));
                    availableFood.remove(maxWholeGrainIndx);
                }
                // fruit and veggies
                else if (neededFruitsVeggies >= neededWholeGrains && neededFruitsVeggies >= neededProtein
                        && neededFruitsVeggies >= neededOther) {
                    int maxFruitsVeggiesPer = 0;
                    int maxFruitVeggieIndx = 0;
                    for (int i = 0; i < availableFood.size(); i++) {
                        if (availableFood.get(i).getFruitVeggiesPer() > maxFruitsVeggiesPer) {
                            maxFruitsVeggiesPer = availableFood.get(i).getFruitVeggiesPer();
                            maxFruitVeggieIndx = i;
                        }
                    }
                    hamper.addFood(availableFood.get(maxFruitVeggieIndx));
                    availableFood.remove(maxFruitVeggieIndx);
                }
                // protein
                else if (neededProtein >= neededFruitsVeggies && neededProtein >= neededWholeGrains
                        && neededProtein >= neededOther) {
                    int maxProteinPer = 0;
                    int maxProteinIndx = 0;
                    for (int i = 0; i < availableFood.size(); i++) {
                        if (availableFood.get(i).getProteinPer() > maxProteinPer) {
                            maxProteinPer = availableFood.get(i).getProteinPer();
                            maxProteinIndx = i;
                        }
                    }
                    hamper.addFood(availableFood.get(maxProteinIndx));
                    availableFood.remove(maxProteinIndx);
                }
                // other
                else if (neededOther >= neededFruitsVeggies && neededOther >= neededProtein
                        && neededOther >= neededWholeGrains) {
                    int maxOtherPer = 0;
                    int maxOtherIndx = 0;
                    for (int i = 0; i < availableFood.size(); i++) {
                        if (availableFood.get(i).getOtherPer() > maxOtherPer) {
                            maxOtherPer = availableFood.get(i).getOtherPer();
                            maxOtherIndx = i;
                        }
                    }
                    hamper.addFood(availableFood.get(maxOtherIndx));
                    availableFood.remove(maxOtherIndx);
                }
            }
        }

    }

    // returns string of all food left in the inventory
    public String formattedInventoryList() {
        String temp = "";
        for (int i = 0; i < availableFood.size(); i++) {
            temp += availableFood.get(i).formatFoodData() + "\n";
        }
        return temp;
    }

    // returns string of client needs from the database
    public String formattedClientTypesList() {
        String temp = "";
        for (int i = 0; i < clientRequirementsByType.size(); i++) {
            temp += clientRequirementsByType.get(i).formatClientTypeData() + "\n";
        }
        return temp;
    }

    // returns string of total hamper requirements for client group
    public String formattedTotalHamperRequirements() {
        return "Total Hamper Requirements:\nwhole grains: " + this.getTotWholeGrains() + ", fruit veggies: "
                + this.getTotFruitVeggies()
                + ", protein: " + this.getTotProtein() + ", other: " + this.getTotOther() + ", calories: "
                + this.getTotCalories() + "\n";
    }

    public int getTotCalories() {
        return totCalories;
    }

    public int getTotOther() {
        return totOther;
    }

    public int getTotProtein() {
        return totProtein;
    }

    public int getTotFruitVeggies() {
        return totFruitVeggies;
    }

    public int getTotWholeGrains() {
        return totWholeGrains;
    }

    // returns hamper object
    public Hamper getHamper() {
        return hamper;
    }
}
