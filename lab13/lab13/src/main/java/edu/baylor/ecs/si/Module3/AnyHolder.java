package edu.baylor.ecs.si.Module3;

public class AnyHolder<T extends Bicycle> {
    protected T bicycle;

    public AnyHolder(T bicycle) {
        this.bicycle = bicycle;
    }
}

