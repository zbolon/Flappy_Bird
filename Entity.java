import java.awt.*;

abstract class Entity {
    private int x, y;
    private int width, height;


    public abstract void draw(Graphics2D g2);
    public abstract void update();

    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public int getX(){return x;}
    public int getY(){return y;}

    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}

}
