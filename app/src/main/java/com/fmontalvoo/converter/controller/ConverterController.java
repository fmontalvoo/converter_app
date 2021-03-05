package com.fmontalvoo.converter.controller;

import java.math.BigInteger;

public class ConverterController {
    private final BigInteger TWO = BigInteger.valueOf(2);
    private final BigInteger EIGHT = BigInteger.valueOf(8);
    private final BigInteger SIXTEEN = BigInteger.valueOf(16);

    public String convert(String number, int option) {
        if (number == null || number.isEmpty()) return "";

        switch (option) {
            case 1:
                return binaryToHexadecimal(number);
            case 2:
                return binaryToDecimal(number);
            case 3:
                return binaryToOctal(number);
            case 4:
                return octalToHexadecimal(number);
            case 5:
                return octalToDecimal(number);
            case 6:
                return octalToBinary(number);
            case 7:
                return decimalToHexadecimal(number);
            case 8:
                return decimalToOctal(number);
            case 9:
                return decimalToBinary(number);
            case 10:
                return hexadecimalToDecimal(number);
            case 11:
                return hexadecimalToOctal(number);
            case 12:
                return hexadecimalToBinary(number);
            default:
                return "";
        }
    }

    private BigInteger powerOf(BigInteger base, BigInteger exponent) {
        if (exponent.compareTo(BigInteger.ZERO) == 0)
            return BigInteger.ONE;
        return base.multiply(powerOf(base, exponent.subtract(BigInteger.ONE)));
    }

    private String solve(String number, BigInteger base) {
        BigInteger result = BigInteger.ZERO;
        for (BigInteger i = BigInteger.valueOf(number.length() - 1), j = BigInteger.ZERO; i
                .compareTo(BigInteger.ZERO) >= 0; i = i.subtract(BigInteger.ONE), j = j.add(BigInteger.ONE)) {
            result = result.add(new BigInteger("" + number.charAt(i.intValue())).multiply(powerOf(base, j)));
        }
        return result.toString();
    }

    private String reverse(String number) {
        StringBuilder reversed = new StringBuilder();
        for (int i = number.length() - 1; i >= 0; i--)
            reversed.append(number.charAt(i));
        return reversed.toString();
    }

    private String completeBits(String binary) {
        if ((binary.length() % 4) == 0)
            return binary;
        return completeBits('0' + binary);
    }

    private char characters(int n) {
        switch (n) {
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
            default:
                return '\0';
        }
    }

    private BigInteger numbers(char c) {
        switch (c) {
            case 'A':
                return BigInteger.valueOf(10);
            case 'B':
                return BigInteger.valueOf(11);
            case 'C':
                return BigInteger.valueOf(12);
            case 'D':
                return BigInteger.valueOf(13);
            case 'E':
                return BigInteger.valueOf(14);
            case 'F':
                return BigInteger.valueOf(15);
            default:
                return BigInteger.ZERO;
        }
    }

    private String binaryToHexadecimal(String binary) {
        return decimalToHexadecimal(binaryToDecimal(binary));
    }

    private String binaryToDecimal(String binary) {
        return solve(binary, TWO);
    }

    private String binaryToOctal(String binary) {
        return decimalToOctal(binaryToDecimal(binary));
    }

    private String octalToHexadecimal(String octal) {
        return decimalToHexadecimal(octalToDecimal(octal));
    }

    private String octalToDecimal(String octal) {
        return solve(octal, EIGHT);
    }

    private String octalToBinary(String octal) {
        return decimalToBinary(octalToDecimal(octal));
    }

    private String decimalToHexadecimal(String number) {
        StringBuilder hexadecimal = new StringBuilder();
        for (BigInteger i = new BigInteger(number); i.compareTo(BigInteger.ZERO) > 0; i = i.divide(SIXTEEN)) {
            BigInteger result = i.mod(SIXTEEN);
            if (result.compareTo(BigInteger.TEN) >= 0) {
                hexadecimal.append(characters(result.intValue()));
            } else {
                hexadecimal.append(result);
            }
        }
        return reverse(hexadecimal.toString());
    }

    private String decimalToOctal(String number) {
        String octal = "";
        for (BigInteger i = new BigInteger(number); i.compareTo(BigInteger.ZERO) > 0; i = i.divide(EIGHT)) {
            octal += i.mod(EIGHT);
        }
        return reverse(octal);
    }

    private String decimalToBinary(String number) {
        String binary = "";
        for (BigInteger i = new BigInteger(number); i.compareTo(BigInteger.ZERO) > 0; i = i.divide(TWO)) {
            binary += i.mod(TWO);
        }
        return completeBits(reverse(binary));
    }

    private String hexadecimalToDecimal(String hexadecimal) {
        BigInteger decimal = BigInteger.ZERO;
        final String characters = "ABCDEF";
        BigInteger len = new BigInteger(String.valueOf(hexadecimal.length()));
        BigInteger[] oremun = new BigInteger[len.intValue()];

        for (BigInteger i = BigInteger.ZERO; i.compareTo(len) < 0; i = i.add(BigInteger.ONE)) {
            for (BigInteger j = BigInteger.ZERO; j.compareTo(BigInteger.valueOf(characters.length())) < 0; j = j
                    .add(BigInteger.ONE)) {
                char c = hexadecimal.charAt(i.intValue());
                if (c == characters.charAt(j.intValue())) {
                    oremun[i.intValue()] = numbers(c);
                } else if (c < 65) {
                    oremun[i.intValue()] = new BigInteger("" + hexadecimal.charAt(i.intValue()));
                }
            }

        }

        for (BigInteger i = len.subtract(BigInteger.ONE), j = BigInteger.ZERO; i.compareTo(BigInteger.ZERO) >= 0; i = i
                .subtract(BigInteger.ONE), j = j.add(BigInteger.ONE)) {
            decimal = decimal.add(oremun[i.intValue()].multiply(powerOf(SIXTEEN, j)));
        }
        return decimal.toString();
    }

    private String hexadecimalToOctal(String hexadecimal) {
        return decimalToOctal(hexadecimalToDecimal(hexadecimal));
    }

    private String hexadecimalToBinary(String hexadecimal) {
        return decimalToBinary(hexadecimalToDecimal(hexadecimal));
    }
}
