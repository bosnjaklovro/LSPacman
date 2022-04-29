
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiPlayer extends Pane {
    private Pacman pacman = null;
    private Ghost ghost = null;
    private ArrayList<Donut> donuts = null;

    public Image map = null;

    public MultiPlayer() {

        try {
            Socket socket = new Socket("localhost", 4444);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        
        // create the Layout where we can put scene elements, the main layout
        // layout are used for automatic positioning elements inside the scene, for
        // example, TOP, LEFT, RIGHT, CENTER

        // create a scene with a specific size (width, height), connnect with the layout
        try {
            map = new Image(new FileInputStream("assets/images/map.png"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        init();

        // Update the animation timer.
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                pacman.update();
                ghost.update();
                for(Donut donut:donuts) {
                    if(pacman.colidesWith(donut)) {
                        donut.setVisible(false);
                        donuts.remove(donut);
                        System.out.println("Donut eaten " + donuts.size());
                    }
                }
                if(donuts.size() == 0) {
                    Platform.runLater(() -> { 
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("You Have WOn!!!");
                        alert.setHeaderText("Game Well DOne");
                        alert.setContentText("Score: " + donuts.size());
                        alert.showAndWait();
                        init();
                        this.start();
                    });
                    this.stop();
                }
                if(ghost.colidesWith(pacman)) {
                    System.out.println("Game Over");
                    System.out.println("Score: " + donuts.size());
                    Platform.runLater(() -> { 
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Game Over");
                        alert.setHeaderText("Game Over");
                        alert.setContentText("Score: " + donuts.size());
                        alert.showAndWait();
                        init();
                        this.start();
                    });
                    this.stop();
                }
            }
        };

        // starts the timer
        timer.start();
    }

    public void init() {
        pacman = new Pacman(map);
        ghost = new Ghost(map);
        donuts = new ArrayList<>();
        
        ImageView mapView = new ImageView(map);
        this.getChildren().clear();
        this.getChildren().add(mapView);

        for(int i=0;i<100;i++) {
            Donut donut = new Donut(map);
            this.getChildren().add(donut);
            donuts.add(donut);
        }

        this.getChildren().addAll(pacman, ghost);
    }

    /**
     * Controls the movement of the pacman.
     * @param Scene scene to control
     * @param KeyEvent event to control
     */
    public void handleMovement(Scene scene) {
        System.out.println("handleMovement");
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP:
                case W:
                    pacman.goUP();
                    break;
                case DOWN:
                case S:
                    pacman.goDOWN();
                    break;
                case LEFT:
                case A:
                    pacman.goLEFT();
                    break;
                case RIGHT:
                case D:
                    pacman.goRIGHT();
                    break;
                default:
                    break;
            }
        });
    }
}