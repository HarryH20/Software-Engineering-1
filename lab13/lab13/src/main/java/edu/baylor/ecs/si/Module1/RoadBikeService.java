package edu.baylor.ecs.si.Module1;

import edu.baylor.ecs.si.Module1.BasicService;
import edu.baylor.ecs.si.Module1.Bicycle;
import edu.baylor.ecs.si.Module1.MountainBikeService;
import edu.baylor.ecs.si.Module1.RoadBike;

public class RoadBikeService extends BasicService {
    public void accept(RoadBike roadBike){
        System.out.println("Fixing RoadBike");
    }
    public void accept(MountainBikeService mBike){
        System.out.println("Fixing MountainBike");
    }
    public void accept(Bicycle Bike){
        System.out.println("Fixing Bike");
    }

}
