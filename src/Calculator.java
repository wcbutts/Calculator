import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    private String operator;
    private  Scanner userInput;
    private  ArrayList<String> listOfOperators;

    public Calculator() {
        listOfOperators = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));
        userInput = new Scanner(System.in);
    }

    private double getNumber() {
        double number;

        while (true) {
            try {
                number = userInput.nextDouble();
                userInput.nextLine();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid entry. Choose a number ");
                userInput.nextLine();
            }
        }
        return number;
    }

    private String getOperator() {

        while (true) {
            operator = userInput.nextLine();

            if (!listOfOperators.contains(operator)) {
                System.out.println("Invalid entry. Choose an operator (+,-, *, or /)");
            } else {
                break;
            }
        }
        return operator;
    }

    private String getAnotherOperator() {
        while (true) {
            operator = userInput.nextLine();

            if (!listOfOperators.contains(operator) && !operator.equals("=")) {
                System.out.println("Invalid entry. Choose an operator (+,-, *, or /) or choose '=' to total.");
            } else {
                break;
            }
        }
        return operator;
    }

    private void calculate() {

        System.out.println("Choose a number.");
        double operand1 = getNumber();
        System.out.println("Choose an operator (+,-, *, or /)");
        operator = getOperator();

        double result;
        boolean wantToContinue = true;

        do {

            System.out.println("Choose another number.");
            double operand2 = getNumber();

            if (operator.equals("/") && operand2 == 0) {
                System.out.print("Cannot divide by 0. ");
                continue;
            }

            result = switch (operator) {

                case "+" -> operand1 + operand2;
                case "-" -> operand1 - operand2;
                case "*" -> operand1 * operand2;
                case "/" -> operand1 / operand2;

                default -> 0;
            };
            operand1 = result;

            System.out.println("Choose another operator (+,-, *, or /) or choose '=' to total.");
            operator = getAnotherOperator();

            if (operator.equals("=")) {
                System.out.println("Result: " + result);
                wantToContinue = false;
            }
        } while (wantToContinue);

        userInput.close();
    }

    public void run() {
        calculate();
    }
}