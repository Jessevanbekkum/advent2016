package day19;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WhiteElephant {
    int nrOfRounds(int index, int groupSize) {
        if (index % 2 == 0 || (index == 1 && groupSize % 2 == 1)) {
            return 0;
        } else {
            if (groupSize % 2 == 0) {
                return 1 + nrOfRounds(index / 2 + 1, groupSize / 2);
            } else {
                return 1 + nrOfRounds(index / 2, groupSize / 2);
            }

        }
    }

    int calc(int groupSize) {
        int max = 0;
        int winner = 0;
        for (int index = 1; index <= groupSize; index++) {
            int nrOfRounds = nrOfRounds(index, groupSize);
            if (nrOfRounds > max) {
                max = nrOfRounds;
                winner = index;
            }
        }

        return winner;
    }

    int opposite(int position, int groupSize) {
        int i = (position + groupSize / 2) % groupSize;
        return i;
    }


    int calc2(final int groupsize) {
        int left = groupsize;
        boolean[] elves = new boolean[groupsize];
        int current = 0;
        while (left > 1) {
            if (elves[current]) {
                current++;
            }
            int opposite = (opposite(current, left) - current + groupsize) % groupsize;
            int i = (current + 1) % groupsize;
            while (opposite != 0) {
                if (!elves[i]) {
                    opposite--;
                }
                if (opposite != 0) {
                    i = (i + 1) % groupsize;
                }
            }
            elves[i] = true;
            left--;
            current = (current + 1) % groupsize;

            if (left % 1000 == 0) {
                System.out.println(left);
            }
            print(elves);
        }
        return current % groupsize;
    }

    void print(boolean[] bb) {
        for (boolean b : bb) {
            if (b) {
                System.out.print('X');
            } else {
                System.out.print('_');
            }
        }
        System.out.println();
    }

    void print(List<Integer> bb) {
        bb.stream().forEach(x -> System.out.printf("%2d", x));
        System.out.println();
    }

    int calc3(final int groupsize) {
        LinkedList<Integer> elves = new LinkedList<>();
        for (int i = 0; i < groupsize; i++) {
            elves.add(i);
        }
        int current = 0;
        while (elves.size() > 1) {
            int opposite = opposite(current, elves.size());
            if (opposite < current) {
                current--;
            }
            elves.remove(opposite);
            current = (current + 1) % groupsize;
            if (elves.size() % 1000 == 0) {
                System.out.println(elves.size());
            }
            print(elves);
        }
        return elves.get(0);
    }

    int calc4(final int groupsize) {
        LinkedList<Integer> elves = new LinkedList<>();
        for (int i = 0; i < groupsize; i++) {
            elves.add(i);
        }
        recurCalc(elves);
        return 0;
    }

    int recurCalc(List<Integer> elves) {
        int current = 0;
        int opposite = opposite(current, elves.size());

        Iterator<Integer> iterator = elves.iterator();
        int counter = 0;
        int kill = 1;
        while (iterator.hasNext()) {
            iterator.next();
            if (counter >= opposite) {
                if (kill % 3 != 0) {
                    iterator.remove();
                }
                kill++;
            }
            counter++;
        }
        print(elves);
        return 0;
    }

    int calc6(final int groupsize) {
        LinkedList<Integer> elves = new LinkedList<>();
        for (int i = 0; i < groupsize; i++) {
            elves.add(i);
        }
        return remove23(elves);
    }

    int remove23(List<Integer> elves) {
        int first = opposite(0, elves.size());
        LinkedList<Integer> right = new LinkedList<>(elves);

        for (int i = 0; i < first; i++) {
            right.add(right.removeFirst());
        }
        int kill ;

        if (elves.size() %2 ==0) {
            kill = 1 ;
        }
        else {
            kill = 2;
        }
        while (right.size() > 1) {
            if (kill % 3 == 0) {
                right.add(right.removeFirst());
            } else {
                right.removeFirst();
            }
            kill++;
//            print(right);
        }
        return right.get(0);
    }
}
