package edu.baylor.ecs.csi3471.HasslerHarrison.module1;

class StringExercise3 {
    public String httpRemover(String string1){
        return string1.replaceAll("(?i)HTTPS?", "");


    }

    public static void main(String args[]){
        StringExercise3 line = new StringExercise3();
        for (String word : args) {
            System.out.println(line.httpRemover(word));
        }

    }

}
