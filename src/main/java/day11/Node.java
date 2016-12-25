package day11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Node implements Comparable<Node> {
    private final NodeMemory memory = NodeMemory.INSTANCE;
    private final Map<Integer, Set<Component>> inventory;
    private final Map<Integer, List<Component>> compressed;
    private final int score;

    private int elevatorPosition;

    private int distance;

    public Node(final Map<Integer, Set<Component>> inventory, final int elevatorPosition, int distance) {
        this.inventory = inventory;
        this.elevatorPosition = elevatorPosition;
        this.distance = distance;
        this.score = inventory.get(1).size() * 3 + inventory.get(2).size() * 2 + inventory.get(3).size();
        compressed = getCompressed(inventory);
    }

    private Map<Integer, List<Component>> getCompressed(final Map<Integer, Set<Component>> inventory) {
        Map<Integer, List<Component>> result = new HashMap<>();
        for (Map.Entry<Integer, Set<Component>> floor : inventory.entrySet()) {
            List<Component> list = new ArrayList<>();
            list.addAll(floor.getValue());
            Collections.sort(list);

            ArrayList<Component> floorList = new ArrayList<>();
            result.put(floor.getKey(), floorList);
            for (int i = 0; i < list.size() - 1; i++) {
                if (Objects.equals(list.get(i).getElement(), list.get(i + 1).getElement())) {
                    floorList.add(new Component("", Component.Type.Pair));
                    i++;
                }
                else {
                    floorList.add(list.get(i));
                }
            }
        }
        return result;
    }

    public boolean isFinal() {
        return IntStream.range(1, 4).allMatch(i -> inventory.get(i).isEmpty());
    }

    List<Node> generateNeighbours() {

        Set<Set<Component>> sets = getMovableSets();

        List<Node> moveUp = sets.stream()
                .map(set -> generateNeighbour(set, 1))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        List<Node> moveDown = sets.stream()
                .map(set -> generateNeighbour(set, -1))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        moveUp.addAll(moveDown);
        return moveUp;
    }

    private Optional<Node> generateNeighbour(final Set<Component> set, int move) {
        int newElevator = elevatorPosition + move;
        if (newElevator == 5 || newElevator == 0) {
            return Optional.empty();
        }
        final Map<Integer, Set<Component>> newInventory =
                calcNewInventory(elevatorPosition, newElevator, set);

        Node newNode = new Node(newInventory, newElevator, distance + 1);
        if (!newNode.isValid()) {
            return Optional.empty();
        }
        if (memory.allNodes.contains(newNode)) {
            return Optional.empty();
        }

        memory.allNodes.add(newNode);
        return Optional.of(newNode);

    }

    private Map<Integer, Set<Component>> calcNewInventory(final int startFloor, final int endFloor, final Set<Component> set) {
        Map<Integer, Set<Component>> result = new HashMap<>();
        for (Map.Entry<Integer, Set<Component>> c : inventory.entrySet()) {
            result.put(c.getKey(), Sets.newHashSet(c.getValue()));
        }

        result.get(startFloor).removeAll(set);
        result.get(endFloor).addAll(set);
        return result;
    }

    private Set<Set<Component>> getMovableSets() {
        Set<Set<Component>> powerSet = powerSet(inventory.get(elevatorPosition));

        Set<Set<Component>> moves = powerSet.stream()
                .collect(Collectors.toSet());

        return removePairs(moves);
    }

    private Set<Set<Component>> removePairs(final Set<Set<Component>> moves) {
        boolean havePair = false;
        Set<Set<Component>> redundant = new HashSet<>();
        for (Set<Component> move : moves) {
            if (move.size() == 1) {
                continue;
            }
            List<Component> move2 = Lists.newArrayList(move);
            if (move2.get(0).getElement().equals(move2.get(1).getElement())) {
                if (havePair) {
                    redundant.add(move);
                }
                havePair = true;
            }
        }
        return moves.stream()
                .filter(move -> containsNoRedundantElements(move, redundant))
                .collect(Collectors.toSet());
    }

    private boolean containsNoRedundantElements(Set<Component> move, Set<Set<Component>> redundant) {
        return redundant.stream().allMatch(set -> Sets.intersection(move, set).isEmpty());
    }

    private static Set<Set<Component>> powerSet(Set<Component> originalSet) {
        Set<Set<Component>> sets = new HashSet<>();

        originalSet.forEach(c1 ->
                originalSet.forEach(c2 ->
                        sets.add(Sets.newHashSet(c1, c2))));

        return sets;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            sb.append("F").append(i).append(" ");
            if (i == elevatorPosition) {
                sb.append("E ");
            } else {
                sb.append(". ");
            }

            String currentFloor = inventory.get(i).stream().map(Component::toString).collect(Collectors.joining(" "));

            sb.append(currentFloor);
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean isValid() {
        return inventory.values().stream().allMatch(this::isFloorSafe);
    }

    private boolean isFloorSafe(Set<Component> comps) {
        return comps.stream().allMatch(comp -> comp.isSafe(comps));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node node = (Node) o;
        return elevatorPosition == node.elevatorPosition &&
                Objects.equals(compressed, node.compressed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory, elevatorPosition);
    }

    public Integer getDistance() {
        return distance;
    }

    @Override
    public int compareTo(final Node o) {
        return ComparisonChain.start().compare(distance, o.distance).compare(score, o.score).result();


    }
}
