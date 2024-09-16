package edu.baylor.ecs.si.Module3;

public class RoadBike extends Bicycle {
    private int tireWidth;
    public BicycleColor color;

    public RoadBike(int startCadence, int startSpeed, int startGear, BicycleColor color) {
        super(startCadence, startSpeed, startGear, color);
    }

    public int getTireWidth() {
        return tireWidth;
    }

    public void setTireWidth(int tireWidth) {
        this.tireWidth = tireWidth;
    }

    public void printDescription() {
        System.out.println("\nRoadBike is " + "in gear " + this.gear +
                " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed +
                ". Its color is " + this.getColor() + ". ");
    }
    public void visit(BicycleVisitor visitor) {
        visitor.accept(this);
    }


}
