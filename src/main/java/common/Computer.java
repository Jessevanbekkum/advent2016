package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Computer {

    private Map<String, Integer> memory = new HashMap<>();
    private int pointer;
    private List<String> instructions;
    private Pattern line = Pattern.compile("(\\w{3}) (\\S+) ?(\\S+)?");

    private List<Integer> toggles = new ArrayList<>();

    {
        memory.put("a", 0);
        memory.put("b", 0);
        memory.put("c", 1);
        memory.put("d", 0);
    }

    public Computer(List<String> instructions) {
        this.instructions = instructions;
        pointer = 0;

    }

    public int calculate() {
        while (pointer < instructions.size()) {
            executeLine(instructions.get(pointer));
        }
        return memory.get("a");
    }

    void copy(String value, String destination) {
        int resolvedValue = parseValue(value);
        memory.put(destination, resolvedValue);
    }

    void inc(String register) {
        memory.put(register, memory.get(register) + 1);
    }

    void dec(String register) {
        memory.put(register, memory.get(register) - 1);
    }

    private void jmp(String condition, Integer delta) {
        int value = parseValue(condition);
        if (value != 0) {
            pointer += delta;
        } else {
            pointer++;
        }
    }

    int parseValue(String s) {
        if (s.equals("a") || s.equals("b") || s.equals("c") || s.equals("d")) {
            return memory.get(s);
        }
        return Integer.parseInt(s);
    }


    void executeLine(String s) {
        Matcher matcher = line.matcher(s);

        matcher.find();

        String command = matcher.group(1);
        String op1 = matcher.group(2);
        String op2;
        switch (command) {
            case "dec":
                dec(op1);
                pointer++;
                break;
            case "inc":
                inc(op1);
                pointer++;

                break;
            case "jnz":
                op2 = matcher.group(3);
                jmp(op1, Integer.parseInt(op2));
                break;
            case "cpy":
                op2 = matcher.group(3);
                copy(op1, op2);
                pointer++;

        }
    }

    public int readRegister(String register) {
        return memory.get(register);
    }


}
