package game;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Bataille navale");

        GamePannel gamePannel = new GamePannel();

        window.add(gamePannel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePannel.startGameThread();
    }
}









