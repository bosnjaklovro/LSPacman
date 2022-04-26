import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ghost extends ImageView{
    private int postionX;
    private int postionY;
    private int rotation;
    private int speed;

    private Image image = null;

    public Ghost() {
        this.postionX = 0;
        this.postionY = 0;

        image = new Image("GhostOrange.png");
        this.setImage(image);
    }

    public void update() {
        postionX += (int) (Math.random() * getGhostWidth() / 30);
        postionY += (int) (Math.random() * getGhostHeight() / 30);
        this.setTranslateX(postionX);
        this.setTranslateY(postionY);
        this.setRotate(rotation);

        if (postionX > 800)
            postionX = 0;
        if (postionY > 500)
            postionY = 0;
            rotation += 1;

    } // end update()

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

    public int getGhostWidth() {
        return (int) this.getImage().getWidth();
    }

    public int getGhostHeight() {
        return (int) this.getImage().getHeight();
    }

    public void setPositionX(int i) {
    }



    
}
