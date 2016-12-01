package day1;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Puzzle1 {
    String input = "L3, R1, L4, L1, L2, R4, L3, L3, R2, R3, L5, R1, R3, L4, L1, L2, R2, R1, L4, L4, R2, L5, R3, R2, R1, L1, L2, R2, R2, L1, L1, R2, R1, L3, " +
            "L5, R4, L3, R3, R3, L5, L190, L4, R4, R51, L4, R5, R5, R2, L1, L3, R1, R4, L3, R1, R3, L5, L4, R2, R5, R2, L1, L5, L1, L1, R78, L3, R2, L3, R5, " +
            "L2, R2, R4, L1, L4, R1, R185, R3, L4, L1, L1, L3, R4, L4, L1, R5, L5, L1, R5, L1, R2, L5, L2, R4, R3, L2, R3, R1, L3, L5, L4, R3, L2, L4, L5, " +
            "L4, R1, L1, R5, L2, R4, R2, R3, L1, L1, L4, L3, R4, L3, L5, R2, L5, L1, L1, R2, R3, L5, L3, L2, L1, L4, R4, R4, L2, R3, R1, L2, R1, L2, L2, R3, " +
            "R3, L1, R4, L5, L3, R4, R4, R1, L2, L5, L3, R1, R4, L2, R5, R4, R2, L5, L3, R4, R1, L1, R5, L3, R1, R5, L2, R1, L5, L2, R2, L2, L3, R3, R3, R1";

    @Test
    public void example1() {
        int calculate = calculate("R2, L3");
        System.out.println(calculate);
    }

    @Test
    public void example2() {
        int calculate = calculate("R5, L5, R5, R3");
        System.out.println(calculate);
    }
    @Test
    public void doStuff() {
        int calculate = calculate(input);
        System.out.println(calculate);

        System.out.println();
    }

    private int calculate(String moves) {
        String[] split = moves.split(", ");

        List<String> strings = Arrays.asList(split);

        Grid grid = new Grid();
        strings.forEach(m -> read(m, grid));

        System.out.println(grid.getFirstDouble());
        return grid.getMinDistance();
    }

    private void read(String move, Grid grid) {
        int nrOfSteps = Integer.valueOf(move.substring(1));
        if (move.startsWith("R")) {
            grid.doRight(nrOfSteps);
        }
        if (move.startsWith("L")) {
            grid.doLeft(nrOfSteps);
        }

    }
}
