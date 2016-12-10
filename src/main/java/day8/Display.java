package day8;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Display {
    Pattern rect = Pattern.compile("rect (\\d+)x(\\d+)");
    Pattern rotC = Pattern.compile("rotate column x=(\\d+) by (\\d+)");
    Pattern rotR = Pattern.compile("rotate row y=(\\d+) by (\\d+)");

    final char[][] screen;

    final int width;
    final int height;

    Display(int width, int height) {
        this.width = width;
        this.height = height;
        screen = new char[height][width];

        for (int i = 0; i < screen.length; i++) {
            Arrays.fill(screen[i], '.');
        }
    }


    public void print() {
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[i].length; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.println();
        }
    }

    public void rect(int x, int y) {
        x = Math.min(x, screen[0].length);
        y = Math.min(y, screen.length);
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                screen[i][j] = '#';
            }
        }
    }

    public void rotateRow(int y, int by) {
        char[] newRow = new char[width];
        for (int i = 0; i < width; i++) {
            newRow[(i + by) % width] = screen[y][i];
        }
        screen[y] = newRow;
    }

    public void rotateColumn(int x, int by) {
        char[] newColumn = new char[height];
        for (int i = 0; i < height; i++) {
            newColumn[(i + by) % height] = screen[i][x];
        }
        for (int i = 0; i < height; i++) {
            screen[i][x] = newColumn[i];
        }
    }

    public void command(String s) {
        applyRect(s);
        applyRotC(s);
        applyRotR(s);
    }

    private void applyRect(final String s) {
        Matcher matcher = rect.matcher(s);
        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));

            rect(x, y);
        }
    }

    private void applyRotC(final String s) {
        Matcher matcher = rotC.matcher(s);
        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group(1));
            int by = Integer.parseInt(matcher.group(2));

            rotateColumn(x, by);
        }
    }

    private void applyRotR(final String s) {
        Matcher matcher = rotR.matcher(s);
        if (matcher.matches()) {
            int y = Integer.parseInt(matcher.group(1));
            int by = Integer.parseInt(matcher.group(2));

            rotateRow(y, by);
        }
    }

    public int countLit() {
        int sum = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (screen[j][i] == '#'){
                    sum++;
                }
            }
        }

        return sum;
    }

}
