package com.solvd.laba.decorator;

public class ValidatorFactory {
    public static NumberValidator getValidator(boolean positive, boolean even, boolean squared) {
        NumberValidator numberValidator = new BasicNumberValidator();
        if (positive) {
            numberValidator = new PositiveNumberDecorator(numberValidator);
        }
        if (even) {
            numberValidator = new EvenNumberDecorator(numberValidator);
        }
        if (squared) {
            numberValidator = new SquareNumberDecorator(numberValidator);
        }
        return numberValidator;
    }
}
