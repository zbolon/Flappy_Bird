import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Queue {
    private BufferedImage upright, downright;
    private final int SCREEN_WIDTH = 500;
    private final int SCREEN_HEIGHT = 750;
    private int headNum = 0;

    private Obstacle[][] obstacles = new Obstacle[3][2];
    private Obstacle[] head = null;

    public Queue(){
        try{
            upright = ImageIO.read(new File("Images/upright_pipe.png"));
            downright = ImageIO.read(new File("Images/downright_pipe.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i < obstacles.length; i++){
            int y = (int)(Math.random() * 520);
            //bottom pipe can be from 750 to 200
            obstacles[i][0] = new Obstacle(SCREEN_WIDTH + (SCREEN_WIDTH * i), SCREEN_HEIGHT - y - 50, 100, 600, upright, SCREEN_HEIGHT, true);
            //200 between pipes
            obstacles[i][1] = new Obstacle(SCREEN_WIDTH + (SCREEN_WIDTH * i), SCREEN_HEIGHT - y - 200 - 600, 100, 600, downright, obstacles[i][0]);
        }
        // random right now
        head = obstacles[0];
    }

    public Obstacle[] dequeue(){
        // instead of deleting, just move the pointer
        Obstacle[] oldHead = head;

        switch (headNum){
            case 0:
                System.out.println("HEAD = 0");
                head = obstacles[1];
                headNum = 1;
                break;
            case 1:
                System.out.println("HEAD = 1");
                head = obstacles[2];
                headNum = 2;
                break;
            case 2:
                System.out.println("HEAD = 2");
                headNum = 0;
                head = obstacles[0];
                break;
            default:
                System.out.println("Might be an error in Queue");
                head = obstacles[0];
                break;

        }

        return oldHead;
    }

    public Obstacle[] peak(){
        return head;
    }

    public Obstacle[][] getObstacles(){
        return obstacles;
    }



    // I need this to be an array which will push to the end
    // remove from the front
    // and peak from the front
}
