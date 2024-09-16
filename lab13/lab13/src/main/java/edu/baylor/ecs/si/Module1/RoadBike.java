package edu.baylor.ecs.si.Module1;

public class RoadBike extends Bicycle {
    private int tireWidth;

    public RoadBike(int startCadence, int startSpeed, int startGear, int tireWidth) {
        super(startCadence, startSpeed, startGear);
        this.setTireWidth(tireWidth);
    }

    public int getTireWidth() {
        return tireWidth;
    }

    public void setTireWidth(int tireWidth) {
        this.tireWidth = tireWidth;
    }

    public void printDescription(){
        System.out.println("\nRoadBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + " with this tire width " + this.tireWidth);
    }


    public void visit(BasicService service){
        service.accept(this);
    }
    public void visit(RoadBikeService service){
        service.accept(this);
    }
    public void visit(MountainBikeService service){
        service.accept(this);
    }
}
