package HurtMePlenty.formatter;

public class ValueFormatter {
    public static final String SEPARATOR = ": ";
    public static final int VALUE_POSITION = 1;

    public static String getValueFromString(String inputString) {
        return inputString.split(SEPARATOR)[VALUE_POSITION];
    }
}
