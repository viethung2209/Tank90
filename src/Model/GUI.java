package Model;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private MyPanel gamePanel;
    private MenuPanel menuPanel;
    private IOpenPlay inter;
    public GUI() {
        setSize(Const.WIDTH_F, Const.HEIGHT_F);
        setTitle("Tank_Batlle");
        setIconImage(
                new ImageIcon(
                        GUI.class.getResource(
                                "/img/bg_menu.png"
                        )
                ).getImage()
        );
        Insets insets = Toolkit.getDefaultToolkit()
                .getScreenInsets(getGraphicsConfiguration());
        setSize(Const.WIDTH_F+insets.left + insets.right
                , Const.HEIGHT_F + insets.top + insets.bottom);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        menuPanel = new MenuPanel();
        //add(menuPanel);
        inter = new IOpenPlay() {
            @Override
            public void openPlay() {
                changeComponent();
            }

            @Override
            public void back() {
            addGame();
            }
        };
        menuPanel.setInter(inter);
        add(menuPanel);
    }
    private void addGame() {
        remove(gamePanel);
        gamePanel = null;
        menuPanel = new MenuPanel();
        menuPanel.setInter(inter);
        add(menuPanel);
        repaint();
    }
    public void changeComponent(){
        remove(menuPanel);
        gamePanel = new MyPanel();
        gamePanel.setInter(inter);
        gamePanel.requestFocus();
        add(gamePanel);
        gamePanel.requestFocus();
        menuPanel = null;
        repaint();

    }
}
