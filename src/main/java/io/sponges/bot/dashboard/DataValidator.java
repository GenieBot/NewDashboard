package io.sponges.bot.dashboard;

public interface DataValidator {

    default boolean valid(String... strings) {
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
