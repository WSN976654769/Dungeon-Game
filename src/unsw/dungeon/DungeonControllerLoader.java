package unsw.dungeon;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.entity.*;
import unsw.dungeon.entity.thing.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image boulderImage;
    private Image switchImage;
    private Image bombImage;
    private Image enemyImage;
    private Image invincibilityImage;
    private Image swordImage;
    private Image treasureImage;
    private Image keyImage;
    private Image opendoorImage;
    private Image closeddoorImage;
    private Image hoverImage;
    private Image pitImage;

    public DungeonControllerLoader() {
        super();
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");        
        treasureImage = new Image("/gold_pile.png");
        pitImage = new Image("/pit.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
        hoverImage = new Image("/bubbly.png");
        bombImage = new Image("/bomb_unlit.png");
        keyImage = new Image("/key.png");
        closeddoorImage = new Image("/closed_door.png");
        opendoorImage = new Image("/open_door.png");

        
    }

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");        
        treasureImage = new Image("/gold_pile.png");
        pitImage = new Image("/pit.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        invincibilityImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
        hoverImage = new Image("/bubbly.png");
        bombImage = new Image("/bomb_unlit.png");
        keyImage = new Image("/key.png");
        closeddoorImage = new Image("/closed_door.png");
        opendoorImage = new Image("/open_door.png");

    }

    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(Switch sw) {
        ImageView view = new ImageView(switchImage);
        addEntity(sw, view);
    }

    @Override 
    public void onLoad(Bomb bomb) {
        ImageView view = new ImageView();
        switch (bomb.getLastTime()) {
            case 5:
                view = new ImageView(bombImage);
                break;
            case 4:
                view = new ImageView(new Image("/bomb_lit_1.png"));
                break;
            case 3:
                view = new ImageView(new Image("/bomb_lit_2.png"));
                break;
            case 2:
                view = new ImageView(new Image("/bomb_lit_3.png"));
                break;
            case 1:
                view = new ImageView(new Image("/bomb_lit_4.png"));
            default:
                break;
        }
        addEntity(bomb, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }

    @Override
    public void onLoad(Invincibility invincibility) {
        ImageView view = new ImageView(invincibilityImage);
        addEntity(invincibility, view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }
    public void onLoad(Hover hover) {
        ImageView view = new ImageView(hoverImage);
        addEntity(hover, view);
    }
    
    public void onLoad(Pit pit) {
        ImageView view = new ImageView(pitImage);
        addEntity(pit, view);
    }

    public void onLoad(Door door) {
    	ImageView view;
    	if(door.isState()) {
    		view = new ImageView(opendoorImage);
    		//System.out.println("Loading opened door");
    	}
    	else {
    		//System.out.println("Loading closed door");
    		view = new ImageView(closeddoorImage);
    	}
        addEntity(door, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }



    public List<ImageView> refreshImageEntities(Dungeon dungeon) {
        for (Entity entity : dungeon.getEntities()) {
            int x = entity.getX();
            int y = entity.getY();
            int id;
            String type = entity.getClass().getSimpleName().toLowerCase();

            switch (type) {
                case "player":
                    Player player = new Player(dungeon, x, y);
                    dungeon.setPlayer(player);
                    onLoad(player);
                    break;
                case "wall":
                    Wall wall = new Wall(x, y);
                    onLoad(wall);
                    break;
                case "exit":
                    Exit exit = new Exit(x, y);
                    onLoad(exit);
                    break;
                case "boulder":
                    Boulder boulder = new Boulder(x, y);
                    onLoad(boulder);
                    break;
                case "switch":
                    Switch sw = new Switch(x, y);
                    onLoad(sw);
                    break;
                case "bomb":
                    Bomb bomb=(Bomb) entity;
                    onLoad(bomb);
                    break;
                case "enemy":
                    Enemy enemy = new Enemy(dungeon, x, y);
                    enemy.addEnemy();
                    onLoad(enemy);
                    break;
                case "invincibility":
                    Invincibility invincibility = new Invincibility(x, y);
                    onLoad(invincibility);
                    break;
                case "sword":
                    Sword sword = new Sword(x, y);
                    onLoad(sword);
                    break;
                case "treasure":
                    Treasure treasure = new Treasure(x, y);
                    onLoad(treasure);
                    entity = treasure;
                    break;
                case"pit":
                	Pit pit = new Pit(x, y);
                    onLoad(pit);
                    entity = pit;
                    break;
                case"hover":
                	Hover hover = new Hover(x, y);
                    onLoad(hover);
                    entity = hover;
                    break;   
                case "key":
                    Key preKey = (Key) entity;
                    id = preKey.getId();
                    Key key = new Key(x, y, id);
                    onLoad(key);
                    break;
                case "door":
                    Door preDoor = (Door) entity;
                    id = preDoor.getId();
                    boolean set = preDoor.isState();
                    Door door = new Door(x, y, id, set);
                    onLoad(door);
                    break;
            }
        }
        return entities;
    }

	
}
