package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FoodSelectionAlgorithm {
    // private Hamper hamper = new Hamper();
    private ArrayList<Integer> hamperClientsByType; // we only need one
    private ArrayList<Food> allFood;

    // private HashMap<String, Integer> maxCaloriesInEachCategory;
    private ArrayList<Integer> maxCaloriesInEachCategory = new ArrayList<Integer>();
    private ArrayList<Integer> calorieTargetsForEachCategory;
    // private int excessCaloriesMax; // calories in all food - targets
    private int maxCalories; // calories in all food
    private int excessCaloriesMin; // how few calories over excess - will change due to algo
    private Hamper bestHamper; // best hamper found (fewest excess cals) - will change due to
                               // algo
    private ArrayList<Food> newAllFood; // for comparison in algorithm

    

    FoodSelectionAlgorithm(ArrayList<Integer> hamperClientsByType, ArrayList<Food> allFood,
            ArrayList<ClientTypes> clientRequirementsByType) throws InsufficientCaloriesForHamperException {
        this.hamperClientsByType = hamperClientsByType;
        this.allFood = allFood;
        this.maxCaloriesInEachCategory = calculateMaxCaloriesInEachCategory();
        this.calorieTargetsForEachCategory = calculateCalorieTargetsForEachCategory(clientRequirementsByType);
        this.maxCalories = sumArrayList(
                this.maxCaloriesInEachCategory);
        this.excessCaloriesMin = maxCalories;
        if (this.maxCalories < sumArrayList(this.calorieTargetsForEachCategory)){
            throw new InsufficientCaloriesForHamperException("not enough calories to meet requirements");
        }
        // this.excessCaloriesMax = this.maxCalories
        // - sumArrayList(this.calorieTargetsForEachCategory);
        // this.allFoodSimpleArray = foodArrayToCaloriesArray(this.allFood);

    }

    Hamper simulatedAnnealingAlgorithm() {
        // heuristic search algorithm for finding most efficient hamper
        // algorithm search parameters
        // internal struggle: does this algorithm use just simple arraylist ints or
        // actual hampers
        double temp = 100000.0; // starting temperature
        double cool = 0.999999; // how fast it cools
        double min_temp = 0.0000001; // when to exit loop
        int new_cost;
        int current_cost;

        Hamper currentSolution = new Hamper();
        for (Food food : this.allFood) {
            currentSolution.addFood(food);
        }
        Hamper newSolution = new Hamper(currentSolution.getHamperFood());
        // keep track of best solution so far
        // this.bestHamper = (ArrayList<Integer>) currentSolution.clone();
        this.bestHamper = currentSolution;

        // adjust available food according to what was added to the hamper
        for (Food food : currentSolution.getHamperFood())
            this.allFood.remove(food);

        while (temp > min_temp) {

            // change the existing solution slightly
            newSolution = neighbor(currentSolution); // note side effect changes this.newAllFood
            // calculate the current cost and the new cost
            new_cost = costf(newSolution);
            current_cost = costf(currentSolution);
            // System.out.println(new_cost);

            // calculate probability cutoff
            double p = Math.pow(Math.E, ((-new_cost - current_cost) / temp));
            // is it better, or does it make the probability cutoff?
            if (new_cost < current_cost || Math.random() < p) {
                // replace current solution with new solution
                currentSolution = new Hamper(cloneFoodArray(newSolution.getHamperFood()));
                this.allFood = cloneFoodArray(this.newAllFood);
                // System.out.println("new cost: " + String.valueOf(new_cost));
            }
            // my code for calculating best so far
            if (new_cost < this.excessCaloriesMin) {
                this.excessCaloriesMin = new_cost;
                this.bestHamper = new Hamper(cloneFoodArray(newSolution.getHamperFood()));
            }
            // # decrease the temperature
            temp = temp * cool;
        }
        System.out.println("final hamper size: " + String.valueOf(currentSolution.getHamperFood().size()));
        System.out.println("final calories wasted total:");
        System.out.println(costf(currentSolution));
        System.out.println("final calories wasted best:");
        System.out.println(costf(this.bestHamper));
        System.out.println(this.excessCaloriesMin);
        System.out.println(check_valid(this.bestHamper));
        System.out.println("final calories wasted by category:");
        System.out.println(costByCategory(currentSolution));
        System.out.println("targets");
        System.out.println(calorieTargetsForEachCategory);
        return currentSolution;
    }

    private int costf(Hamper solution) {
        // cost function for annealing, which is equal to excess calories that are
        // wasted above target. simulated annealing tries to minimize the cost function.

        int cost = this.maxCalories;
        // if not valid return max calories
        if (!check_valid(solution)) {
            cost = this.maxCalories;
            return cost;
        }
        int waste = 0;
        ArrayList<Integer> foodCaloriesByCategory = new ArrayList<Integer>(
                Arrays.asList(solution.getTotWholeGrainsInHamper(), solution.getTotFruitVeggiesInHamper(),
                        solution.getTotProteinInHamper(), solution.getTotOtherInHamper()));
        for (int i = 0; i < 4; i++) {
            waste += foodCaloriesByCategory.get(i) - calorieTargetsForEachCategory.get(i);
        }
        return waste;
    }

    private ArrayList<Integer> costByCategory(Hamper solution) {
        // cost function for annealing, which is equal to excess calories that are
        // wasted above target. simulated annealing tries to minimize the cost function.
        
        int cost = this.maxCalories;
        // if not valid return max calories
        if (!check_valid(solution)) {
            cost = this.maxCalories;
            return new ArrayList<Integer>(Arrays.asList(cost, cost, cost, cost));
        }
        ArrayList<Integer> wasteArray = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
        
        ArrayList<Integer> foodCaloriesByCategory = new ArrayList<Integer>(
                Arrays.asList(solution.getTotWholeGrainsInHamper(), solution.getTotFruitVeggiesInHamper(),
                        solution.getTotProteinInHamper(), solution.getTotOtherInHamper()));
        for (int i = 0; i < 4; i++) {
            wasteArray.set(i,foodCaloriesByCategory.get(i) - calorieTargetsForEachCategory.get(i));
        }
        return wasteArray;
    }

    private boolean check_valid(Hamper solution) {
        ArrayList<Integer> foodCaloriesByCategory = new ArrayList<Integer>(
                Arrays.asList(solution.getTotWholeGrainsInHamper(), solution.getTotFruitVeggiesInHamper(),
                        solution.getTotProteinInHamper(), solution.getTotOtherInHamper()));

        // for each category (grain, veg, prot, other) see if it's valid (all categories
        // met)
        for (int i = 0; i < 4; i++) {
            if (foodCaloriesByCategory.get(i) < calorieTargetsForEachCategory.get(i)) {
                return false;
            }
        }
        return true;
    }

    private Hamper neighbor(Hamper currentSolution) {
        /*
         * change the current solution slightly, by swapping food between hamper and
         * available food or adding/removing food to hamper/all_food.
         * 50% chance to swap items, 50% chance to simply add or remove an item
         */
        Hamper newSolution = new Hamper(currentSolution.getHamperFood()); // copy current solution
        this.newAllFood = cloneFoodArray(this.allFood);

        // * # flip a coin to see whether to swap items
        // * swap = bool(random.randint(0, 1))
        boolean swap;
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1); // get either 0 or 1
        if (randomNum == 1) {
            swap = true;
        } else {
            swap = false;
        }
        // * if len(newSolution) == 0 or len(newAllFood) == 0:
        if (newSolution.getHamperFood().size() == 0 || this.newAllFood.size() == 0) {
        }
        // * swap = False
        swap = false;
        // * if swap:
        if (swap) {
            // * # select random foods to swap
            int i = ThreadLocalRandom.current().nextInt(0, this.newAllFood.size());
            Food food1 = this.newAllFood.get(i);
            int j = ThreadLocalRandom.current().nextInt(0, newSolution.getHamperFood().size());
            Food food2 = newSolution.getHamperFood().get(j);

            // * # swap foods
            newSolution.addFood(food1);
            this.newAllFood.remove(food1);
            newSolution.removeFood(food2);
            this.newAllFood.add(food2);

        } else {
            boolean add;
            int randomNum2 = ThreadLocalRandom.current().nextInt(0, 1 + 1); // get either 0 or 1
            if (randomNum2 == 1) {
                add = true;
            } else {
                add = false;
            }
            // * check for edge cases where hamper or available food is empty
            if (newSolution.getHamperFood().size() == 0) {
                // if current_hamper is empty must add food to hamper
                add = true;
            } else if (this.newAllFood.size() == 0) {
                // if available_food is empty must remove food from hamper
                add = false;
            }
            // if coinflip succeeds add to current
            if (add) {

                // select random food to add to hamper
                int i = ThreadLocalRandom.current().nextInt(0, this.newAllFood.size());
                Food food = this.newAllFood.get(i);

                // add food to hamper and remove it from available food
                newSolution.addFood(food);
                this.newAllFood.remove(food);

            }
            // if coinflip fails remove from current
            else {
                // select random food to remove from hamper
                int i = ThreadLocalRandom.current().nextInt(0, newSolution.getHamperFood().size());
                Food food = newSolution.getHamperFood().get(i);

                // add food to hamper and remove it from available food
                newSolution.removeFood(food);
                this.newAllFood.add(food);
            }

        }
        return newSolution;
    }

    private ArrayList<Integer> calculateMaxCaloriesInEachCategory() {
        ArrayList<Integer> cals = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0));
        for (Food food : this.allFood) {
            cals.set(0, cals.get(0) + food.getWholeGrains());
            cals.set(1, cals.get(1) + food.getFruitVeggies());
            cals.set(2, cals.get(2) + food.getProtein());
            cals.set(3, cals.get(3) + food.getOther());
        }
        return cals;
    }

    private Integer sumArrayList(ArrayList<Integer> arr) {
        Integer sum = 0;
        for (Integer val : arr)
            sum += val;
        return sum;
    }

    private ArrayList<Food> cloneFoodArray(ArrayList<Food> arr) {
        ArrayList<Food> clone = new ArrayList<Food>();
        for (Food f : arr)
            clone.add(new Food(f.getName(), f.getWholeGrainsPer(), f.getFruitVeggiesPer(), f.getProteinPer(),
                    f.getOtherPer(), f.getCalories()));

        return clone;
    }

    private ArrayList<ArrayList<Integer>> foodArrayToCaloriesArray(ArrayList<Food> foodArray) {
        // this needs to be like a double list right?
        ArrayList<ArrayList<Integer>> caloriesFullArray = new ArrayList<ArrayList<Integer>>();
        for (Food food : foodArray) {
            ArrayList<Integer> caloriesCategoriesArray = new ArrayList<Integer>();
            caloriesCategoriesArray.add(food.getWholeGrains());
            caloriesCategoriesArray.add(food.getFruitVeggies());
            caloriesCategoriesArray.add(food.getProtein());
            caloriesCategoriesArray.add(food.getOther());
            caloriesFullArray.add(caloriesCategoriesArray);
        }
        return caloriesFullArray;

    }

    private ArrayList<Integer> calculateCalorieTargetsForEachCategory(ArrayList<ClientTypes> clientRequirementsByType) {

        int numOfDaysInWeek = 1;
        // for each category (0=grains,1=veg,2=prot,3=other), sum the calorie
        // requirements for the entire family (this.hamperClientsByType) and store it in
        // CalorieTargetsForEachCategory
        // for (int requirement: this.hamperClientsByType)
        int numAdultMales = this.hamperClientsByType.get(0);
        int numAdultFemales = this.hamperClientsByType.get(1);
        int numChildOverEight = this.hamperClientsByType.get(2);
        int numChildUnderEight = this.hamperClientsByType.get(3);

        int targetWholeGrains = 0;
        int targetFruitVeggies = 0;
        int targetProtein = 0;
        int targetOther = 0;
        // needed whole grains
        targetWholeGrains += clientRequirementsByType.get(0).getWholeGrains() * numAdultMales
                + clientRequirementsByType.get(1).getWholeGrains() * numAdultFemales
                + clientRequirementsByType.get(2).getWholeGrains() * numChildOverEight
                + clientRequirementsByType.get(3).getWholeGrains() * numChildUnderEight;
        // needed fruit veggies
        targetFruitVeggies += clientRequirementsByType.get(0).getFruitVeggies() * numAdultMales
                + clientRequirementsByType.get(1).getFruitVeggies() * numAdultFemales
                + clientRequirementsByType.get(2).getFruitVeggies() * numChildOverEight
                + clientRequirementsByType.get(3).getFruitVeggies() * numChildUnderEight;
        // needed protein
        targetProtein += clientRequirementsByType.get(0).getProtein() * numAdultMales
                + clientRequirementsByType.get(1).getProtein() * numAdultFemales
                + clientRequirementsByType.get(2).getProtein() * numChildOverEight
                + clientRequirementsByType.get(3).getProtein() * numChildUnderEight;
        // needed other
        targetOther += clientRequirementsByType.get(0).getOther() * numAdultMales
                + clientRequirementsByType.get(1).getOther() * numAdultFemales
                + clientRequirementsByType.get(2).getOther() * numChildOverEight
                + clientRequirementsByType.get(3).getOther() * numChildUnderEight;

        targetWholeGrains *= numOfDaysInWeek;
        targetFruitVeggies *= numOfDaysInWeek;
        targetProtein *= numOfDaysInWeek;
        targetOther *= numOfDaysInWeek;
        List<Integer> targets = Arrays.asList(targetWholeGrains, targetFruitVeggies, targetProtein, targetOther);
        ArrayList<Integer> calorieTargets = new ArrayList<Integer>(targets); // will be size 4
        System.out.println("Calorie targets by category:");
        System.out.println(calorieTargets);
        return calorieTargets;
    }

}