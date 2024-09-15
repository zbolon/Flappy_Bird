import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final int SCREEN_WIDTH = 500;
    private final int SCREEN_HEIGHT = 750;
    private int FPS = 60;

    private Key key = new Key();
    private Player player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 17 * 3, 12 * 3, key);
    private Obstacle[][] obstacles = new Obstacle[3][2];

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(key);
        setObstacles();
    }

    public void setObstacles(){
        obstacles[0][0] = new Obstacle(SCREEN_WIDTH, SCREEN_HEIGHT - 100, 50, 100);
    }

    public void update(){
        player.update();
        obstacles[0][0].update();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);
        obstacles[0][0].draw(g2);
    }

    public void run(){

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (true){
            repaint();

            update();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;


            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
