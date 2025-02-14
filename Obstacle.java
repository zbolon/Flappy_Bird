import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends Entity {
    private int SCREEN_HEIGHT;
    private int tempY;
    private boolean status;
    private boolean top;

    private BufferedImage image;
    private Obstacle partner;

    public Obstacle(int x, int y, int width, int height, BufferedImage image, int SCREEN_HEIGHT, boolean top){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        this.image = image;
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        this.status = false;
        this.top = top;
    }

    public Obstacle(int x, int y, int width, int height, BufferedImage image, Obstacle partner){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        this.image = image;
        this.partner = partner;
        this.top = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
        // to save memory and time, draw only parts shown on screen
    }


    public boolean check_position(){
        if (getX() == -getWidth()){


            //if speed of obstacles increases, then the new x_position decreases
            setX(1500 - 100);

            //it's because I make both pipes random values

            // new y - value
            if (partner == null){
                tempY = (int)(Math.random() * 520);
                //bottom pipe
                setY(SCREEN_HEIGHT - tempY - 50);

            } else {
                //top pipe
                setY(partner.SCREEN_HEIGHT - partner.getTempY() - 200 - 600);

            }
            status = false;
            return true;
        }
        return false;
    }

    public void move(){
        setX(getX() - 2);
    }
    public void update(){};

    public boolean updateN() {
        boolean move = check_position();
        move();
        return move;
        //gotta change the y-value
    }

    public int getTempY(){
        return tempY;
    }
    public int getSCREEN_HEIGHT(){
        return SCREEN_HEIGHT;
    }

    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getTop(){
        return top;
    }
}
