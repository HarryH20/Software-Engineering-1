package edu.baylor.ecs.csi3471.lab3.iteration2;

public class Person {
    private String name;
    private int idNumber;

    private static int population = 0;

    public Person(){
        population++;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setIdNumber(int idNumber){
        this.idNumber = idNumber;
    }
    public String getName(){
        return this.name;
    }
    public int getIdNumber(){
        return this.idNumber;
    }

    public static int getPopulation(){
        return population;
    }


}


