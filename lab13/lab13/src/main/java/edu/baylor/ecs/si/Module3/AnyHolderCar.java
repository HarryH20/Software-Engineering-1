package edu.baylor.ecs.si.Module3;

import java.util.ArrayList;
import java.util.List;

public class AnyHolderCar {
    public List<AnyHolder<? extends Bicycle>> carHolders;

    public AnyHolderCar() {
        this.carHolders = new ArrayList<>(4);
    }

    public <T extends Bicycle> void accept(T bicycle) {
        carHolders.add(new AnyHolder<>(bicycle));
    }
}
