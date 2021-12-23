package game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Explosion {

    public int x, y ;
    public BufferedImage explosion;

    GamePannel gp;


    public Explosion(GamePannel gp, int x, int y) {
        this.gp = gp ;
        this.x=x;
        this.y=y;
    }

    public void draw(Graphics2D g2) {
        // pas besoin du try catch avec le throws
        try {
            explosion = ImageIO.read(getClass().getResource("picture/explosion.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2.drawImage(explosion, x, y, gp.tileSize*5, gp.tileSize*5, null);
    }

}
