package com.solvd.laba.decorator;

public class Demo {

    public static void main(String[] args) {
        //Check squared and even
        NumberValidator numberValidator = ValidatorFactory.getValidator(false, true, true);
        numberValidator.validate(4);
        numberValidator.validate(9);
        numberValidator.validate(11);
        numberValidator.validate(-4);

        //Check positive and even
        numberValidator = ValidatorFactory.getValidator(true, true, false);
        numberValidator.validate(10);
        numberValidator.validate(-10);
        numberValidator.validate(-13);
        numberValidator.validate(13);
    }
}
