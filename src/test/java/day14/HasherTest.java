package day14;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class HasherTest {
    @Test
    public void checkExample() {
        Hasher hasher = new Hasher("abc");
        int result = 22728;
        assertThat(hasher.calc(), is(result));
    }

    @Test
    public void calculateInput1() {
        Hasher hasher = new Hasher("ahsbgdzn");
        System.out.println(hasher.calc());
    }

    @Test
    public void checkExample2() {
        Hasher hasher = new Hasher("abc", 2016);
        int result = 22551;
        assertThat(hasher.calc(), is(result));
    }

    @Test
    public void calculateInput2() {
        Hasher hasher = new Hasher("ahsbgdzn", 2016);
        System.out.println(hasher.calc());
    }

    @Test
    public void calculateInput256() {
        Hasher hasher = new Hasher("ahsbgdzn", 2016, "SHA-256");
        System.out.println(hasher.calc());
    }


    @Test
    public void shouldKeyStretch() {
        Hasher hasher = new Hasher("abc", 2016);
        assertEquals("a107ff634856bb300138cac6568c0f24", hasher.hash(0));

    }


}
