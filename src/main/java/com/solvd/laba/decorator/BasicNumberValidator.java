package com.solvd.laba.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicNumberValidator implements NumberValidator {
    public static final Logger LOGGER = LogManager.getLogger(BasicNumberValidator.class);

    @Override
    public void validate(int number) {
        LOGGER.info("Accepted number %d".formatted(number));
    }
}
