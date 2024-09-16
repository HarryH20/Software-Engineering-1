/*
* Harrison Hassler
* Assignment 2 - parsing a CSV file
* 1/28/2024
* Soft Eng 1
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CSVParser {

    public static void main(String[] args) throws FileNotFoundException {
        if (args == null || args.length != 1) {
            System.out.println("syntax is CSVParser <file path>");
            System.exit(0);
        }
        Scanner scanner = new Scanner(new File(args[0]));

        //Arrays to hold results
        ArrayList<String> longestWorkoutPerson = new ArrayList<>();
        long longestWorkout = 0;

        ArrayList<String> heaviestPeople = new ArrayList<>();
        double heaviestWeight = 0;

        ArrayList<String> highestVitaminCPeople = new ArrayList<>();
        double highestVitaminC = 0;

        int totalPeople = 0;
        double totalWeight = 0;

        //Trashes firstline
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
        if(scanner.hasNextLine()){
            scanner.nextLine();
        }

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            //Splits each line by comma
            String[] columns = line.split(",");

            if(columns.length == 8){
                String name = columns[0].replaceAll("\"", "");
                String weight = columns[2].replaceAll("\"", "");
                String startDate = columns[5].replaceAll("\"", "");
                String endDate = columns[6].replaceAll("\"", "");
                String vitaminC = columns[7].replaceAll("\"", "");

                try {
                    Date newStartDate = dateFormat.parse(startDate);
                    Date newEndDate = dateFormat.parse(endDate);
                    long workoutTime = newEndDate.getTime()-newStartDate.getTime();

                    double newWeight = Double.parseDouble(weight);
                    double newVitaminC = Double.parseDouble(vitaminC.split("/")[1]);

                    //Find person with the longest workout
                    if(workoutTime > longestWorkout){
                        longestWorkout = workoutTime;
                        longestWorkoutPerson.clear();
                        longestWorkoutPerson.add(name);

                    }
                    //If there are multiple people with the longest workout
                    else if(workoutTime == longestWorkout){
                        longestWorkoutPerson.add(name);
                    }

                    //Find person with highest weight
                    if(newWeight > heaviestWeight){
                        heaviestWeight = newWeight;
                        heaviestPeople.clear();
                        heaviestPeople.add(name);
                    }
                    //If there are multiple people with the highest weight
                    else if(newWeight == heaviestWeight){
                        heaviestPeople.add(name);
                    }

                    //Find person with highest vitamin C
                    if(newVitaminC > highestVitaminC){
                        highestVitaminC = newVitaminC;
                        highestVitaminCPeople.clear();
                        highestVitaminCPeople.add(name);

                    }
                    //If there are multiple people with the highest vitamin C
                    else if(newVitaminC == highestVitaminC){
                        highestVitaminCPeople.add(name);
                    }

                    //Calculate total people and weight
                    totalWeight += newWeight;
                    totalPeople++;


                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


            }


        }


        //Output Results
        if(totalPeople > 0){
            double averageWeight = totalWeight/totalPeople;
            System.out.println("The person(s) with the longest workout session " + longestWorkoutPerson);
            System.out.println("The person(s) with the highest weight " + heaviestPeople);
            System.out.println("The person(s) with the highest vitamin C consumption level " + highestVitaminCPeople);
            System.out.println("Number of all people " + totalPeople);
            System.out.println("Average weight " + averageWeight);
        }
        else{
            System.out.println("No valid data");
        }

        //Close scanner
        scanner.close();

    }


}
