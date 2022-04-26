
/**
* JavaFX Symple GUI, example 1.
* 
* @author David Patric, Alan Mutka
* @version 2205
*/

import java.io.FileInputStream;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.image.renderable.RenderContext;

public class View extends Application {

   Scene scene = null;
   private Pacman pacman = null;
   private Ghost ghost = null;

   public static void main(String[] args) {
      // method inside the Application class, it will setup our program as a JavaFX
      // application
      // then the JavaFX is ready, the "start" method will be called automatically
      launch(args);
   }

   @Override
   public void start(Stage _stage) throws Exception {

      ///////////////////////// Setting window properties
      // set the window title
      _stage.setTitle("Pac-Man");

      // create the Layout where we can put scene elements, the main layout
      // layout are used for automatic positioning elements inside the scene, for
      // example, TOP, LEFT, RIGHT, CENTER
      StackPane root = new StackPane();

      // create a scene with a specific size (width, height), connnect with the layout
      scene = new Scene(root, 720, 720);

      Image map = new Image("map.png");
      BackgroundImage bMap = new BackgroundImage(map, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

      Background bg = new Background(bMap);
      root.setBackground(bg);

      pacman = new Pacman();
      ghost = new Ghost();

      root.getChildren().addAll(pacman, ghost);

      // Update the animation timer.
      AnimationTimer timer = new AnimationTimer() {
         public void handle(long now) {
            pacman.update();

         }
      };
      // starts the timer
      timer.start();

      handleMovement();
      _stage.setScene(scene);
      _stage.show();

   }

   /**
    * Controls the movement of the pacman.
    */
   public void handleMovement() {
      scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
         switch (event.getCode()) {
            case UP:
            case W:
               System.out.println("UP");
               pacman.setPositionY(pacman.getPositionY() - 3);
               break;
            case DOWN:
            case S:
               pacman.setPositionY(pacman.getPositionY() + 3);
               System.out.println("DOWN");
               break;
            case LEFT:
            case A:
               pacman.setPositionX(pacman.getPositionX() - 3);
               System.out.println("LEFT");
               break;
            case RIGHT:
            case D:
               pacman.setPositionX(pacman.getPositionX() + 3);
               System.out.println("RIGHT");
               break;
            default:
               break;
         }
      });
   }

   /**
    * ghost moves to the left side of the screen for 2 seconds
    */
   public void ghostMoveLeft() {
      ghost.setPositionX(ghost.getPositionX() - 3);
      

   

   }
}
