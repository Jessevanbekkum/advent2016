package day13;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class Maze {
    private final int input;
    private final int dimX;
    private final int dimY;
    private int[][] maze;

    private Queue<Point> bfs = new LinkedList<>();

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point of(int x, int y) {
            Point point = new Point(x, y);
            return point;
        }

        Optional<Point> getUp() {
            if (y == 0) {
                return Optional.empty();
            }
            return Optional.of(of(x, y - 1));
        }

        Optional<Point> getDown() {
            if (y >= dimY - 1) {
                return Optional.empty();
            }
            return Optional.of(of(x, y + 1));
        }

        Optional<Point> getLeft() {
            if (x == 0) {
                return Optional.empty();
            }
            return Optional.of(of(x - 1, y));
        }

        Optional<Point> getRight() {
            if (x >= dimX - 1) {
                return Optional.empty();
            }
            return Optional.of(of(x + 1, y));
        }

        int distance() {
            return maze[y][x];
        }

        List<Point> getNeighbours() {
            List<Point> result = new ArrayList<>();
            getUp().ifPresent(result::add);
            getDown().ifPresent(result::add);
            getLeft().ifPresent(result::add);
            getRight().ifPresent(result::add);
            return result;
        }
    }

    public Maze(final int input, int dimX, int dimY) {
        this.input = input;
        this.dimX = dimX;
        this.dimY = dimY;
        maze = new int[dimY][dimX];
        for (int[] column : maze) {
            Arrays.fill(column, MAX_VALUE);
        }
        maze[1][1] = 0;
        bfs.add(new Point(1, 1));
    }

    public int calculateDistanceTo(int x, int y) {
        while (maze[y][x] == MAX_VALUE && !bfs.isEmpty()) {
            calculate();
        }

        return maze[y][x];
    }

    public int calculatePlacesInSteps(int steps) {
        while (!bfs.isEmpty()) {
            calculate();
        }
        int sum = 0;
        for (final int[] aMaze : maze) {
            for (final int anAMaze : aMaze) {
                if (anAMaze <= 50 && anAMaze >=0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private void calculate() {
        Point current = bfs.remove();
        if (isWall(current.x, current.y)) {
            maze[current.y][current.x] = -1;
        } else {
            if (maze[current.y][current.x] == MAX_VALUE) {
                maze[current.y][current.x] = minNeighbours(current) + 1;
            }
            addNeighbours(current);
        }
    }

    private void addNeighbours(final Point current) {
        current.getNeighbours().stream().filter(this::unSeen).forEach(bfs::add);
    }


    private int minNeighbours(Point point) {
        OptionalInt min = point.getNeighbours().stream().mapToInt(Point::distance).filter(i -> i != -1).min();

        return min.isPresent() ? min.getAsInt() : MAX_VALUE;

    }

    private boolean unSeen(Point point) {
        return maze[point.y][point.x] == MAX_VALUE;
    }

    boolean bitsAreOdd(int i) {
        return Integer.bitCount(i) % 2 == 1;
    }

    public boolean isWall(int x, int y) {
        int v = x * x + 3 * x + 2 * x * y + y + y * y + input;
        return bitsAreOdd(v);
    }


    public void print() {
        for (final int[] aMaze : maze) {
            for (final int anAMaze : aMaze) {
                if (anAMaze == MAX_VALUE) {
                    System.out.printf("XX ");
                } else {
                    System.out.print(anAMaze + " ");
                }
            }
            System.out.println();
        }
    }
}
