import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {
    private boolean jump;
    private boolean pressed;


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_SPACE) {
            jump = true;
            pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_SPACE){
            jump = false;
            pressed = false;
        }

    }


    public boolean getJump(){
        return jump;
    }

    public boolean isPressed() {
        return pressed;
    }
    public void setPressed(boolean pressed){
        this.pressed = pressed;
    }
}
