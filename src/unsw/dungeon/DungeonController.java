package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import unsw.dungeon.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private DungeonStartScreen startScreen;
    
    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    public void setStartScreen(DungeonStartScreen startScreen) {
    	this.startScreen = startScreen;
    }
    
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE:
                player.fireBomb(this);
                break;
            case UP:
                player.moveUp();
                refreshUI();
                break;
            case DOWN:
                player.moveDown();
                refreshUI();
                break;
            case LEFT:
                player.moveLeft();
                refreshUI();
                break;
            case RIGHT:
                player.moveRight();
                refreshUI();
                break;
            default:
                break;
        }
        checkAlive();
        checkSuccess();
    }

    public void refreshUI() {
        DungeonControllerLoader dungeonControllerLoader = new DungeonControllerLoader();
        initialEntities = dungeonControllerLoader.refreshImageEntities(dungeon);
        initialize();
    }

    private void checkSuccess() {
        if (player.isSuccess()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Game Over");
            alert.headerTextProperty().set("you win!!!");
            alert.showAndWait();
            System.exit(0);
        }
    }

    public void checkAlive() {
        if (!dungeon.getPlayer().isAlive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Game Over");
            alert.headerTextProperty().set("you lose!!!");
            
            alert.showAndWait();
            System.exit(0);
        }
        if (!player.isAlive()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("Game Over");
            alert.headerTextProperty().set("you lose!!!");
            alert.showAndWait();
           
            System.exit(0);
        }
    }

}

