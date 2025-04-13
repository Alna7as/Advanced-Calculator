package javafxapplication4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JavaFXApplication4 extends Application {

    private TextField num1Field, num2Field;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("آلة حاسبة متطورة");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(15);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #f4f4f4;");

        Label num1Label = new Label("الرقم الأول:");
        num1Label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        num1Label.setTextFill(Color.DARKBLUE);
        grid.add(num1Label, 0, 0);

        num1Field = new TextField();
        num1Field.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14; -fx-border-color: #cccccc; -fx-border-radius: 5;");
        grid.add(num1Field, 1, 0);

        Label num2Label = new Label("الرقم الثاني:");
        num2Label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        num2Label.setTextFill(Color.DARKBLUE);
        grid.add(num2Label, 0, 1);

        num2Field = new TextField();
        num2Field.setStyle("-fx-background-color: #ffffff; -fx-font-size: 14; -fx-border-color: #cccccc; -fx-border-radius: 5;");
        grid.add(num2Field, 1, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button addButton = createStyledButton("➕ جمع", "#4CAF50");
        addButton.setOnAction(e -> calculate('+'));

        Button subtractButton = createStyledButton("➖ طرح", "#FF9800");
        subtractButton.setOnAction(e -> calculate('-'));

        Button multiplyButton = createStyledButton("✖️ ضرب", "#2196F3");
        multiplyButton.setOnAction(e -> calculate('*'));

        Button divideButton = createStyledButton("➗ قسمة", "#F44336");
        divideButton.setOnAction(e -> calculate('/'));

        Button percentageButton = createStyledButton("% نسبة", "#9C27B0");
        percentageButton.setOnAction(e -> calculate('%'));

        Button clearButton = createStyledButton("مسح", "#607D8B");
        clearButton.setOnAction(e -> clearFields());

        buttonBox.getChildren().addAll(addButton, subtractButton, multiplyButton, divideButton, percentageButton, clearButton);
        grid.add(buttonBox, 0, 2, 2, 1);

        resultLabel = new Label("النتيجة: ");
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        resultLabel.setTextFill(Color.DARKGREEN);
        grid.add(resultLabel, 0, 3, 2, 1);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: " + color + "; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10 20; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5;"
        );
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: derive(" + color + ", -20%);"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + color + ";"));
        button.setOnMousePressed(e -> button.setStyle("-fx-background-color: derive(" + color + ", -30%);"));
        button.setOnMouseReleased(e -> button.setStyle("-fx-background-color: derive(" + color + ", -20%);"));

        return button;
    }

    private void calculate(char operator) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultLabel.setText("خطأ: القسمة على صفر!");
                        resultLabel.setTextFill(Color.RED);
                        return;
                    }
                    break;
                case '%':
                    result = (num1 * num2) / 100;
                    break;
            }
            
            resultLabel.setText("النتيجة: " + result);
            resultLabel.setTextFill(Color.DARKGREEN);
        } catch (NumberFormatException e) {
            resultLabel.setText("خطأ: أدخل أرقامًا صحيحة!");
            resultLabel.setTextFill(Color.RED);
        }
    }

    private void clearFields() {
        num1Field.clear();
        num2Field.clear();
        resultLabel.setText("النتيجة: ");
        resultLabel.setTextFill(Color.DARKGREEN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}