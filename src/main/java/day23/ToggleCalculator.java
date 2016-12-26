package day23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Computer;

public class ToggleCalculator {


    static Integer calculate(List<String> strings, Map<String, Integer> memory) {
        Map<String, Integer> initial = new HashMap<>();
        initial.put("a", 0);
        initial.put("b", 0);
        initial.put("c", 0);
        initial.put("d", 0);

        initial.putAll(memory);
        Computer computer = new Computer(strings, initial);
        computer.calculate();
        return computer.readRegister("a");
    }
}
