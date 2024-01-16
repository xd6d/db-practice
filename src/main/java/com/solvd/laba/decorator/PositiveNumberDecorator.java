package com.solvd.laba.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PositiveNumberDecorator implements NumberValidator {
    public static final Logger LOGGER = LogManager.getLogger(PositiveNumberDecorator.class);

    private final NumberValidator numberValidator;

    public PositiveNumberDecorator(NumberValidator numberValidator) {
        this.numberValidator = numberValidator;
    }

    @Override
    public void validate(int number) {
        numberValidator.validate(number);
        if (number > 0) {
            LOGGER.info("%d is positive".formatted(number));
        } else {
            LOGGER.info("%d is not positive".formatted(number));
        }
    }
}
