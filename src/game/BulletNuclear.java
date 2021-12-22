package game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BulletNuclear {

    public int x, y;
    public int speed;
    public BufferedImage bullet;
    public String direction;
    int toto=0;
    int titi=0;
    GamePannel gp;
    KeyHandler keyH;

    public BulletNuclear(GamePannel gp, KeyHandler keyH, int x, int y, String direction, NuclearShip nuclearShip){
        this.gp=gp;
        this.keyH=keyH;
        this.x=x;
        toto = x;
        this.y=y;
        titi = y;
        this.direction=direction;
        setSpeed();
    }

    public int toto(int toto){
        return toto;
    }
    public int titi(int titi){
        return titi;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setDefaultValues(){}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSpeed(){
        speed=10;
    }

    public void getBulletNuclearImage(){
        try {
            bullet = ImageIO.read(getClass().getResource("picture/missile_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.spacePressed){
            if(direction=="right"){
                getBulletNuclearImage();
                toto += speed;
            }
            else if(direction=="left"){
                getBulletNuclearImage();
                toto -= speed;
            }
            else if(direction=="up"){
                getBulletNuclearImage();
                toto += speed/2;
                titi -= speed/2;
            }
            else if(direction=="down"){
                getBulletNuclearImage();
                toto += speed/2;
                titi += speed/2;
            }
        }
    }


    public void draw(Graphics2D g2){
        BufferedImage image = bullet;
        System.out.println("draw x"+toto);
        System.out.println("draw y"+titi);
        g2.drawImage(image, toto, titi, gp.tileSize, gp.tileSize, null);
    }
}
