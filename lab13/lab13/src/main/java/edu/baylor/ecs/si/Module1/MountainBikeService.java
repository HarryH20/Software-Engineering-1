package edu.baylor.ecs.si.Module1;

public class MountainBikeService extends BasicService {


    public void accept(RoadBike roadBike){
        System.out.println("Fixing RoadBike");
    }
    public void accept(MountainBike mBike){
        System.out.println("Fixing MountainBike");
    }
    public void accept(Bicycle Bike){
        System.out.println("Fixing Bike");
    }

}
