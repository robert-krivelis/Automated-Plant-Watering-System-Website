package edu.ucalgary.ensf409;

import java.util.Arrays;

public class FoodBankManagement {
    public static void main(String[] args) {
        // creates hamper creator object and inputs client configuration for the hamper
        HamperCreator createdHamper = new HamperCreator(0, 0, 0, 1); // AM AF CO8 CU8
        // calculates total calories required for hamper

        // adds food from inventory to hamper
        System.out.println(createdHamper.getTotCalories());
        // System.out.println(Arrays.toString(createdHamper.bruteForceMostEfficientHamper()));
        System.out.println(createdHamper.getMinExcessCalories());
        // createdHamper.bruteForceMostEfficientHamper();

        // createdHamper.addFoodItemsToHamper();

        // System.out.println(createdHamper.formattedTotalHamperRequirements());

        // System.out.println(createdHamper.getHamper().formattedCaloriesInHamper());

        // System.out.println(createdHamper.getHamper().formattedFoodInHamper());

        // System.out.println(createdHamper.formattedInventoryList());

    }
}
