package edu.baylor.ecs.si.Module3;

public enum BicycleColor {
    RED(1),
    BLUE(2),
    GREEN(3),
    YELLOW(4),
    BLACK(5),
    WHITE(6);

    private final int identifier;

    BicycleColor(int identifier) {
        this.identifier = identifier;
    }

    public int getIdentifier() {
        return identifier;
    }
}
