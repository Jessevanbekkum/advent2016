package day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexTest {

    String s1 = "abba[mnop]qrst";
    String s2 = "abcd[bddb]xyyx";
    String s3 = "aaaa[qwer]tyui";
    String s4 = "ioxxoj[asdfgh]zxcvbn";

    @Test
    public void shouldAbba() {
        Regex regex = new Regex();
        assertTrue(regex.matchAbba(s1));
        assertTrue(regex.matchAbba(s2));
        assertFalse(regex.matchAbba(s3));
        assertTrue(regex.matchAbba(s4));
    }

    @Test
    public void shouldBrAbba() {
        Regex regex = new Regex();
        assertFalse(regex.matchBrAbba(s1));
        assertTrue(regex.matchBrAbba(s2));
        assertFalse(regex.matchBrAbba(s3));
        assertFalse(regex.matchBrAbba(s4));
    }

    @Test
    public void shouldBrAbba2() {
        Regex regex = new Regex();
        assertTrue(regex.matchBrAbba("ab[abba]ab"));
    }

    @Test
    public void shouldTls() {
        Regex regex = new Regex();
        assertTrue(regex.isTls(s1));
        assertFalse(regex.isTls(s2));
        assertFalse(regex.isTls(s3));
        assertTrue(regex.isTls(s4));
    }

    @Test
    public void shouldSSL() {
        String ss1 = "aba[bab]xyz";
        String ss2 = "xyx[xyx]xyx";
        String ss3 = "aaa[kek]eke";
        String ss4 = "zazbz[bzb]cdb";
        Regex regex = new Regex();
        assertTrue(regex.isSSL(ss1));
        assertFalse(regex.isSSL(ss2));
        assertTrue(regex.isSSL(ss3));
        assertTrue(regex.isSSL(ss4));
    }

    @Test
    public void specialCase() {
        Regex regex = new Regex();
        assertTrue(regex.isSSL("aba[abab]xxx[xxx]xxx"));
    }

    @Test
    public void shouldDoInput() throws URISyntaxException, IOException {
        Regex regex = new Regex();
        Path path = Paths.get(this.getClass().getResource("/day7/input.txt").toURI());

        try (Stream<String> stream = Files.lines(path)) {
            long count = stream.filter(regex::isTls).count();
            System.out.println(count);
        }
    }

    @Test
    public void shouldDoInput2() throws URISyntaxException, IOException {
        Regex regex = new Regex();
        Path path = Paths.get(this.getClass().getResource("/day7/input.txt").toURI());

        try (Stream<String> stream = Files.lines(path)) {
            long count = stream.filter(regex::isSSL).count();
            System.out.println(count);
        }
    }

    @Test
    public void shouldDoBabs() {
        Regex regex = new Regex();
        List<String> babs = regex.getBabs("ab[abab]xx");

        assertEquals(babs.size(), 2);
        assertTrue(babs.contains("aba"));
        assertTrue(babs.contains("bab"));
    }
}
