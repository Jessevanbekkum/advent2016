package day16;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DragonTest {
    @Test
    public void doExample() {
        Dragon dragon = new Dragon(20, "10000");
        dragon.dragonCurve();
        assertEquals("10000011110010000111", dragon.toString());
    }


    @Test
    public void doExample1() {
        Dragon dragon = new Dragon(3, "1");
        dragon.dragonCurve();
        assertEquals("100", dragon.toString());
    }

    @Test
    public void doExample2() {
        Dragon dragon = new Dragon(3, "0");
        dragon.dragonCurve();
        assertEquals("001", dragon.toString());
    }

    @Test
    public void doExample3() {
        Dragon dragon = new Dragon(11, "11111");
        dragon.dragonCurve();
        assertEquals("11111000000", dragon.toString());
    }

    @Test
    public void doExample4() {
        Dragon dragon = new Dragon(25, "111100001010");
        dragon.dragonCurve();
        assertEquals("1111000010100101011110000", dragon.toString());
    }


    @Test
    public void doChecksum() {
        Dragon dragon = new Dragon(15, "11010001");
        String checksum = dragon.checksum(Dragon.fromString("110010110100"), 12);

        assertEquals("100", checksum);
    }

    @Test
    public void doExample5() {
        Dragon dragon = new Dragon(20, "10000");
        dragon.dragonCurve();
        assertEquals("01100", dragon.checksum());
    }

    @Test
    public void doInput() {
        Dragon dragon = new Dragon(272, "10001001100000001");
        dragon.dragonCurve();
        System.out.println(dragon.checksum());
    }

    @Test
    public void doInput2() {
        Dragon dragon = new Dragon(35651584, "10001001100000001");
        dragon.dragonCurve();
        System.out.println(dragon.checksum());
    }
}
