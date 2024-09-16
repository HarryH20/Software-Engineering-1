package edu.baylor.ecs.csi3471.lab3.iteration3;

public class CitySimulation {
    public static void main(String[] args){
       Student student1 = new Student();
       Student student2 = new Student();
       student1.setName("Harrison");
       student1.setIdNumber(22);
       student1.setGPA(3.75);
       student2.setName("Harrison");
       student2.setIdNumber(22);
       student2.setGPA(3.75);

       System.out.println(student1.equals(student2));


    }
}
