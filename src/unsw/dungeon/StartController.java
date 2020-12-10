package unsw.dungeon;



import java.io.IOException;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class StartController {   
	
	private DungeonApplication app;
	
    @FXML
    private Button treasure;
    
    @FXML
    private Button exit;
    
    @FXML
    private Button boulder;
    @FXML
    private Button enemies;
    @FXML
    private Button combine1;
    @FXML
    private Button combine2;
    @FXML
    private Button combine3;

    @FXML
    public void handleExitButton(ActionEvent event) throws IOException {
      System.out.println("exit");     
//      app.chooseMap("exit");      
//      app.start();	  
    }
    
    @FXML
    public void handleBoulderButton(ActionEvent event) {
       System.out.println("boulder");
     //  app.chooseMap("boulder");
    }
    
    @FXML
    public void handleTreasureButton(ActionEvent event) {
       System.out.println("treasure");
     //  app.chooseMap("treasure");
    }
    
    @FXML
    public void handleEnemyButton(ActionEvent event) {
       System.out.println("enemy");
      // app.chooseMap("enemy");
    }
    @FXML
    public void ExitTreasureButton(ActionEvent event) {
    	// app.chooseMap("treasure and exit");
    }
    @FXML
    public void TreasureEnemieButton(ActionEvent event) {
    	// app.chooseMap("ememy and treasure");
    }
    @FXML
    public void BoulderEnemiesButton(ActionEvent event) {
    	// app.chooseMap("boulder and enemies");
    }
    public void setApp(DungeonApplication app) {
       this.app = app;
    }

}

