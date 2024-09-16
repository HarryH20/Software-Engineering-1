package edu.baylor.ecs.csi3471.HasslerHarrison.module4;

public class Loop {
    public static void loop(int count) {
        if (count > 0) {
            System.out.println("Loops left: " + count);
            loop(count - 1);
        }
    }

    public static void main(String[] args) {
        loop(10);
    }
}
