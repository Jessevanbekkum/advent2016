package day7;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    Pattern abba = Pattern.compile("\\w*(\\w)(?!\\1)(\\w)\\2\\1\\w*");
    Pattern brAbba = Pattern.compile("\\[\\w*(\\w)(?!\\1)(\\w)\\2\\1\\w*\\]");

    private Pattern brackets = Pattern.compile("\\[(\\w*)]");

    private Pattern bab = Pattern.compile("(\\w)(?!\\1)(\\w)\\1");

    public boolean matchAbba(String s) {
        return abba.matcher(s).find();
    }

    public boolean matchBrAbba(String s) {
        return brAbba.matcher(s).find();
    }

    public boolean isTls(String s) {
        return matchAbba(s) && !matchBrAbba(s);
    }

    public boolean isSSL(String s) {
        List<String> babs = getBabs(s);
        String s2 = removeBrackets(s);
        for (String bab : babs) {
            String aba = String.valueOf(new char[]{bab.charAt(1), bab.charAt(0), bab.charAt(1)});
            if (s2.contains(aba)) {
                return true;
            }
        }
        return false;
    }

    private String removeBrackets(final String s) {
        return s.replaceAll("\\[\\w*]", "|");
    }



    public List<String> getBabs(String s) {
        List<String> babs = new ArrayList<>();
        Matcher matcher = brackets.matcher(s);
        while (matcher.find()) {
            String inside = matcher.group(1);
            Matcher matcher1 = bab.matcher(inside);
            int pos = 0;
            while (matcher1.find(pos)) {
                String c1 = matcher1.group(1);
                String c2 = matcher1.group(2);
                String bab = c1 + c2 + c1;
                babs.add(bab);
                pos = matcher1.start() + 1;
            }
        }
        return babs;
    }
}
