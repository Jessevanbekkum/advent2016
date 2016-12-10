package day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Robot implements ChipSink{

    final int id;
    final List<Chip> inventory = new ArrayList<>();
    private ChipSink low;
    private ChipSink high;

    private int lowRule;
    private int highRule;

    public Robot(final int id) {
        this.id = id;
    }

    public void addChip(final Chip chip) {
        inventory.add(chip);
    }

    public void setLow(ChipSink robot){
        this.low = robot;
    }

    public void setHigh(ChipSink robot){
        this.high = robot;
    }

    public boolean distribute() {
        if (inventory.size()<2){
            return false;
        }

        if (inventory.size() ==2){
            Collections.sort(inventory);
            this.lowRule = inventory.get(0).value;
            this.highRule = inventory.get(1).value;
            this.low.addChip(inventory.get(0));
            this.high.addChip(inventory.get(1));
            inventory.clear();
            return true;
        }

        throw new RuntimeException("WFT");
    }

    public boolean isDecider(int a, int b) {
        return lowRule == a && highRule == b;
    }
}
