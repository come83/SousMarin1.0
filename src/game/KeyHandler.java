package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean ZPressed, QPressed, SPressed,DPressed;
    public boolean spacePressed, controlPressed;
    public int spacePressedCount = 0;
    public int controlPressedCount = 0;


    @Override
    public void keyTyped(KeyEvent e) {
        // obligé pour implementer keylistener
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
            spacePressedCount +=1;
        }
        if(code == KeyEvent.VK_CONTROL ){
            controlPressed = true;
            controlPressedCount +=1;
        }
        if(code == KeyEvent.VK_Z){
            ZPressed = true;
        }
        if(code == KeyEvent.VK_Q){
            QPressed = true;
        }
        if(code == KeyEvent.VK_S){
            SPressed = true;
        }
        if(code == KeyEvent.VK_D){
            DPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_Z){
            ZPressed = false;
        }
        if(code == KeyEvent.VK_Q){
            QPressed = false;
        }
        if(code == KeyEvent.VK_S){
            SPressed = false;
        }
        if(code == KeyEvent.VK_D){
            DPressed = false;
        }
    }
}
