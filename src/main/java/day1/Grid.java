package day1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Grid {
    static List<Point> locs = new ArrayList<>();


    Direction currentDirection = Direction.NORTH;
    Point currentLocation = getPoint(0, 0);

    enum Direction {
        NORTH(p -> getPoint(p.x + 1, p.y)),
        EAST(p -> getPoint(p.x, p.y + 1)),
        SOUTH(p -> getPoint(p.x - 1, p.y)),
        WEST(p -> getPoint(p.x, p.y - 1));

        private final Function<Point, Point> move;

        Direction(final Function<Point, Point> move) {
            this.move = move;
        }
    }

    private static Point getPoint(final int x, final int y) {
        Point point = new Point(x, y);
        locs.add(point);
        return point;
    }

    public void doRight(int distance) {

        currentDirection = moveRight(currentDirection);
        currentLocation = step(distance, currentDirection, currentLocation);
    }

    private Direction moveRight(Direction direction) {
        Direction[] values = Direction.values();
        Direction newDirection = Direction.NORTH;
        for (int i = 0; i < 3; i++) {
            if (values[i] == direction) {
                newDirection = values[i + 1];
            }
        }

        return newDirection;
    }

    private Point step(int nrOfSteps, Direction direction, Point point) {
        if (nrOfSteps == 0) {
            return point;
        }
        return direction.move.apply(step(nrOfSteps - 1, direction, point));
    }

    private Direction moveLeft(Direction direction) {
        return moveRight(moveRight(moveRight(direction)));
    }

    public void doLeft(int distance) {
        currentDirection = moveLeft(currentDirection);
        currentLocation = step(distance, currentDirection, currentLocation);
    }

    public int getMinDistance() {
        return Math.abs(currentLocation.x) + Math.abs(currentLocation.y);
    }


    public Point getFirstDouble() {

        Set<Point> stuff = new HashSet<>();
        for (Point p : locs) {
            int size = stuff.size();
            stuff.add(p);
            if (size == stuff.size()) {
                return p;
            }
        }
        return null;
    }
}
