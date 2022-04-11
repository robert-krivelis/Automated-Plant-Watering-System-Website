package edu.ucalgary.ensf409;

public class ClientTypes {
    // contains information about a client
    private String clientType;
    // percent of each catagory
    private int wholeGrains;
    private int fruitVeggies;
    private int protein;
    private int other;
    // calories needed by person
    private int calories;

    // constructor
    public ClientTypes(String ClientType, int WholeGrains, int FruitVeggies, int Protein, int Other,
            int Calories) {
        this.clientType = ClientType;
        this.wholeGrains = WholeGrains;
        this.fruitVeggies = FruitVeggies;
        this.protein = Protein;
        this.other = Other;
        this.calories = Calories;
    }

    // format client needs data
    public String formatClientTypeData() {
        String formattedClientTypeData = "";
        formattedClientTypeData += this.clientType;
        formattedClientTypeData += "\n(whole grains: ";
        formattedClientTypeData += this.getWholeGrains();
        formattedClientTypeData += ", fruit veggies: ";
        formattedClientTypeData += this.getFruitVeggies();
        formattedClientTypeData += ", protein: ";
        formattedClientTypeData += this.getProtein();
        formattedClientTypeData += ", other: ";
        formattedClientTypeData += this.getOther();
        formattedClientTypeData += ", calories: ";
        formattedClientTypeData += this.getCalories() + ")";
        return formattedClientTypeData;

    }

    // getters
    public String getClientType() {
        return clientType;
    }

    public int getWholeGrains() {
        return (int) (((double) wholeGrains / 100) * calories);
    }

    public int getFruitVeggies() {
        return (int) (((double) fruitVeggies / 100) * calories);
    }

    public int getProtein() {
        return (int) (((double) protein / 100) * calories);
    }

    public int getOther() {
        return (int) (((double) other / 100) * calories);
    }

    public int getCalories() {
        return calories;
    }

}
