package day21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LettersAndHash {

    private String input;
    Pattern swapPosition = Pattern.compile("swap position (\\d+) with position (\\d+)");
    Pattern swapLetter = Pattern.compile("swap letter (.+) with letter (.+)");
    Pattern rotate = Pattern.compile("rotate (left|right) (\\d+) step[s]?");
    Pattern rotatePosition = Pattern.compile("rotate based on position of letter (.+)");
    Pattern reverse = Pattern.compile("reverse positions (.+) through (.+)");
    Pattern move = Pattern.compile("move position (.+) to position (.+)");

    List<Pattern> patterns = new ArrayList<>();

    {
        patterns.add(swapLetter);
        patterns.add(swapPosition);
        patterns.add(rotate);
        patterns.add(rotatePosition);
        patterns.add(reverse);
        patterns.add(move);
    }

    Map<Pattern, Consumer<String>> actionMapper = new HashMap<>();

    public String getInput() {
        return input;
    }

    Consumer<String> moveAction = l -> {
        Matcher matcher = move.matcher(l);
        matcher.find();
        Integer pos1 = Integer.parseInt(matcher.group(1));
        Integer pos2 = Integer.parseInt(matcher.group(2));

        StringBuilder sb = new StringBuilder(input);
        char swp = input.charAt(pos1);
        sb.replace(pos1, pos1 + 1, "");
        sb.insert(pos2, String.valueOf(swp));
        input = sb.toString();
    };

    Consumer<String> swapLetterAction = l -> {
        Matcher matcher = swapLetter.matcher(l);
        matcher.find();
        String letterA = matcher.group(1);
        String letterB = matcher.group(2);
        int indexa = input.indexOf(letterA);
        int indexb = input.indexOf(letterB);
        swapPosition(indexa, indexb);
    };

    Consumer<String> rotatePositionAction = l ->
    {
        Matcher matcher = rotatePosition.matcher(l);
        matcher.find();
        String letterA = matcher.group(1);
        int index = input.indexOf(letterA);
        int rot = index >= 4 ? index + 2 : index + 1;
        input = rotateOne(input, "right", rot);
    };

    Consumer<String> reverseAction = l ->
    {
        Matcher matcher = reverse.matcher(l);
        matcher.find();
        Integer pos1 = Integer.parseInt(matcher.group(1));
        Integer pos2 = Integer.parseInt(matcher.group(2));
        String rev = new StringBuilder(input.substring(pos1, pos2+1)).reverse().toString();
        input = input.substring(0, pos1) + rev + input.substring(pos2 +1);
    };

    Consumer<String> swapPositionAction = l -> {
        Matcher matcher = swapPosition.matcher(l);
        matcher.find();
        Integer pos1 = Integer.parseInt(matcher.group(1));
        Integer pos2 = Integer.parseInt(matcher.group(2));
        swapPosition(pos1, pos2);
    };

    private void swapPosition(final Integer pos1, final Integer pos2) {
        char[] chars = input.toCharArray();
        char swp = chars[pos1];
        chars[pos1] = chars[pos2];
        chars[pos2] = swp;
        input = String.valueOf(chars);
    }

    Consumer<String> rotateAction = l -> {
        Matcher matcher = rotate.matcher(l);
        matcher.find();
        String lr = matcher.group(1);
        int dist = Integer.parseInt(matcher.group(2));
        input = rotateOne(input, lr, dist);
    };

    {
        actionMapper.put(swapLetter, swapLetterAction);
        actionMapper.put(swapPosition, swapPositionAction);
        actionMapper.put(rotate, rotateAction);
        actionMapper.put(reverse, reverseAction);
        actionMapper.put(rotatePosition, rotatePositionAction);
        actionMapper.put(move, moveAction);
    }

    public LettersAndHash(String input) {
        this.input = input;
    }

    String rotateOne(String input, String direction, int steps) {
        if (steps == 0) {
            return input;
        }
        switch (direction) {
            case "left":
                return rotateOne(input.substring(1) + input.substring(0, 1), direction, steps - 1);
            case "right":
                return rotateOne(input.substring(input.length() - 1) + input.substring(0, input.length() - 1), direction, steps - 1);
        }
        throw new RuntimeException("Unknown direction " + direction);
    }

    public void applyLine(String line) {
//        String tmp = input;
        patterns.stream().filter(pattern -> pattern.matcher(line).matches())
                .findFirst()
                .ifPresent(pattern -> actionMapper.get(pattern).accept(line));
//        if (tmp.equals(input)) {
//            System.out.println(line + " did not work");
//        }
//        System.out.println(input);
    }



}
