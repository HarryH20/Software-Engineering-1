package edu.baylor.ecs.csi3471.lab3.iteration5;



import java.util.Random;

public class CitySimulation {
    public static void dispatch(Person p){
        p.printMe();
    }
    public static void main(String[] args){
        final int CITIZENS = 100;
        int people = 0;
        int students = 0;

        Random random = new Random();

        for(int i = 0; i < CITIZENS; i++) {
            Person p;
            if (random.nextBoolean()) {
                p = new Person();
            } else {
                p = new Student();
            }
            dispatch(p);
            /*It prints out I am a student because java
            * determines which type of object p is referring to at
            * runtime. This works because we have overriden the
            * printMe method.
            */
        }
        System.out.println("Number of people " + Person.getPopulation());
        System.out.println("Number of students " + Student.getStudentPopulation());




    }
}
