package com.rain.utils.captcha.enums;

/**
 * 运算符枚举
 *
 * @author rain
 * @date 2024/09/22
 */
public enum OperatorsEnum {
    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/');

    private final char operator;

    OperatorsEnum(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }
}
