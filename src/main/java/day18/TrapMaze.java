package day18;

public class TrapMaze {

    boolean[][] maze;


    private boolean isLeftSafe(int x, int y) {
        if (x == 0 || y == 0) {
            return true;
        }
        return maze[y - 1][x - 1];
    }

    private boolean isRightSafe(int x, int y) {
        if (x == maze[0].length - 1 || y == 0) {
            return true;
        }
        return maze[y - 1][x + 1];
    }

    public TrapMaze(String input, int length) {
        maze = new boolean[length][input.length()];
        maze[0] = parse(input);
        calculate();


    }

    private void calculate() {
        for (int y = 1; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                maze[y][x] = isSafe(x, y);
            }
        }
    }

    private boolean isSafe(int x, int y) {

        return isLeftSafe(x, y) == isRightSafe(x, y);
    }

    public boolean[] parse(String s) {
        boolean[] result = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = s.charAt(i) == '.';
        }
        return result;
    }

    public int countSafe() {
        int count = 0;
        for (boolean[] row : maze) {
            for (boolean cell : row) {
                count = cell ? count + 1 : count;
            }
        }
        return count;
    }

    public void print() {
        for (final boolean[] aMaze : maze) {
            for (final boolean anAMaze : aMaze) {
                if (anAMaze) {
                    System.out.printf(". ");
                } else {
                    System.out.print("^ ");
                }
            }
            System.out.println();
        }
    }
}
