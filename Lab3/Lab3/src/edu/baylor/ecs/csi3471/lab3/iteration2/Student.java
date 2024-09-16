package edu.baylor.ecs.csi3471.lab3.iteration2;

public class Student extends Person {

    private static int studentPopulation = 0;
    private double GPA;
    Student(){
        studentPopulation++;
    }



    public void setGPA(double GPA){
        this.GPA = GPA;
    }


    public double getGPA(){
        return GPA;
    }

    public static int getStudentPopulation(){
        return studentPopulation;
    }




}
