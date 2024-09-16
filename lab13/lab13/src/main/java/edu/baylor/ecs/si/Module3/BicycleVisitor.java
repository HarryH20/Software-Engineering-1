package edu.baylor.ecs.si.Module3;

public interface BicycleVisitor {
    void accept(Bicycle b);
    void accept(MountainBike b);
    void accept(RoadBike b);
}
