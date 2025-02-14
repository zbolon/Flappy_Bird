import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Score {
    private int score;
    private int SCREEN_WIDTH;

    private BufferedImage image;

    public Score(int SCREEN_WIDTH){
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        try{
            image = ImageIO.read(new File("Images/flappy_numbers_sprite.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        score += 1;
    }

    public void draw(Graphics2D g2){

        //loop through score until can't / by 10;
        // take the remainder (%) and draw it
        int temp_score = score;
        //System.out.println(score);

        if (score == 0){
            g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4),100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4),100/2 + 100,0,0,75,100,null);
        }
        int offset = 0;
        while (temp_score != 0){
            int num = temp_score % 10;
            switch (num){

                //need to add an offset to dx1 and dx2 based on 2 things

                case 0:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4),100 + offset,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,0,0,75,100,null);
                    break;
                case 1:
                    g2.drawImage(image,SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset, 100/2 + 100,75,0,150,100,null);
                    break;
                case 2:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,150,0,225,100,null);
                    break;
                case 3:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,225,0,300,100,null);
                    break;
                case 4:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,0,100,75,200,null);
                    break;
                case 5:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,75,100,150,200,null);
                    break;
                case 6:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,150,100,225,200,null);
                    break;
                case 7:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,225,100,300,200,null);
                    break;
                case 8:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,0,200,75,300,null);
                    break;
                case 9:
                    g2.drawImage(image, SCREEN_WIDTH / 2 - (75 / 4) + offset,100,(SCREEN_WIDTH / 2) + (75 / 2) - (75 / 4) + offset,100/2 + 100,75,200,150,300,null);
                    break;
            }

            temp_score = temp_score / 10;
            offset -= 40;
        }


    }
}
