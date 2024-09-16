package edu.baylor.ecs.csi3471.lab3.iteration2;
import java.util.Random;
public class CitySimulation {
    public static void main(String[] args){
        final int CITIZENS = 100;
        int people = 0;
        int students = 0;

        Random random = new Random();

        for(int i = 0; i < CITIZENS; i++){
            if(random.nextBoolean()){
                Person person = new Person();
            }
            else{
                Student student = new Student();
            }
        }
        System.out.println("Number of people " + Person.getPopulation());
        System.out.println("Number of students " + Student.getStudentPopulation());




    }
}
