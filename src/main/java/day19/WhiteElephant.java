package day19;

import java.util.BitSet;

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
        if (i == 0) {
            return groupSize;
        }
        return i;
    }


}
