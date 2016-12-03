package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.google.common.base.Splitter;

public class Triangler {

    boolean isTriangle(List<Integer> triangle) {
        if (triangle.size() != 3) {
            return false;
        }
        Integer max = triangle.stream().max(Integer::compareTo).get();
        return triangle.stream().mapToInt(i -> i).sum() > 2 * max;
    }

    List<Integer> splitLine(String s) {
        List<String> strings = Splitter.on(' ').omitEmptyStrings().trimResults().splitToList(s);

        return strings.stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }

    int trianglesInLines(List<String> list) {
        List<List<Integer>> collect = list.stream().map(this::splitLine).collect(Collectors.toList());
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            List<Integer> col = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Integer integer = collect.get(j).get(i);
                col.add(integer);
            }
            if (this.isTriangle(col)) {
                sum++;
            }
        }

        return sum;
    }
}
