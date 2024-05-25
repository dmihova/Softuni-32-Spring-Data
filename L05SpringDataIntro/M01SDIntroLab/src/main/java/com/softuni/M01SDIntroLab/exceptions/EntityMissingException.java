package com.softuni.M01SDIntroLab.exceptions;

public class EntityMissingException extends RuntimeException {
    public EntityMissingException(String msg) {
        super(msg);
    }
}