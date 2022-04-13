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
        ArrayList<Food> allFood = new ArrayList<Food>();
        ArrayList<ClientTypes> clientRequirementsByType = new ArrayList<ClientTypes>();
        ClientTypes client1 = new ClientTypes("male", 30, 30, 30, 10, 9996);
        // add four times for each type of client
        clientRequirementsByType.add(client1);
        clientRequirementsByType.add(client1);
        clientRequirementsByType.add(client1);
        clientRequirementsByType.add(client1);
        allFood.add(food1);
        FoodSelectionAlgorithm foodAlg = new FoodSelectionAlgorithm(hamperClientsByType, allFood, clientRequirementsByType);
    }

}
