package day22;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Calculator {

    GridNode[][] grid = new GridNode[25][37];

    Calculator(List<String> lines) {
        lines.stream().filter(s -> Pattern
                .matches("\\/dev\\/grid.*", s))
                .map(GridNode::new).forEach(n -> grid[n.y][n.x] = n);
    }


    void print() {
        setPairs();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    int pairs() {
        setPairs();

        int pairs = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].pair) {
                    pairs++;
                }
            }
        }

        return pairs;
    }

    private void setPairs() {
        List<GridNode> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                list.add(grid[i][j]);
            }
        }
        for (GridNode a : list) {
            for (GridNode b : list) {
                if (a == b) {
                    continue;
                }
                if (a.used == 0) {
                    continue;
                }
                if (a.used > b.avail) {
                    continue;
                }
                a.pair = true;
            }
        }
    }
}
