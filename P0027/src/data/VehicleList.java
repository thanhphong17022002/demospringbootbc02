 
package data;

import java.util.regex.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.*;

 
public class VehicleList {

    ArrayList<Vehicle> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    MyToys toy = new MyToys();

    public VehicleList() {
    }


    public void AddNew() {
        String ID_Vehicle;
        String Name_Vehicle;
        String color_Vehicle;
        double price_Vehicle;
        String brand_Vehicle;
        String type;
        int productYear;

        boolean IDExists;

        Pattern idPattern = Pattern.compile("@\\d{3}"); 

        do {
            IDExists = false;
            ID_Vehicle = toy.getString("Input ID (such as @001): ");

            Matcher matcher = idPattern.matcher(ID_Vehicle);
            if (!matcher.matches()) {
                System.out.println("Invalid ID format. Please use format #001.");
                continue; 
            }

            for (Vehicle o : list) {
                if (o.getID_Vehicle().equals(ID_Vehicle)) {
                    IDExists = true;
                    break;
                }
            }

            if (IDExists) {
                System.out.println("The vehicle with ID " + ID_Vehicle + " already exists.");
            }
        } while (!IDVehicleIsValid(ID_Vehicle));

        Name_Vehicle = toy.getString("Input name: ");
        color_Vehicle = toy.getString("Input color: ");
        price_Vehicle = toy.getAnDouble("Input price: ");
        brand_Vehicle = toy.getString("Input brand: ");
        type = toy.getString("Input type: ");

        do {
            productYear = toy.getAnInt("Input year (between 2000 and 2023): ");
            if (productYear < 2000 || productYear > 2023) {
                System.out.println("Year must be between 2000 and 2023.");
            }
        } while (productYear < 2000 || productYear > 2023);

        list.add(new Vehicle(ID_Vehicle, Name_Vehicle, color_Vehicle, price_Vehicle, brand_Vehicle, type, productYear));
        System.out.println("New vehicle has been added successfully.");
    }


    private boolean IDVehicleIsValid(String id) {
        Pattern idPattern = Pattern.compile("#\\d{3}");
        Matcher matcher = idPattern.matcher(id);
        return matcher.matches();
    }


    public void CheckExists() {
        System.out.println("Check exist by ID: ");
        String checkId = toy.getString("Input ID:");

        boolean foundInList = false;
        boolean foundInFile = false;

        for (Vehicle vehicle : list) {
            if (vehicle.getID_Vehicle().equals(checkId)) {
                System.out.println("Existed Vehicle:");
                System.out.println(vehicle);
                foundInList = true;
                break;
            }
        }

        if (!foundInList) {
            System.out.println("No Vehicle Found in List!");
        }

        try {
            FileReader fr = new FileReader("vehicle.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("ID: " + checkId)) {
                    System.out.println("Existed Vehicle in File:");
                    System.out.println(line);
                    foundInFile = true;
                    break; // Exit the loop if found in the file
                }
            }

            br.close();
            fr.close();

            if (!foundInFile) {
                System.out.println("No Vehicle Found in File!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

      
    }

    public void updateVehicle() { 
        if (!list.isEmpty()) {
            String idToUpdate = toy.getString("Update by ID: ");
            boolean found = false;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID_Vehicle().equals(idToUpdate)) {
                    found = true;
                    System.out.println("Update " + list.get(i).getName_Vehicle());

                    // Prompt for new values
                    String newName = toy.getString("Update Name: ");
                    String newColor = toy.getString("Update Color: ");
                    double newPrice = toy.getAnDouble("Update Price: ");
                    String newBrand = toy.getString("Update Brand: ");
                    String newType = toy.getString("Update Type: ");
                    int newProductYear = toy.getAnInt("Update Year: ");

                    // Update the Vehicle object
                    list.get(i).setName_Vehicle(newName);
                    list.get(i).setColor_Vehicle(newColor);
                    list.get(i).setPrice_Vehicle(newPrice);
                    list.get(i).setBrand_Vehicle(newBrand);
                    list.get(i).setType(newType);
                    list.get(i).setProductYear(newProductYear);

                    System.out.println("After update: " + list.get(i));
                    break; // Exit loop after updating the vehicle
                }
            }

            if (!found) {
                System.out.println("Vehicle with ID " + idToUpdate + " not found.");
            }
        } else {
            System.out.println("List is empty. Nothing to update.");
        }
    }

