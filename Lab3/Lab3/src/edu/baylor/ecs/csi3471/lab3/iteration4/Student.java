package edu.baylor.ecs.csi3471.lab3.iteration4;


import java.util.Objects;

public class Student extends Person {

    private static int studentPopulation = 0;
    Student(){
        super();
        studentPopulation++;
    }


    private double GPA;


    public void setGPA(double GPA){
        this.GPA = GPA;
    }


    public double getGPA(){
        return this.GPA;
    }

    public static int getStudentPopulation(){
        return studentPopulation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(GPA, student.GPA) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), GPA);
    }
}
