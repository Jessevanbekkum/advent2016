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
        assertThat( whiteElephant.calc(3001330), is(3));
    }

    @Test
    public void shouldCalcOpposite() {
        assertThat(whiteElephant.opposite(1,5), is(3));
        assertThat(whiteElephant.opposite(2,4), is(4));
    }
}
