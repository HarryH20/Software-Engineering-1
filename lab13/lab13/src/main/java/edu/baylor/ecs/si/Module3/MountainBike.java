package edu.baylor.ecs.si.Module3;


public class MountainBike extends Bicycle {
    private String suspension;
    public BicycleColor color;

    public MountainBike(int startCadence, int startSpeed, int startGear, BicycleColor color) {
        super(startCadence, startSpeed, startGear, color);

    }

    public String getSuspension() {
        return this.suspension;
    }

    public void setSuspension(String suspensionType) {
        this.suspension = suspensionType;
    }

    public void printDescription() {
        System.out.println("\nMountainBike is " + "in gear " + this.gear +
                " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed +
                ". Its color is " + this.getColor() + ". ");
    }
    public void visit(BicycleVisitor visitor) {
        visitor.accept(this);
    }

}
