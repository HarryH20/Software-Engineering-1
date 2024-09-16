package edu.baylor.ecs.si.Module3;

public class TestBike2 {
    public static void main(String[] args) {
        MountainBike mountainBike = new MountainBike(1, 2, 3, BicycleColor.RED);

        RoadBike roadBike = new RoadBike(4, 5, 6, BicycleColor.BLUE);

        Bicycle bicycle = new Bicycle(7, 8, 9, BicycleColor.GREEN);

        AnyHolderCar car = new AnyHolderCar();

        car.accept(mountainBike);
        car.accept(roadBike);
        car.accept(bicycle);

        System.out.println("Bicycles in the car:");
        for (AnyHolder<? extends Bicycle> holder : car.carHolders) {
            Bicycle b = holder.bicycle;
            b.printDescription();
        }
    }
}

