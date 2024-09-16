package edu.baylor.ecs.si.Module1;

public class MountainBike extends Bicycle {
    private String suspension;

    public MountainBike(int startCadence, int startSpeed, int startGear, String suspensionType) {
        super(startCadence, startSpeed, startGear);
        this.setSuspension(suspensionType);
    }

    public String getSuspension() {
        return this.suspension;
    }

    public void setSuspension(String suspensionType) {
        this.suspension = suspensionType;
    }

    public void printDescription(){
        System.out.println("\nMountainBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + " with this suspension " + this.suspension);
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
