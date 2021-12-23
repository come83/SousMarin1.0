package game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NuclearShip {

    public int x, y;
    public int speed;
    public BufferedImage ship_droite, ship_gauche;
    public String direction;

    GamePannel gp;
    KeyHandler keyH;

    public NuclearShip(GamePannel gp, KeyHandler keyH) {
        this.gp=gp;
        this.keyH=keyH;
        setDefaultValues();
        getShipImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String getDirection() {
        return direction;
    }


    public void setDefaultValues() {
        x = 100 ;
        y = 100 ;
        speed = 10;
        direction = "right";
    }


    public void getShipImage(){
        try {
            ship_droite = ImageIO.read(getClass().getResource("picture/ship_2_droite.png"));
            ship_gauche = ImageIO.read(getClass().getResource("picture/ship_2_gauche.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void update() {

        if(keyH.ZPressed){
            if(getY()<=0)setY(0);
            else{
                direction="up";
                setY((getY()-speed));
            }
        }
        else if(keyH.SPressed){
            if(getY()>=680)setY(680);
            else{
                direction="down";
                setY((getY()+speed));
            }
        }
        else if(keyH.QPressed){
            if(getX()<=0)setX(0);
            else{
                direction="left";
                setX((getX()-speed));
            }
        }
        else if(keyH.DPressed){
            if(getX()>1130)setX(1130);
            else{
                direction="right";
                setX((getX()+speed));
            }
        }
       // System.out.println("x"+getX());
       // System.out.println("y"+getY());
    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
            case "down":
            case "right":
                image = ship_droite;
                break;
            case "left":
                image = ship_gauche;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
