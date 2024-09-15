import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Player extends Entity {
    private BufferedImage image;
    private Key key;
    private long start_time = System.currentTimeMillis();
    private long current_time;

    private final double yVel = 500; //(m/s)
    private final double yAcc = 9.81 * 4; // (m/s/s)
    private double y, yo;
    private double t;


    //yVel stays the same and yAccel
    //need to know how much time has passed since space bar was pressed (in seconds)


    public Player(int x, int y, int width, int height, Key key){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        this.key = key;

        try{
            image = ImageIO.read(new File("/Users/zachbolon/Java/Flappy Bird/Images/bird.png"));
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image,getX(), getY(), getWidth(), getHeight(), null);
    }

    public void update(){
        /*
        if (key.getJump() & !key.isPressed()){
            start_time = System.currentTimeMillis();
            // reset the time and jump up by Vo
            yo = getY();
            setY(getY() - 10);
            key.setPressed(true);

            //make it so that you can't jump, until you reach the max height

        } else {
            //do calculation
            // y= 0.5 * a * t^2 + Vot + Yo
            current_time = System.currentTimeMillis();
            t = (current_time - start_time) / 1000.0;
            System.out.println(t);
            y = (0.5 * yAcc * Math.pow(t, 2)) + yVel * t - yo;

            setY((int) y);
            //System.out.println(y);
        }*/

        if (key.isPressed()){
            setY(getY() - 10);
        } else {
            setY(getY() + 1);
        }



    }

}
