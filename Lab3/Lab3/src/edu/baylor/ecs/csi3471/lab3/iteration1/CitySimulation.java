package edu.baylor.ecs.csi3471.lab3.iteration1;
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
                people++;
            }
            else{
                Student student = new Student();
                students++;
            }
        }
        System.out.println("Number of people " + people);
        System.out.println("Number of students " + students);




    }
}
