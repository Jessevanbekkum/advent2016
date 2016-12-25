package day17;

import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import com.google.common.collect.Lists;

public class DoorMaze {

    MessageDigest digest = DigestUtils.getDigest("MD5");
    char[] directions = {'U', 'D', 'L', 'R'};
    final String input;

    DoorMaze(final String input) {

        this.input = input;

    }

    String shortestPath() {
        Queue<String> bfs = new LinkedList<>();
        bfs.add("");
        while (!bfs.isEmpty()) {
            String remove = bfs.remove();
            if (isFinish(remove)) {
                return remove;
            }
            bfs.addAll(expand(remove));
        }
        return "";
    }

    int longestPath() {
        int longest = 0;
        Stack<String> bfs = new Stack<>();
        bfs.add("");
        while (!bfs.isEmpty()) {
            String remove = bfs.pop();
            if (isFinish(remove)) {
                if (remove.length() > longest) {
                    longest = remove.length();
                    }
            } else {
                bfs.addAll(expand(remove));
            }
        }
        return longest;
    }

    private List<? extends String> expand(final String remove) {
        byte[] md5 = digest.digest((input + remove).getBytes());
        String ops = Hex.encodeHexString(md5).substring(0, 4);
        List<String> result = Lists.newArrayList();
        IntStream.range(0, 4).forEach(i -> {
            if (ops.charAt(i) > 'a' && isValid(remove + directions[i])) {
                result.add(remove + directions[i]);
            }
        });
        return result;
    }


    private boolean isFinish(String path) {
        return getPos(path) == 44;
    }

    boolean isValid(String path) {
        return getPos(path) != 0;
    }

    int getPos(String path) {
        int x = 1;
        int y = 1;
        for (char c : path.toCharArray()) {
            x += getModX(c);
            y += getModY(c);
            if (x < 1 || x > 4 || y < 1 || y > 4) {
                return 0;
            }
        }
        return x * 10 + y;
    }

    int getModX(char i) {
        if (i == 'L') {
            return -1;
        }
        if (i == 'R') {
            return 1;
        }
        return 0;
    }

    int getModY(char i) {
        if (i == 'U') {
            return -1;
        }
        if (i == 'D') {
            return 1;
        }
        return 0;
    }
}
