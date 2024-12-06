public class IntVector {
    public int x, y;

    public IntVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVector turn(int amount) {
        int xClone = x;
        int yClone = y;
        for (int i = 0; i < amount; i++) {
            int tempX = x;
            xClone = y;
            yClone = -tempX;
        }
        return new IntVector(xClone, yClone);
    }

    public int distanceTo(IntVector other) {
        int xDiff = other.x - x;
        int yDiff = other.y - y;
        if (xDiff != 0) return Math.abs(xDiff);
        else return yDiff;
    }

    public IntVector travelDirection(IntVector other) {
        int xDiff = other.x - x;
        int yDiff = other.y - y;
        if (xDiff != 0) {
            if (xDiff < 0) return new IntVector(-1, 0);
            else return new IntVector(1, 0);
        }
        else if (yDiff < 0) return new IntVector(0, -1);
        else return new IntVector(0, 1);
    }

    public String toString() {
        return x + ":" + y;
    }

    public IntVector mult(int dist) {
        return new IntVector(x * dist, y * dist);
    }

    public IntVector add(IntVector other) {
        return new IntVector(x + other.x, y + other.y);
    }
}
