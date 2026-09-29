package fr.calculatrice;

import java.math.BigInteger;

public final class SpecialFunction {
    private SpecialFunction() {}

    /**
     * F(n) = somme des chiffres de 3n² + n + 1.
     */
    public static BigInteger f(BigInteger n) {
        BigInteger value = n.pow(2)
                .multiply(BigInteger.valueOf(3))
                .add(n)
                .add(BigInteger.ONE);

        return digitSum(value);
    }

    static BigInteger digitSum(BigInteger value) {
        String digits = value.abs().toString();
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < digits.length(); i++) {
            sum = sum.add(BigInteger.valueOf(digits.charAt(i) - '0'));
        }
        return sum;
    }
}
