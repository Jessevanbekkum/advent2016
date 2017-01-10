package common;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Computer {

    private Map<String, Integer> memory = new HashMap<>();
    private int pointer;
    private List<String> code;
    private Pattern line = Pattern.compile("(\\w{3}) (\\S+) ?(\\S+)? ?(\\S+)?");

    private boolean[] toggles;
    boolean toggling = false;

    public Computer(List<String> instructions, Map<String, Integer> memory) {
        this.memory.putAll(memory);
        this.code = instructions;
        toggles = new boolean[instructions.size()];
        pointer = 0;

    }

    Map<String, String> translate = new HashMap<>();

    {
        translate.put("inc", "dec");
        translate.put("dec", "inc");
        translate.put("cpy", "jnz");
        translate.put("jnz", "cpy");
        translate.put("tgl", "inc");
    }

    public int calculate() {
        while (pointer < code.size()) {
            executeLine(code.get(pointer));
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
        int lineNumber = pointer + parseValue(register);
        if (lineNumber >= code.size()) {
            return;
        }
        String s = code.get(lineNumber);
        if (s.equals("jnz 0 0")) {
            throw new RuntimeException("oeps");
        }
        Matcher matcher = line.matcher(s);
        matcher.find();
        String command = matcher.group(1);

        String t = s.replace(command, translate.get(command));
        code.set(lineNumber, t);
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
//        System.out.println(s);
        Matcher matcher = line.matcher(s);

        matcher.find();

        String command = matcher.group(1);
        String op1 = matcher.group(2);
        String op2;
        String op3;
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
                op3 = matcher.group(4);
                mul(op1, op2, op3);
                pointer++;
                break;
            case "add":
                op2 = matcher.group(3);
                op3 = matcher.group(4);
                add(op1, op2, op3);
                pointer++;
            case "div":
                op2 = matcher.group(3);
                op3 = matcher.group(4);
                div(op1, op2, op3);
                pointer++;
            case "sub":
                op2 = matcher.group(3);
                op3 = matcher.group(4);
                sub(op1, op2, op3);
                pointer++;
            case "out":
                out(op1);
                pointer++;
        }
    }

    private void add(final String op1, final String as, final String bs) {
        int a = parseValue(as);
        int b = parseValue(bs);
        memory.put(op1, a + b);
    }


    private void div(final String op1, final String as, final String bs) {
        int a = parseValue(as);
        int b = parseValue(bs);
        memory.put(op1, a / b);
    }

    private void sub(final String op1, final String as, final String bs) {
        int a = parseValue(as);
        int b = parseValue(bs);
        memory.put(op1, a - b);
    }


    private void mul(final String op1, final String as, String bs) {
        int a = parseValue(as);
        int b = parseValue(bs);
        memory.put(op1, a * b);
    }

    public int readRegister(String register) {
        return memory.get(register);
    }

    int outCounter=0;
    int last=1;

    public void out(String s) {
        int i = parseValue(s);
        System.out.println(i);
        if (i > 1 || i < 0 || i == last) {
            throw new IllegalStateException("Bunny");
        }
        last = i;
        outCounter++;
        if (outCounter>= 12) {
            throw new UnsupportedOperationException("WHOHO");
        }
    }
}
