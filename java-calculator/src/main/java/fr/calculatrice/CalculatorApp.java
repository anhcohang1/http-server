package fr.calculatrice;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class CalculatorApp extends JFrame {
    private final JTextField display = new JTextField("0");
    private BigDecimal accumulator;
    private String pendingOperation;
    private boolean startNewNumber = true;

    public CalculatorApp() {
        super("Calculatrice Java 25");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(360, 500);
        setLocationRelativeTo(null);

        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        display.setBorder(new EmptyBorder(16, 12, 16, 12));

        JPanel buttons = new JPanel(new GridLayout(5, 4, 8, 8));
        buttons.setBorder(new EmptyBorder(10, 10, 10, 10));

        addButton(buttons, "7"); addButton(buttons, "8"); addButton(buttons, "9"); addButton(buttons, "÷");
        addButton(buttons, "4"); addButton(buttons, "5"); addButton(buttons, "6"); addButton(buttons, "×");
        addButton(buttons, "1"); addButton(buttons, "2"); addButton(buttons, "3"); addButton(buttons, "-");
        addButton(buttons, "0"); addButton(buttons, "."); addButton(buttons, "="); addButton(buttons, "+");
        addButton(buttons, "C"); addButton(buttons, "F"); addButton(buttons, "±"); addButton(buttons, "⌫");

        add(display, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, String label) {
        JButton button = new JButton(label);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        button.addActionListener(e -> handle(label));
        panel.add(button);
    }

    private void handle(String key) {
        try {
            if (key.matches("\\d")) {
                if (startNewNumber || display.getText().equals("0")) display.setText(key);
                else display.setText(display.getText() + key);
                startNewNumber = false;
            } else if (key.equals(".")) {
                if (startNewNumber) {
                    display.setText("0.");
                    startNewNumber = false;
                } else if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            } else if ("+-×÷".contains(key)) {
                applyPending();
                accumulator = new BigDecimal(display.getText());
                pendingOperation = key;
                startNewNumber = true;
            } else if (key.equals("=")) {
                applyPending();
                pendingOperation = null;
                accumulator = null;
                startNewNumber = true;
            } else if (key.equals("C")) {
                display.setText("0");
                accumulator = null;
                pendingOperation = null;
                startNewNumber = true;
            } else if (key.equals("±")) {
                BigDecimal value = new BigDecimal(display.getText()).negate();
                display.setText(Calculator.format(value));
            } else if (key.equals("⌫")) {
                if (!startNewNumber && display.getText().length() > 1) {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                } else {
                    display.setText("0");
                    startNewNumber = true;
                }
            } else if (key.equals("F")) {
                BigInteger n = new BigInteger(display.getText());
                display.setText(SpecialFunction.f(n).toString());
                accumulator = null;
                pendingOperation = null;
                startNewNumber = true;
            }
        } catch (NumberFormatException ex) {
            showError("F nécessite un entier n.");
        } catch (ArithmeticException | IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    private void applyPending() {
        if (pendingOperation != null && accumulator != null) {
            BigDecimal right = new BigDecimal(display.getText());
            display.setText(Calculator.format(Calculator.calculate(accumulator, right, pendingOperation)));
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
        display.setText("0");
        accumulator = null;
        pendingOperation = null;
        startNewNumber = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp().setVisible(true));
    }
}
