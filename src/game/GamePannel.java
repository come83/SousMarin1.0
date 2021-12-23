package game;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Runnable pour implémenter en thread
public class GamePannel extends JPanel implements Runnable{

    final int originalTileSize = 16;
    final int scale = 3 ;

    public int tileSize = originalTileSize*scale;
    final int maxScreenCol = 25;
    final int maxScreenRow = 15;
    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeight = tileSize*maxScreenRow;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    NuclearShip nuclearShip = new NuclearShip(this,keyH);
    WarShip warShip = new WarShip(this, keyH);

    List<BulletNuclear> lesBulletNuclear = new ArrayList<BulletNuclear>();
    List<BulletWar> lesBulletWar = new ArrayList<BulletWar>();

    public boolean collisonNuclear = false;
    public boolean collisionWar = false;
    public boolean isExplosed = false;
    Explosion explosion;

    public GamePannel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread =  new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/(double)FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            update();
            repaint();
            if(collisionWar || collisonNuclear){
                System.out.println("fin partie ");

                result();
                repaint();
                break;
            }

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void result(){
        if(collisonNuclear){
            System.out.println("Le Joueur Gauche Gagne ! ");
            explosion = new Explosion(this,warShip.getX(),warShip.getY());
            isExplosed = true;
         //   lesBulletNuclear.remove((lesBulletNuclear.size()-1));

        }
        else if(collisionWar){
            System.out.println("Le Joueur Droit Gagne ! ");
            explosion = new Explosion(this,nuclearShip.getX(),nuclearShip.getY());
            isExplosed = true;
           // lesBulletWar.remove((lesBulletWar.size()-1  ));

        }
    }

    public void update() {

        nuclearShip.update();
        warShip.update();

        if(keyH.spacePressed) {
            int totox = nuclearShip.getX();
            int totoy = nuclearShip.getY();
            if(lesBulletNuclear.size()>keyH.spacePressedCount){
                lesBulletNuclear.remove((lesBulletNuclear.size()-1));
            }
            lesBulletNuclear.add(new BulletNuclear(this, keyH,totox,totoy,nuclearShip.getDirection(), nuclearShip));
            lesBulletNuclear.get(keyH.spacePressedCount-1).update();

          //  System.out.println("#########################################################");
          //  System.out.println("xb"+lesBulletNuclear.get(keyH.spacePressedCount-1).getX());
          //  System.out.println("yb"+lesBulletNuclear.get(keyH.spacePressedCount-1).getY());
          //  System.out.println("xs"+warShip.getX());
          //  System.out.println("yx"+warShip.getY());

            if( (lesBulletNuclear.get(keyH.spacePressedCount-1).getX()  <= (warShip.getX() + 25))  &&
                    (lesBulletNuclear.get(keyH.spacePressedCount-1).getX() >= (warShip.getX() - 25)) &&
                    (lesBulletNuclear.get(keyH.spacePressedCount-1).getY() <= (warShip.getY() + 25)) &&
                    (lesBulletNuclear.get(keyH.spacePressedCount-1).getY() >= (warShip.getY() - 25))
            ){
                System.out.println("touché");
                collisonNuclear = true;
            }

        }
        if(keyH.controlPressed) {
            int titix = warShip.getX();
            int titiy = warShip.getY();
            if(lesBulletWar.size()>keyH.controlPressedCount){
                lesBulletWar.remove((lesBulletWar.size()-1));
            }
            lesBulletWar.add(new BulletWar(this, keyH,titix,titiy,warShip.getDirection(), warShip));
            lesBulletWar.get(keyH.controlPressedCount-1).update();

            if( (lesBulletWar.get(keyH.controlPressedCount-1).getX()  <= (nuclearShip.getX() + 25))  &&
                    (lesBulletWar.get(keyH.controlPressedCount-1).getX() >= (nuclearShip.getX() - 25)) &&
                    (lesBulletWar.get(keyH.controlPressedCount-1).getY() <= (nuclearShip.getY() + 25)) &&
                    (lesBulletWar.get(keyH.controlPressedCount-1).getY() >= (nuclearShip.getY() - 25))
            ){
                System.out.println("touché");
                collisionWar = true;
            }

        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        nuclearShip.draw(g2);
        warShip.draw(g2);
        if(isExplosed){
            explosion.draw(g2);
        }

        if(keyH.spacePressed){
            lesBulletNuclear.get(keyH.spacePressedCount-1).draw(g2);
        }
        if(keyH.controlPressed){
            lesBulletWar.get(keyH.controlPressedCount-1).draw(g2);
        }
        g2.dispose();
    }
}
