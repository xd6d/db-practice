package com.solvd.laba.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SquareNumberDecorator implements NumberValidator {
    public static final Logger LOGGER = LogManager.getLogger(SquareNumberDecorator.class);

    private final NumberValidator numberValidator;

    public SquareNumberDecorator(NumberValidator numberValidator) {
        this.numberValidator = numberValidator;
    }

    @Override
    public void validate(int number) {
        numberValidator.validate(number);
        int root = (int) Math.sqrt(number);
        if (Math.pow(root, 2) == number) {
            LOGGER.info("%d is squared".formatted(number));
        } else {
            LOGGER.info("%d is not squared".formatted(number));
        }
    }
}
