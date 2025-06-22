package net.jonnegaming.starMetal.text;

public class strings {

    public static String cleanString(String input) {
        // Replace any character that is not a letter or a number with an empty string
        String cleanedString = input.replaceAll("[^a-zA-Z0-9 ]", "");

        // Replace spaces with underscores
        cleanedString = cleanedString.replaceAll(" ", "_");

        return cleanedString;
    }
    public static String getRomanNumeral(int number) {
        if (number <= 0 || number > 3999) {
            return String.valueOf(number); // Return the number itself if out of range
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[number / 1000] +
                hundreds[(number % 1000) / 100] +
                tens[(number % 100) / 10] +
                ones[number % 10];
    }
}
