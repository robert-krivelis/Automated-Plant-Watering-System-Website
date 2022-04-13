package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

public class FoodInventoryDatabaseAccess {
    public final String DBURL = "jdbc:mysql://localhost/food_inventory";
    public final String USERNAME = "student";
    public final String PASSWORD = "ensf409";

    private Connection dbConnect;
    private ResultSet results;
    private ArrayList<ClientTypes> clientRequirementsByType = new ArrayList<ClientTypes>();
    private ArrayList<Food> theFood = new ArrayList<Food>();

    public FoodInventoryDatabaseAccess() {
        initializeConnection();
        selectAllClientTypes();
        selectAllFoodData();
        close();
    }

    public void initializeConnection() {

        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // gets the client needs from the database
    public void selectAllClientTypes() {

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM DAILY_CLIENT_NEEDS");

            while (results.next()) {
                clientRequirementsByType.add(new ClientTypes(results.getString("Client"), results.getInt("WholeGrains"),
                        results.getInt("FruitVeggies"), results.getInt("Protein"), results.getInt("Other"),
                        results.getInt("Calories")));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // get array list of client needs objects
    public ArrayList<ClientTypes> getClientRequirementsByType() {
        return clientRequirementsByType;
    }

    // gets food from the database
    public void selectAllFoodData() {

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM AVAILABLE_FOOD");
            while (results.next()) {
                theFood.add(
                        new Food(results.getString("Name"), results.getInt("GrainContent"), results.getInt("FVContent"),
                                results.getInt("ProContent"), results.getInt("Other"), results.getInt("Calories")));
            }
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    // get array list of food objects
    public ArrayList<Food> getAllFood() {
        return theFood;
    }

    // close database connection
    public void close() {

        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
