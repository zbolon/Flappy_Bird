import java.awt.*;

public class Obstacle extends Entity {

    public Obstacle(int x, int y, int width, int height){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.fillRect(getX(),getY(),getWidth(), getHeight());
    }

    @Override
    public void update() {
        setX(getX() - 1);
    }
}
