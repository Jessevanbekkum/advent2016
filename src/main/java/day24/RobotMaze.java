package day24;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.common.collect.Lists;

public class RobotMaze {

    private char[][] maze;
    private int max;
    private int[][] distanceTable;

    private Map<Integer, Point> points = new HashMap<>();

    class Point {

        final int x;
        final int y;

        int distance;

        void setDistance(int distance) {
            this.distance = distance;
        }

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        boolean valid() {
            return x >= 0 && y >= 0 && y < maze.length && x < maze[0].length;
        }
    }

    RobotMaze(List<String> strings) {
        this.maze = new char[strings.size()][strings.get(0).length()];
        for (int i = 0; i < maze.length; i++) {
            char[] chars = maze[i];
            for (int j = 0; j < chars.length; j++) {
                maze[i][j] = strings.get(i).charAt(j);
            }
        }
        initialize();
        fillDistanceGraph();
        doTSP();
    }

    public int doTSP() {
        List<Integer> collect = IntStream.rangeClosed(1, max).mapToObj(i -> (i)).collect(Collectors.toList());
        List<List<Integer>> paths = generator(collect);
        paths.forEach(p -> p.add(0,0));
        Optional<List<Integer>> first = paths.stream().filter(p -> calcDistance(p) == 12).findFirst();
        OptionalInt min = paths.stream().map(this::calcDistance).mapToInt(i -> i).min();
        return min.getAsInt();
    }

    public int doTSPWithReturn() {
        List<Integer> collect = IntStream.rangeClosed(1, max).mapToObj(i -> (i)).collect(Collectors.toList());
        List<List<Integer>> paths = generator(collect);
        paths.forEach(p -> p.add(0,0));
        paths.forEach(p -> p.add(0));
        Optional<List<Integer>> first = paths.stream().filter(p -> calcDistance(p) == 12).findFirst();
        OptionalInt min = paths.stream().map(this::calcDistance).mapToInt(i -> i).min();
        return min.getAsInt();
    }


    int calcDistance(List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            sum += distanceTable[list.get(i)][list.get(i + 1)];

        }
        return sum;
    }

    void initialize() {
        for (int i = 0; i < maze.length; i++) {
            char[] chars = maze[i];
            for (int j = 0; j < chars.length; j++) {
                char aChar = chars[j];
                if (aChar == '#' || aChar == '.') {
                    continue;
                }

                int value = Character.getNumericValue(aChar);
                points.put(value, new Point(j, i));

                max = Math.max(max, value);

            }
        }

        distanceTable = new int[max + 1][max + 1];
    }

    void fillDistanceGraph() {
        for (Map.Entry<Integer, Point> x : points.entrySet()) {
            Map<Integer, Integer> distances = distanceFor(x.getValue());
            for (Integer i : distances.keySet()) {
                distanceTable[x.getKey()][i] = distances.get(i);
            }
        }
    }

    Map<Integer, Integer> distanceFor(final Point p) {
        Set<Point> visited = new HashSet<>();
        Map<Integer, Integer> result = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(p);
        visited.add(p);
        while (!queue.isEmpty()) {
            Point remove = queue.remove();
            char atPoint = getAtPoint(remove);
            if (atPoint == '#') {
                continue;
            }

            Set<Point> neighbours = getNeighbours(remove);
            neighbours.removeAll(visited);
            queue.addAll(neighbours);
            visited.addAll(neighbours);

            if (atPoint != '.') {
                result.put(Character.getNumericValue(atPoint), remove.distance);
            }
        }
        return result;
    }


    Set<Point> getNeighbours(Point p) {
        Set<Point> points = new HashSet<>();
        points.add(new Point(p.x - 1, p.y));
        points.add(new Point(p.x, p.y - 1));
        points.add(new Point(p.x + 1, p.y));
        points.add(new Point(p.x, p.y + 1));
        return points.stream()
                .map(q -> {
                    q.setDistance(p.distance + 1);
                    return q;
                })
                .filter(Point::valid).collect(Collectors.toSet());
    }

    char getAtPoint(Point p) {
        return maze[p.y][p.x];
    }

    void print() {
        for (char[] chars : maze) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    static List<List<Integer>> generator(List<Integer> source) {
        if (source.isEmpty()) {
            return Collections.singletonList(Collections.emptyList());
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Integer i : source) {

            List<Integer> others = source.stream().filter(k -> !Objects.equals(k, i)).collect(Collectors.toList());
            List<List<Integer>> generator = generator(others);
            generator.forEach(s -> {
                List<Integer> newList = Lists.newArrayList(i);
                newList.addAll(s);
                result.add(newList);
            });
        }

        return result;
    }
}
