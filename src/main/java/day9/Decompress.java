package day9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decompress {

    Pattern p = Pattern.compile("(\\(\\d+x\\d+\\))");
    Pattern q = Pattern.compile("\\((\\d+)x(\\d+)\\)");

    public int length(String s) {

        int length = s.length();
        int pos = 0;
        Matcher matcher = p.matcher(s);

        while (matcher.find(pos)) {
            String group = matcher.group(1);

            Matcher expression = q.matcher(group);
            expression.find();
            Integer nrOfChars = Integer.parseInt(expression.group(1));
            Integer times = Integer.parseInt(expression.group(2));

            length = length - group.length() + nrOfChars * (times - 1);
            pos = matcher.end() + nrOfChars;
        }
        return length;
    }

    public long lengthv2(String s) {
        long length = s.length();
        int pos = 0;
        Matcher matcher = p.matcher(s);

        while (matcher.find(pos)) {
            String group = matcher.group(1);

            Matcher expression = q.matcher(group);
            expression.find();
            Integer nrOfChars = Integer.parseInt(expression.group(1));
            Integer times = Integer.parseInt(expression.group(2));

            String subString = s.substring(matcher.end(), matcher.end() + nrOfChars);
            long sublength = lengthv2(subString);

            length = length - group.length() - subString.length() + sublength * times;
            pos = matcher.end() + nrOfChars;
        }
        return length;

    }
}
