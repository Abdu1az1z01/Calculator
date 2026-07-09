
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        model model = new model();

        view view = new view();

        new controller(model, view);

        Scene scene = new Scene(view.getRootNode(), 300, 400);
        primaryStage.setTitle("Hardcore CVM Calc");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}