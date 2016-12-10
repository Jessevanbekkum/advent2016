package day10;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class Output implements ChipSink {

    private final List<Chip> inventory = new ArrayList<>();

    private final int id;

    public Output(final int id) {
        this.id = id;
    }

    public void addChip(Chip chip) {
        inventory.add(chip);
    }

    public int getId() {
        return id;
    }

    public int getProduct() {
        return inventory.stream().mapToInt(Chip::getValue).reduce(1, (a,b) -> a * b);
    }
}
