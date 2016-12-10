package day10;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Factory {

    Map<Integer, Robot> robots = new HashMap<>();
    Map<Integer, Output> outputs = new HashMap<>();

    private final Pattern valueGoes = Pattern.compile("value (\\d+) goes to bot (\\d+)");
    private final Pattern distribute = Pattern.compile("bot (\\d+) gives low to (bot|output) (\\d+) and high to (bot|output) (\\d+)");

    public void parseLine(String s) {
        Matcher matcher = applyValueInstruction(s);

        applyDistributionInstruction(s);
    }

    private void applyDistributionInstruction(final String s) {
        Matcher distMatcher = distribute.matcher(s);
        if (distMatcher.find()) {
            int botSourceId = Integer.parseInt(distMatcher.group(1));
            String typeLow = distMatcher.group(2);
            int lowId = Integer.parseInt(distMatcher.group(3));
            String typeHigh = distMatcher.group(4);
            int highId = Integer.parseInt(distMatcher.group(5));
            ChipSink low = save(typeLow, lowId);
            ChipSink high = save(typeHigh, highId);

            robots.putIfAbsent(botSourceId, new Robot(botSourceId));

            Robot robotSource = robots.get(botSourceId);
            robotSource.setLow(low);
            robotSource.setHigh(high);
        }
    }

    private Matcher applyValueInstruction(final String s) {
        Matcher matcher = valueGoes.matcher(s);
        if (matcher.find()) {
            Chip chip = new Chip(Integer.parseInt(matcher.group(1)));
            int id = Integer.parseInt(matcher.group(2));
            robots.putIfAbsent(id, new Robot(id));
            robots.get(id).addChip(chip);
        }
        return matcher;
    }

    private ChipSink save(final String type, final int id) {
        if (type.equals("bot")) {
            robots.putIfAbsent(id, new Robot(id));
            return robots.get(id);
        } else {
            outputs.putIfAbsent(id, new Output(id));
            return outputs.get(id);
        }
    }

    public int findDecider(int a, int b) {
        return robots.values().stream().filter(rb -> rb.isDecider(a, b)).findFirst().get().id;
    }

    public void distribute() {
        boolean dist = true;
        while (dist) {
            dist = robots.values().stream().anyMatch(Robot::distribute);
        }
    }
}
