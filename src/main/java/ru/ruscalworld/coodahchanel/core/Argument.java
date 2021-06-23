package ru.ruscalworld.coodahchanel.core;

import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Argument {
    String name();
    String description();
    OptionType type();
    boolean required() default false;
}
