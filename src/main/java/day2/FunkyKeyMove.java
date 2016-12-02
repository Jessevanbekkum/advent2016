package day2;

import com.google.common.base.Function;

public enum FunkyKeyMove {
    UP(FunkyKeyMove::doUp),
    DOWN(FunkyKeyMove::doDown),
    LEFT(FunkyKeyMove::doLeft),
    RIGHT(FunkyKeyMove::doRight);

    private static Character doUp(final Character c) {
        switch (c) {
            case '3':
                return '1';
            case '6':
                return '2';
            case '7':
                return '3';
            case '8':
                return '4';
            case 'A':
                return '6';
            case 'B':
                return '7';
            case 'C':
                return '8';
            case 'D':
                return 'B';
            default:
                return c;
        }
    }

    private static Character doDown(final Character c) {
        switch (c) {
            case '1':
                return '3';
            case '2':
                return '6';
            case '3':
                return '7';
            case '4':
                return '8';
            case '6':
                return 'A';
            case '7':
                return 'B';
            case '8':
                return 'C';
            case 'B':
                return 'D';
            default:
                return c;
        }
    }
    private static Character doLeft(final Character c) {
        switch (c) {
            case '3':
                return '2';
            case '4':
                return '3';
            case '6':
                return '5';
            case '7':
                return '6';
            case '8':
                return '7';
            case '9':
                return '8';
            case 'B':
                return 'A';
            case 'C':
                return 'B';
            default:
                return c;
        }
    }
    private static Character doRight(final Character c) {
        switch (c) {
            case '2':
                return '3';
            case '3':
                return '4';
            case '5':
                return '6';
            case '6':
                return '7';
            case '7':
                return '8';
            case '8':
                return '9';
            case 'A':
                return 'B';
            case 'B':
                return 'C';
            default:
                return c;
        }
    }
    public final Function<Character, Character> move;

    FunkyKeyMove(final Function<Character, Character> move) {
        this.move = move;
    }
}
