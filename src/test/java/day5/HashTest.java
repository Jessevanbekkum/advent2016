package day5;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashTest {
    @Test
    public void shouldDoExample() throws NoSuchAlgorithmException {
        Hash hash = new Hash();
        String abc = hash.loop("abc");

        assertEquals(abc, "18f47a30");
    }

    @Test
    public void shouldDoInput() throws NoSuchAlgorithmException {
        Hash hash = new Hash();
        String abc = hash.loop("ffykfhsq");

        System.out.println(abc);
    }

    @Test
    public void shouldDoExample2() throws NoSuchAlgorithmException {
        Hash hash = new Hash();
        String abc = hash.door2("abc");

        assertEquals(abc, "05ace8e3");
    }

    @Test
    public void shouldDoInput2() throws NoSuchAlgorithmException {
        Hash hash = new Hash();
        String abc = hash.door2("ffykfhsq");

        System.out.println(abc);
    }

    @Test
    public void shouldDoExample3() throws NoSuchAlgorithmException {
        Hash hash = new Hash();
        String abc =  hash.door2Fast("abc");
        assertEquals(abc, "05ace8e3");

    }

    @Test
    public void shouldDoInput3() throws NoSuchAlgorithmException {
        Hash hash = new Hash();
        String abc = hash.door2Fast("ffykfhsq");

        System.out.println(abc);
    }
}
