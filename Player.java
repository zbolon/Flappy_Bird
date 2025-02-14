import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
    private BufferedImage[] image = new BufferedImage[3];
    private Key key;
    private long start_time = System.currentTimeMillis();
    private long current_time;

    private double Voy; //(m/s)
    private final double a = 9.81 * 10; // (m/s/s)
    private double y, Yo;
    private double t;
    private boolean jump_hold = false;
    private int i, num;


    //yVel stays the same and yAccel
    //need to know how much time has passed since space bar was pressed (in seconds)

    /*
        PLAYER IS NOT CENTERED
     */

    public Player(int x, int y, int width, int height, Key key){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        this.key = key;

        try{
            image[0] = ImageIO.read(new File("Images/bird.png"));
            image[1] = ImageIO.read(new File("Images/bird2.png"));
            image[2] = ImageIO.read(new File("Images/bird3.png"));
        } catch (IOException e){
            System.out.println("Error");
        }
    }

    public void draw(Graphics2D g2){

        switch (i){
            case (0):
                g2.drawImage(image[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case (1):
                g2.drawImage(image[1], getX(), getY(), getWidth(), getHeight(), null);
            case (2):
                g2.drawImage(image[2], getX(), getY(), getWidth(), getHeight(), null);

        }

    }

    public void update(){

        //always add num

        //check if num is within a range,
        // whatever range it is within set i to that
        num++;

        if (num < 10){
            i = 0;
        } else if (num < 20){
            i = 1;
        } else if (num < 30){
            i = 2;
        } else {
            num = 0;
        }

        // just some physics here
        if (key.getJump() && !jump_hold){
            Yo = getY();
            start_time = System.currentTimeMillis();
            Voy = -9.81 * 12.5;
            jump_hold = true;
        } else if (!key.getJump()){
            jump_hold = false;
        }

        current_time = System.currentTimeMillis();

        t = (current_time - start_time) / 250.0;

        y = 0.5 * a * Math.pow(t, 2) + Voy * t + Yo;
        setY((int)y);

    }
}
