package day19;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WhiteElephantTest {

    WhiteElephant whiteElephant = new WhiteElephant();
    @Test
    public void shouldDoExample() {
        assertThat( whiteElephant.calc(5), is(3));
    }

    @Test
    public void shouldDoInput() {
        System.out.println(whiteElephant.calc(3001330));
    }

    @Test
    public void shouldCalcOpposite() {
        assertThat(whiteElephant.opposite(0,5), is(2));
        assertThat(whiteElephant.opposite(1,5), is(3));
        assertThat(whiteElephant.opposite(2,5), is(4));
        assertThat(whiteElephant.opposite(3,5), is(0));
        assertThat(whiteElephant.opposite(4,5), is(1));
    }

    @Test
    public void shouldCalcOppositeEven() {
        assertThat(whiteElephant.opposite(0,4), is(2));
        assertThat(whiteElephant.opposite(1,4), is(3));
        assertThat(whiteElephant.opposite(2,4), is(0));
        assertThat(whiteElephant.opposite(3,4), is(1));
    }

    @Test
    public void shouldDoExample2() {
        assertThat(whiteElephant.calc2(13), is(1));
    }

    @Test
    public void shouldDoExample2f() {
        assertThat(whiteElephant.calc6(5), is(1));
    }

    @Test
    public void shouldDoExample2g() {
        assertThat(whiteElephant.calc6(4), is(0));
    }

    @Test
    public void shouldDoExample2h() {
        assertThat(whiteElephant.calc6(20), is(7));
    }

    @Test
    public void shouldDoExample2i() {
        for (int i = 1;i<20;i++) {
            System.out.println("Trying " + i);
            assertEquals(whiteElephant.calc3(i), whiteElephant.calc6(i));
        }
    }

    @Test
    public void shouldDoInput2f() {
        assertThat(whiteElephant.calc6(3001330), is(1));
    }

    @Test
    public void shouldDoExample2b() {
        assertThat(whiteElephant.calc3(5), is(1));
    }

    @Test
    public void shouldDoExample2c() {
        whiteElephant.calc2(50);
    }

    @Test
    public void shouldDoExample2d() {
        whiteElephant.calc3(21);
    }

    @Test
    public void shouldDoExample2e() {
        whiteElephant.calc4(20);
    }

    @Test
    public void shouldDoInput2() {
        System.out.println(whiteElephant.calc2(3001330));
    }

    @Test
    public void shouldDoInput2b() {
        for (int i = 1;i<100;i++) {
            int res = whiteElephant.calc3(i) + 1;
            System.out.println(i + " - " + res + " - " +Integer.toString(res, 2));
        }
    }
}
