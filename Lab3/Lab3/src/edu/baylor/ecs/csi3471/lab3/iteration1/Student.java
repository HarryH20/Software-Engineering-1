package edu.baylor.ecs.csi3471.lab3.iteration1;

public class Student {
    private String name;
    private int idNumber;
    private double GPA;

    public Student(){

    }

    public void setName(String name){
        this.name = name;
    }

    public void setIdNumber(int idNumber){
        this.idNumber = idNumber;
    }

    public void setGPA(double GPA){
        this.GPA = GPA;
    }

    public String getName(){
        return name;
    }

    public int getIdNumber(){
        return idNumber;
    }

    public double getGPA(){
        return GPA;
    }




}
