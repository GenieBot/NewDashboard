package io.sponges.bot.dashboard;

public interface DataValidator {

    default boolean validateStrings(String... strings) {
        if (strings == null) {
            return false;
        }
        for (String string : strings) {
            if (string == null || string.length() <= 0) {
                return false;
            }
        }
        return true;
    }

}
