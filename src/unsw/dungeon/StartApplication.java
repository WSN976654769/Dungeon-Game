package unsw.dungeon;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        DungeonStartScreen startScreen = new DungeonStartScreen(primaryStage);
       // DungeonApplication app = new DungeonApplication(primaryStage);
		//DungeonApplication app = new DungeonApplication(primaryStage);
//		startScreen.getController().setApp(app);
        startScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
