public class controller implements ButtonClickListener {

    private final model model;
    private final view view;

    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public controller(model model, view view) {
        this.model = model;
        this.view = view;
        this.view.setButtonClickListener(this);
    }

    public controller(model model, view view, model model1, view view1) {
        this.model = model1;
        this.view = view1;
    }

    @Override
    public void onButtonClick(String buttonText) {
        String currentText = view.getDisplayText();

        if (buttonText.matches("[0-9]")) {
            handleDigit(currentText, buttonText);
        } else if ("C".equals(buttonText)) {
            handleClear();
        } else if ("=".equals(buttonText)) {
            handleEquals(currentText);
        } else {
            handleOperator(currentText, buttonText);
        }
    }

    private void handleDigit(String currentText, String digit) {
        if (startNewNumber || "0".equals(currentText)) {
            view.setDisplayText(digit);
            startNewNumber = false;
        } else {
            view.setDisplayText(currentText + digit);
        }
    }

    private void handleClear() {
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
        view.setDisplayText("0");
    }

    private void handleEquals(String currentText) {
        if (!operator.isEmpty()) {
            try {
                double secondNumber = Double.parseDouble(currentText);
                double result = model.calculate(firstNumber, secondNumber, operator);

                String finalResult = (result % 1 == 0)
                        ? String.valueOf((int) result)
                        : String.valueOf(result);

                view.setDisplayText(finalResult);
            } catch (ArithmeticException e) {
                view.setDisplayText("Error");
            } finally {
                operator = "";
                startNewNumber = true;
            }
        }
    }

    private void handleOperator(String currentText, String op) {
        try {
            firstNumber = Double.parseDouble(currentText);
            operator = op;
            startNewNumber = true;
        } catch (NumberFormatException e) {
            view.setDisplayText("Error");
        }
    }
}