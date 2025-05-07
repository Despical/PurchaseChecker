package me.despical.purchasechecker.utils;

import java.util.function.UnaryOperator;

/**
 * @author Despical
 * <p>
 * Created at 7.05.2025
 */
public class WordUtils {

    public static final UnaryOperator<String> REMOVE_HYPHEN = str -> str.replace('-', ' ');

    /**
     * Capitalizes the first letter of each word in the input string after applying a mapping function.
     *
     * @param input  The input string that contains words to be capitalized.
     * @param mapper A mapping function (UnaryOperator) to apply to the input string before processing.
     *
     * @return A new string with the first letter of each word capitalized and the rest in lowercase.
     *
     * @throws NullPointerException if the input string or mapper is null.
     */
    public static String capitalizeFirstLetters(String input, UnaryOperator<String> mapper) {
        input = mapper.apply(input);

        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
            }
        }

        return result.toString().trim();
    }

    public static String capitalizeFirstLetters(String input) {
        return capitalizeFirstLetters(input, UnaryOperator.identity());
    }
}
