package edu.baylor.ecs.csi3471.HasslerHarrison.module1;


class StringExercise2 {
    public String[] splitter(String string1){
        string1.replaceAll("\"", "");

        return string1.split(", ");

    }

    public static void main(String[] args) {
        StringExercise2 line = new StringExercise2();
        String[] words = line.splitter(args[0]);
        for (String word : words) {
            System.out.println(word);
        }
    }



}
