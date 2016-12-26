package day12;

import java.util.Collections;
import java.util.List;

import common.Computer;

public class Computer12 {
    private final Computer computer;

    public Computer12(final List<String> strings) {
        this.computer = new Computer(strings, Collections.emptyMap());
    }

    public int calculate() {
        computer.calculate();
        return computer.readRegister("a");
    }
}
