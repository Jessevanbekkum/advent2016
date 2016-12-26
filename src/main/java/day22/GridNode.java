package day22;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridNode {
    boolean pair;
    int size;
    int x;
    int y;
    int used;
    int avail;
    int use;
    public final static Pattern parse =
            Pattern.compile("/dev/grid/node-x(\\d+)-y(\\d+)\\s*(\\d+)T\\s*(\\d+)T\\s*(\\d+)T\\s*(\\d+)%");
    GridNode(String line){
        Matcher matcher = parse.matcher(line);
        matcher.find();

        x = Integer.parseInt(matcher.group(1));
        y = Integer.parseInt(matcher.group(2));
        size = Integer.parseInt(matcher.group(3));
        used = Integer.parseInt(matcher.group(4));
        avail = Integer.parseInt(matcher.group(5));
        use = Integer.parseInt(matcher.group(6));
    }

    @Override
    public String toString() {
        if (avail > 70) {
            return "_";
        }
//        return String.format(" %3d/%3d", used, size);
        return pair ? "." : "#";
    }
}
