package com.solvd.laba.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EvenNumberDecorator implements NumberValidator {
    public static final Logger LOGGER = LogManager.getLogger(EvenNumberDecorator.class);

    private final NumberValidator numberValidator;

    public EvenNumberDecorator(NumberValidator numberValidator) {
        this.numberValidator = numberValidator;
    }

    @Override
    public void validate(int number) {
        numberValidator.validate(number);
        if (number % 2 != 0) {
            LOGGER.info("%d is not even".formatted(number));
        } else {
            LOGGER.info("%d is even".formatted(number));
        }
    }
}
