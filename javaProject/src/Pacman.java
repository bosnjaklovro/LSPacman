import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class Pacman extends ImageView {
    private int postionX;
    private int postionY;
    private int rotation;
    private int speed;

    private Image image = null;

    public Pacman() {
        this.postionX = 0;
        this.postionY = 0;
        //animated gif of the pacman
        image = new Image("PacmanAnim.gif");
        this.setImage(image);
    }

    public void update() {
       // postionX += (int) (Math.random() * getPacmanWidth() / 30);
       // postionY += (int) (Math.random() * getPacmanHeight() / 30);
        this.setTranslateX(postionX);
        this.setTranslateY(postionY);
        //this.setRotate(rotation);

        if (postionX > 800)
            postionX = 0;
        if (postionY > 500)
            postionY = 0;
            rotation += 1;

    } // end update()

    /*
     * Accessors and mutators
     */

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPositionX() {
        return postionX;
    }

    public int getPositionY() {
        return postionY;
    }

    public int getRotation() {
        return rotation;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPositionX(int postionX) {
        this.postionX = postionX;
    }

    public void setPositionY(int postionY) {
        this.postionY = postionY;
    }

    public double getPacmanWidth(){
        return image.getWidth();
    }

    public double getPacmanHeight(){
        return image.getHeight();
    }
}
