package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Arrays;

public class FoodBankManager {
    private ArrayList<ArrayList<Integer>> allClientTypesFromUser;

    public FoodBankManager(ArrayList<ArrayList<Integer>> allClientTypesFromUser) {
        this.allClientTypesFromUser = allClientTypesFromUser;
    }

    public void generateOrder() {
        for (ArrayList<Integer> hamperClients : this.allClientTypesFromUser) {
            try {
                HamperCreator createdHamper = new HamperCreator(
                    hamperClients.get(0),
                    hamperClients.get(1),
                    hamperClients.get(2),
                    hamperClients.get(3)); // AM AF CO8 CU8
                System.out.println(createdHamper.getTotCalories());
                System.out.println(createdHamper.getMinExcessCalories());
            } catch (InsufficientCaloriesForHamperException e) {
                generateErrorForm();
            }

        }
        // // creates hamper creator object and inputs client configuration for the hamper
        // HamperCreator createdHamper = new HamperCreator(0, 0, 0, 1); // AM AF CO8 CU8
        // // calculates total calories required for hamper

        // // adds food from inventory to hamper
        // System.out.println(createdHamper.getTotCalories());
        // // System.out.println(Arrays.toString(createdHamper.bruteForceMostEfficientHamper()));
        // System.out.println(createdHamper.getMinExcessCalories());
        // // createdHamper.bruteForceMostEfficientHamper();

        // // createdHamper.addFoodItemsToHamper();

        // // System.out.println(createdHamper.formattedTotalHamperRequirements());

        // // System.out.println(createdHamper.getHamper().formattedCaloriesInHamper());

        // // System.out.println(createdHamper.getHamper().formattedFoodInHamper());

        // // System.out.println(createdHamper.formattedInventoryList());

    }

    private void generateErrorForm() {
    }
}
