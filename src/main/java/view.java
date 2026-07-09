
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class view {

    private final VBox root;
    private final TextField display;
    private ButtonClickListener listener;

    public view() {
        display = new TextField("0");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font("Arial", 24));
        display.setPrefHeight(60);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        String[][] buttons = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"C", "0", "=", "+"}
        };

        for (int r = 0; r < buttons.length; r++) {
            for (int c = 0; c < buttons[r].length; c++) {
                String text = buttons[r][c];
                Button btn = createButton(text);

                btn.setOnAction(e -> {
                    if (listener != null) {
                        listener.onButtonClick(text);
                    }
                });

                gridPane.add(btn, c, r);
            }
        }

        root = new VBox(10, display, gridPane);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #1e1e1e;");
    }

    public Pane getRootNode() {
        return root;
    }

    public String getDisplayText() {
        return display.getText();
    }

    public void setDisplayText(String text) {
        display.setText(text);
    }

    public void setButtonClickListener(ButtonClickListener listener) {
        this.listener = listener;
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setPrefSize(60, 60);
        btn.setFont(Font.font("Arial", 18));

        if (text.matches("[0-9]")) {
            btn.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: white; -fx-background-radius: 5;");
        } else if ("=".equals(text)) {
            btn.setStyle("-fx-background-color: #f1a33c; -fx-text-fill: white; -fx-background-radius: 5;");
        } else if ("C".equals(text)) {
            btn.setStyle("-fx-background-color: #a5a5a5; -fx-text-fill: black; -fx-background-radius: 5;");
        } else {
            btn.setStyle("-fx-background-color: #5e5e5e; -fx-text-fill: white; -fx-background-radius: 5;");
        }
        return btn;
    }
}