import javax.swing.*;
import java.awt.*;

public class GameFrame {

    public GameFrame(){
        JFrame frame = new JFrame();
        GamePanel gp = new GamePanel();

        frame.add(gp);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gp.run();

    }
}
