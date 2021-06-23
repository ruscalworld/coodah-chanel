package ru.ruscalworld.coodahchanel.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String description();
    Argument[] arguments() default {};
}
