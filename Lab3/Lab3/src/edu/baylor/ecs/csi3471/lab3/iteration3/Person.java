package edu.baylor.ecs.csi3471.lab3.iteration3;

import java.util.Objects;

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
        return name;
    }
    public int getIdNumber(){
        return idNumber;
    }

    public static int getPopulation(){
        return population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return idNumber == person.idNumber && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, idNumber);
    }
}


