package edu.baylor.ecs.csi3471.HasslerHarrison.module1;

class StringExercise1 {
    public String crazyCase(String string1) {
        if ((string1.charAt(0) == 'i') || (string1.charAt(0) == 'I')) {
            return string1;
        }
        else {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < string1.length(); i++) {
                if(i % 2 ==0) {
                    result.append(Character.toUpperCase(string1.charAt(i)));
                }
                else {
                    result.append(string1.charAt(i));

                }
            }
            return result.toString();

        }

    }

    public static void main(String args[]){
        StringExercise1 line = new StringExercise1();
        for(String string: args){
            System.out.println(line.crazyCase(string));
        }
    }



}


