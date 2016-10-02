package io.sponges.bot.dashboard;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface Route {

    Method method();

}
