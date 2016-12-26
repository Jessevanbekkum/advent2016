package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Computer {

    private Map<String, Integer> memory = new HashMap<>();
    private int pointer;
    private List<String> instructions;
    private Pattern line = Pattern.compile("(\\w{3}) (\\S+) ?(\\S+)? ?(\\S+)?");

    private boolean[] toggles;
    boolean toggling = false;

    public Computer(List<String> instructions, Map<String, Integer> memory) {
        this.memory.putAll(memory);
        this.instructions = instructions;
        toggles = new boolean[instructions.size()];
        pointer = 0;

    }



//    List<String> improve(List<String> strings) {
//
//    }

    public int calculate() {
        while (pointer < instructions.size()) {
            executeLine(instructions.get(pointer));
        }
        return memory.get("a");
    }

    void copy(String value, String destination) {
        if (!toggling && toggles[pointer]) {
            toggling = true;
            jmp(value, destination);
            return;
        }
        toggling = false;
        int resolvedValue = parseValue(value);
        memory.put(destination, resolvedValue);
    }

    void inc(String register) {
        if (!toggling && toggles[pointer]) {
            toggling = true;
            dec(register);
            return;
        }
        toggling = false;
        memory.put(register, memory.get(register) + 1);
    }

    void dec(String register) {
        if (!toggling && toggles[pointer]) {
            toggling = true;
            inc(register);
            return;
        }
        toggling = false;
        memory.put(register, memory.get(register) - 1);
    }

    void tgl(String register) {
        System.out.println("tgl " + parseValue(register));
        if (!toggling && toggles[pointer]) {
            toggling = true;
            inc(register);
            return;
        }
        toggling = false;

        int toBeToggled = pointer + parseValue(register);
        if (toBeToggled < toggles.length) {
            toggles[toBeToggled] = !toggles[toBeToggled];
        }
    }

    private void jmp(String condition, String delta) {
        if (!toggling && toggles[pointer]) {
            toggling = true;
            copy(condition, delta);
            pointer++;
            return;
        }
        toggling = false;
        int value = parseValue(condition);
        if (value != 0) {
            pointer += parseValue(delta);
        } else {
            pointer++;
        }
    }

    int parseValue(String s) {
        if (s.equals("a") || s.equals("b") || s.equals("c") || s.equals("d")) {
            try {
                return memory.get(s);
            }
            catch (NullPointerException npe){
                System.out.println(npe);
            }
        }
        return Integer.parseInt(s);
    }


    void executeLine(String s) {
        System.out.println(s);
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
                jmp(op1, op2);
                break;
            case "cpy":
                op2 = matcher.group(3);
                copy(op1, op2);
                pointer++;
                break;
            case "tgl":
                tgl(op1);
                pointer++;
                break;
            case "mul":
                op2 = matcher.group(3);
                String op3 = matcher.group(4);
                mul(op1, op2, op3);
                pointer++;
                break;

        }
    }

    private void mul(final String op1, final String as, String bs) {
        int a = parseValue(as);
        int b = parseValue(bs);
        memory.put(op1, a * b);
    }

    public int readRegister(String register) {
        return memory.get(register);
    }


}