    public void deleteVehicle() { //4
        if (!list.isEmpty()) {
            String idToDelete = toy.getString("Delete by ID: ");
            boolean found = false;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID_Vehicle().equals(idToDelete)) {
                    found = true;
                    System.out.println("Deleting " + list.get(i).getName_Vehicle());
                    list.remove(i);
                    System.out.println("Vehicle deleted.");
                    break;  
                }
            }

            if (!found) {
                System.out.println("Vehicle with ID " + idToDelete + " not found.");
            }
        } else {
            System.out.println("List is empty. Nothing to delete.");
        }
    }


    public void searchByName() {
        if (!list.isEmpty()) {
            String n = toy.getString("Input Name to search: ");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName_Vehicle().equalsIgnoreCase(n)) {
                    System.out.println(list.get(i));
                }

            }
        } else {
            System.out.println("nothing");
        }

    }

    
    public void searchById() {
        if (!list.isEmpty()) {
            String n = toy.getString("Input ID to search: ");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID_Vehicle().equalsIgnoreCase(n)) {
                    System.out.println(list.get(i));
                }

            }
        } else {
            System.out.println("nothing");
        }

    }


    public void showDES() {
        if (list.isEmpty()) {
            System.out.println(" EMPTY LIST ");
        } else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getPrice_Vehicle() < list.get(j).getPrice_Vehicle()) {
                        Vehicle t = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, t);
                    }
                }
            }
            for (Vehicle o : list) {
                System.out.println(o);
            }
        }
    }

    public void saveAllVehicleToFile() { 
        try {
            FileWriter fw = new FileWriter("vehicle.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Vehicle o : list) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
        }
        System.out.println(" SAVE VEHICLE SUCCESSFULLY ");
    }

    public void DisplayAll() { 

        try {

            FileReader fr = new FileReader("vehicle.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {

                    break;
                }

                System.out.println(line);
            }
        } catch (Exception e) {
        }
    }

   

    public void run() {
        int choice;
        do {
            System.out.println("==================================");
            System.out.println(" 1. Add new in list \n"
                    + " 2. CheckExists in list and file \n"
                    + " 3. Update vehicle in list \n "
                    + "4. Delete vehicle in list \n "
                    + "5. Search vehicle in list\n "
                    + "6. Display all Vehicle in list \n "
                    + "7. Save all vehicles to file. \n "
                    + "8. Print all vehicle from the file. \n"
                    + " 0. EXIT");
            System.out.println("==================================");

            choice = toy.getAnInt("Get a choice: ");
            switch (choice) {
                case 1:
                    AddNew();
                    break;
                case 2:
                    CheckExists();
                    break;
                case 3:
                    updateVehicle();
                    break;
                case 4:
                    deleteVehicle();
                    break;
                case 5:
                    int miniChoice;
                    System.out.println("5.1: Search by ID_Vehicle. \n"
                            + "5.2: Search by Name_Vehicle. \n"
                            + "0: exit");
                    miniChoice = toy.getAnInt("Get a choice: ");
                    switch (miniChoice) {
                        case 1:
                            searchById();
                            break;
                        case 2:
                            searchByName();
                            break;
                        default:
                            System.out.println("NOT FOUND, CHOOSE AGAIN");
                            break;
                        case 0:
                            System.out.println("GOODBYE");
                            break;
                    }
                    break;
                case 6:
                    showDES();
                    break;
                case 7:
                    saveAllVehicleToFile();
                    break;
                case 8:
                    DisplayAll();
                   
                    break;
                default:
                    System.out.println("NOT FOUND, CHOOSE AGAIN");
                    break;
                case 0:
                    System.out.println("GOODBYE");
                    break;
            }
        } while (choice != 0);
    }

}
