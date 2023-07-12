package application;

import gui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
        AnchorPane root = loader.load();

        ScrollPane scrollPane = (ScrollPane) root.lookup("#scrollPane");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);

        ((MainController) loader.getController()).init(stage);
        stage.show();
    }

    public static Scene getScene(){
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}