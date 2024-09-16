package edu.baylor.ecs.si.Module3;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private List<BicycleHolder> carHolders;

    public Car() {
        this.carHolders = new ArrayList<>(4);
    }

    // Accept different types of bicycles and select appropriate holders
    public void accept(Bicycle bicycle) {
        if (bicycle instanceof MountainBike) {
            carHolders.add(new MountainBikeHolder((MountainBike) bicycle));
        } else if (bicycle instanceof RoadBike) {
            carHolders.add(new RoadBikeHolder((RoadBike) bicycle));
        }
    }
}
