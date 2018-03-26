package library.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.AppConfig.Config;

public class Main extends Application {

    @Override
//  create a window
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BookList.fxml"));
        primaryStage.setTitle(Config.HOME_WINDOW_TITLE);
        primaryStage.setScene(new Scene(root, Config.HOME_WINDOW_WIDTH, Config.HOME_WINDOW_HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
