package edu.baylor.ecs.si.Module3;


public class Bicycle {
    public int cadence;
    public int gear;
    public int speed;
    public BicycleColor color;

    public Bicycle(int startCadence, int startSpeed, int startGear, BicycleColor color) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
        this.color = color;
    }

    public void setCadence(int newValue) {
        cadence = newValue;
    }

    public void setGear(int newValue) {
        gear = newValue;
    }

    public void applyBrake(int decrement) {
        speed -= decrement;
    }

    public void speedUp(int increment) {
        speed += increment;
    }

    public void printDescription() {
        System.out.println("\nBike is " + "in gear " + this.gear +
                " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed +
                ". Its color is " + this.color + ". ");
    }
    public void visit(BicycleVisitor visitor) {
        visitor.accept(this);
    }
    public BicycleColor getColor() {
        return color;
    }

    public void setColor(BicycleColor color) {
        this.color = color;
    }

}
