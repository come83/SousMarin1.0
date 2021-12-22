package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WarShip {

    public int x, y;
    public int speed;
    public BufferedImage ship_droite, ship_gauche;
    public String direction;

    GamePannel gp;
    KeyHandler keyH;


    public WarShip(GamePannel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getShipImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDefaultValues() {
        x = 800;
        y = 100;
        speed = 10;
        direction = "left";
    }

    public void getShipImage() {
        try {
            ship_droite = ImageIO.read(getClass().getResource("picture/ship_1_droite.png"));
            ship_gauche = ImageIO.read(getClass().getResource("picture/ship_1_gauche.png"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            if(y<=0)y=0;
            else{
                direction = "up";
                y -= speed;
            }
        } else if (keyH.downPressed) {
            if(y>680)y=680;
            else{
                direction = "down";
                y += speed;
            }
        } else if (keyH.leftPressed) {
            if(x<=0)x=0;
            else{
                direction = "left";
                x -= speed;
            }

        } else if (keyH.rightPressed) {
            if(x>1130)x=1130;
            else{
                direction = "right";
                x += speed;
            }

        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
            case "down":
            case "left":
                image = ship_gauche;
                break;
            case "right":
                image = ship_droite;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
