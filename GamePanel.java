import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private final int SCREEN_WIDTH = 500;
    private final int SCREEN_HEIGHT = 750;
    private final int FPS = 60;

    private Key key = new Key();
    private Player player = new Player(SCREEN_WIDTH / 2 - (17 * 3 / 2), 0, 17 * 3, 12 * 3, key);
    //private Obstacle[][] obstacles = new Obstacle[3][2];
    private Queue obstacles = new Queue();
    private Score score = new Score(SCREEN_WIDTH);
    private BufferedImage upright, downright;

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(key);
        this.setBackground(Color.cyan);
        setObstacles();
    }

    public void setObstacles(){
        try{
            upright = ImageIO.read(new File("Images/upright_pipe.png"));
            downright = ImageIO.read(new File("Images/downright_pipe.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i < obstacles.getObstacles().length; i++){
            int y = (int)(Math.random() * 520);
            obstacles.getObstacles()[i][0] = new Obstacle(SCREEN_WIDTH + (SCREEN_WIDTH * i), SCREEN_HEIGHT - y - 50, 100, 600, upright, SCREEN_HEIGHT, true);
            //200 between pipes
            obstacles.getObstacles()[i][1] = new Obstacle(SCREEN_WIDTH + (SCREEN_WIDTH * i), SCREEN_HEIGHT - y - 200 - 600, 100, 600, downright, obstacles.getObstacles()[i][0]);
        }
    }

    public boolean checkCollision(){
        // update and check if it hit
        if (player.getY() > SCREEN_HEIGHT - player.getHeight()){
            return true;
        }

        // check the front of the queue
        // get the head array
        Obstacle[] toCheck = obstacles.peak();
        // 0 is bottom, 1 is top

        if (toCheck[0].getX() < player.getX() + player.getWidth() / 2){
            if (toCheck[0].getX() + toCheck[0].getWidth() > player.getX() + player.getWidth() / 2) {

                // should check if the x is within the pipe
                // if player.getY() + player.getHeight() > bottom pipe.getY()
                    // game over
                // if player.getY() < top pipe.getY() + height
                    // game over
                int lowRange = toCheck[1].getY() + toCheck[0].getHeight();
                int highRange = toCheck[0].getY();
                if (player.getY() < lowRange || player.getY() + player.getHeight() > highRange){
                    return true;
                }
            }
        }
        // first check if the bird is close enough to touch a pipe
            // else do nothing
        // if it is
            // check if it touching the side of the pipe
        // if it isn't
            // check if it is between the 2 pipes

        return false;

    }


    public boolean update(){
        player.update();
        boolean moveHead = false;
        for (int i = 0; i < obstacles.getObstacles().length; i++) {
            for (int j = 0; j < obstacles.getObstacles()[i].length; j++) {
                if(obstacles.getObstacles()[i][j].updateN()){
                    moveHead = true;
                    System.out.println("MOVE HEAD");
                }
            }
        }
        if (moveHead){
            obstacles.dequeue();
        }

        boolean update_score = false;
        boolean hit = checkCollision();
        if (hit){
            return false;
        } else {
            if (!obstacles.peak()[0].getStatus()){
                // check if passed bird
                if (obstacles.peak()[0].getX() + obstacles.peak()[0].getWidth() < player.getX()){
                    obstacles.peak()[0].setStatus(true);
                    obstacles.peak()[1].setStatus(true);
                    score.update();
                }
            }
            // check status of head
            // if false
                // check if passed bird
                    // if it did, update and set status to false

            //score.update();
        }

        /*
        for (int i = 0; i < obstacles.length; i++){
            for (int j = 0; j < obstacles[i].length; j++){
                obstacles[i][j].update();

                // if player touches top or bottom
                if (obstacles[i][j].getTop() == true) {
                    if (player.getY() + player.getHeight() > obstacles[i][j].getY()){
                        //System.exit(0);
                    }
                } else {
                    if (player.getY() < obstacles[i][j].getY() + obstacles[i][j].getHeight()){
                        //if (player.getX())
                    }
                }


                if (obstacles[i][j].getX() + obstacles[i][j].getWidth() < player.getX() && obstacles[i][j].getStatus() == false){
                    update_score = true;
                    obstacles[i][j].setStatus(true);
                }

            }
        }

         */
        return true;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.green);
        for (int i = 0; i < obstacles.getObstacles().length; i++){
            for (int j = 0; j < obstacles.getObstacles()[i].length; j++){
                if (obstacles.getObstacles()[i][j].getX() <= SCREEN_WIDTH) {
                    obstacles.getObstacles()[i][j].draw(g2);
                }
            }
        }
        g2.setColor(new Color(108, 67, 30));
        g2.fillRect(0,SCREEN_HEIGHT- 30, SCREEN_WIDTH, 30);

        score.draw(g2);
        player.draw(g2);

        g2.dispose();
    }

    public void run(){

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (update()){
            repaint();

            //update();

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
        // this is the pause menu

        System.exit(0);
    }

}
