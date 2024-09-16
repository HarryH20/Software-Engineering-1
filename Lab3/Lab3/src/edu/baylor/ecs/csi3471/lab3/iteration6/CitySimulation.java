package edu.baylor.ecs.csi3471.lab3.iteration6;



import java.util.Random;

public class CitySimulation {
    public static void identify(Person p){
        System.out.println("this is a person");
    }

    public static void identify(Student s){
        System.out.println("this is a student");
    }
    public static void dispatch(Person p){
        //p.printMe();
        identify(p);
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
            /*It will never print out this is a student
            * because the method that gets called is determined at
            * compile time and since type p is person it will
            * never call identify on a type student.
            */
        }
        System.out.println("Number of people " + Person.getPopulation());
        System.out.println("Number of students " + Student.getStudentPopulation());




    }
}
