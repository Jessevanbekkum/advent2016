package day25;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Computer;

public class Star {
    Star(List<String> strings, int a) {
        Map<String, Integer> initial = new HashMap<>();
        initial.put("a", a);
        initial.put("b", 0);
        initial.put("c", 0);
        initial.put("d", 0);

        Computer computer = new Computer(strings, initial);
        computer.calculate();

    }


}
