package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication  extends Application{

    private String map;
    public void start(Stage stage) throws IOException {
        stage.setTitle("Dungeon");

//     map = "treasure.json";
//     map = "boulders.json";
//     map = "maze.json";
//     map = "advanced.json";
//     map = "advanced1.json";
//     map = "enemies.json";
     map ="door.json";
//     map ="pit.json";
      
     	
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(map);
        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.show();

    }
    public void chooseMap(String map) {
 	  
 	   switch (map) {
 	   case "exit":
 		   this.map = "maze.json";
 		   System.out.println("exit");
 		   break;
 	   case "treasure":
 		   this.map = "treasure.json";
 		   break;
 	   case "enemy":
 		   this.map = "enemies.json";
 		   break;
 	   case "boulder":
 		   this.map = "boulders.json";
 		   break;
 	   }
    }


//    public static void main(String[] args) {
//        launch(args);
//    }

}
