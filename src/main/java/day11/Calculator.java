package day11;

import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Calculator {

    private int minDistance = Integer.MAX_VALUE;

    public void calculateDistance(Node start) {
        PriorityQueue<Node> work = new PriorityQueue<>();
        work.add(start);
        int counter = 0;
        while (!work.isEmpty()) {
            Node current = work.poll();

            if (counter % 10000 == 0) {
                System.out.println(current.getDistance());
                System.out.println(work.size());
            }
            counter++;
            if (current.isFinal()) {
                System.out.println(current.getDistance());
                minDistance = Math.min(current.getDistance(), minDistance);
                break;
            }
            else {
                work.addAll(
                        current.generateNeighbours().stream()
                                .filter(this::isCloserThanMin)
                                .collect(Collectors.toSet()));
            }
        }
    }

    private boolean isCloserThanMin(Node node) {
        return node.getDistance() < minDistance;
    }
}
