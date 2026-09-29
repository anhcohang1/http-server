package fr.calculatrice;

import java.math.BigDecimal;
import java.math.MathContext;

public final class Calculator {
    private static final MathContext MC = MathContext.DECIMAL128;

    private Calculator() {}

    public static BigDecimal calculate(BigDecimal a, BigDecimal b, String operation) {
        return switch (operation) {
            case "+" -> a.add(b, MC);
            case "-" -> a.subtract(b, MC);
            case "×", "x", "X", "*" -> a.multiply(b, MC);
            case "÷", ":", "/" -> {
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Division par zéro impossible.");
                }
                yield a.divide(b, MC);
            }
            default -> throw new IllegalArgumentException("Opération inconnue : " + operation);
        };
    }

    public static String format(BigDecimal value) {
        return value.stripTrailingZeros().toPlainString();
    }
}
