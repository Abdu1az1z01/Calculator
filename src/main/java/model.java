
public class model {

    public double calculate(double n1, double n2, String op) {
        switch (op) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/":
                if (n2 == 0) throw new ArithmeticException("Division by zero");
                return n1 / n2;
            default: return 0;
        }
    }
}