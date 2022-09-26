package org.olexandra;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private final Pattern mainPattern = Pattern.compile("(?://(.+?)\\n)?([\\s\\S]+)");
    private final Pattern delimiterCheck = Pattern.compile("((?:\\[[^\\n\\d\\[\\]]+?])+)|([^\\n\\d\\[\\]]+)");
    private final Pattern delimiterParser = Pattern.compile("(?<=^\\[|]\\[)[^\\[\\]\\n]+(?=]$|]\\[)");


    public int add(String numbers) {

        Matcher matcher = mainPattern.matcher(numbers);

        if (!matcher.matches()) {
            return 0;
        }

        List<String> delimiters = parseDelimiters(matcher.group(1));

        String[] stringNumbers = matcher.group(2).split(joinDelimiters(delimiters));

        List<Integer> negativeNumbers = new ArrayList<>();
        int result = 0;
        for (String element : stringNumbers) {

            int number = Integer.parseInt(element);

            if (number < 0) {
                negativeNumbers.add(number);
            }
            else if (number <= 1000) {
                result += number;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new RuntimeException("Negative numbers: " + negativeNumbers.toString());
        }


        return result;
    }


    private String joinDelimiters(List<String> list) {

        List<String> q = new ArrayList<>();
        for (String s : list) {
            q.add("(?<=\\d)" + Pattern.quote(s) + "(?=\\d|-)");
        }

        return String.join("|", q);

    }

    private List<String> parseDelimiters(String delimiters) {

        if (delimiters == null || delimiters.isEmpty()) {
            return List.of(",", "\n");
        }

        Matcher d = delimiterCheck.matcher(delimiters);

        if (!d.matches()) {
            throw new RuntimeException("Failed to parse delimiters: " + delimiters);
        }

        List<String> delimiterList = new ArrayList<>();
        delimiterList.add("\n");
        delimiterList.add(",");

        String multiple = d.group(1);
        String single = d.group(2);

        if (multiple == null && single == null) {
            throw new RuntimeException("Failed to parse delimiters");
        }

        if (single != null) {
            delimiterList.add(single);
            return delimiterList;
        }

        Matcher parse = delimiterParser.matcher(multiple);

        while (parse.find()) {
            delimiterList.add(parse.group());
        }

        return delimiterList;
    }

}
