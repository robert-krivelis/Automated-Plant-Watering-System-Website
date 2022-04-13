package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

public class FoodBankManagementTest {
    @Test
    public void HamperCreatorAccessesDB() {
                
    }
    
    @Test
    public void FoodSelectionAlgorithmOneMaleTest() {
        ArrayList<Integer> hamperClientsByType = new ArrayList<Integer>(Arrays.asList(1,0,0,0));

        Food food1 = new Food("testfood1", 30, 30, 30, 10, 9996);
        Food food2 = new Food("testfood2", 30, 30, 30, 10, 20000);
        Food food3 = new Food("testfood3", 30, 30, 30, 10, 30000);
        Food food4 = new Food("testfood1", 3, 3, 3, 3, 11);
        Food food5 = new Food("testfood2", 4, 1, 30, 10, 20000);
        Food food6 = new Food("testfood3", 1, 30, 30, 150, 30000);
        ArrayList<Food> allFood = new ArrayList<Food>();
        ArrayList<ClientTypes> clientRequirementsByType = new ArrayList<ClientTypes>();
        ClientTypes client1 = new ClientTypes("male", 30, 30, 30, 10, 1428);
        // add four times(once for each type of client there should be)
        clientRequirementsByType.add(client1);
        clientRequirementsByType.add(client1);
        clientRequirementsByType.add(client1);
        clientRequirementsByType.add(client1);
        allFood.add(food1);
        allFood.add(food2);
        allFood.add(food3);
        allFood.add(food4);
        allFood.add(food5);
        FoodSelectionAlgorithm foodAlg = new FoodSelectionAlgorithm(hamperClientsByType, allFood, clientRequirementsByType);
        Hamper h = foodAlg.simulatedAnnealingAlgorithm();
        // System.out.println(h.getHamperFood().size());
    }

}
