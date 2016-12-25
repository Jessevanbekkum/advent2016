package day15;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Capsule {
    private final List<Disk> disks;

    //Disc #1 has 5 positions; at time=0, it is at position 4.
    //Disc #2 has 2 positions; at time=0, it is at position 1.

    private Pattern pattern = Pattern.compile("Disc #(\\d+) has (\\d+) positions; at time=0, it is at position (\\d+).");


    static class Disk {
        final int id;
        final int maxPos;
        int begin;

        Disk(final int id, final int maxPos) {
            this.id = id;
            this.maxPos = maxPos;
        }

        static Disk makeDisk(Matcher m) {
            Disk disk = new Disk(Integer.parseInt(m.group(1)),
                    Integer.parseInt(m.group(2)));

            disk.begin = Integer.parseInt(m.group(3));
            return disk;
        }

        boolean isZero(int time) {
            return (begin + time + id) % maxPos == 0;
        }
    }

    public Capsule(List<String> disks) {
        this.disks = disks.stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .map(Disk::makeDisk)
                .collect(Collectors.toList());

    }

    int calculateTime() {
        int time = 0;
        boolean found = false;
        while (!found) {
            int curTime = time;
            found = disks.stream().allMatch(d -> d.isZero(curTime));
            time++;
        }
        return time - 1;
    }


}
