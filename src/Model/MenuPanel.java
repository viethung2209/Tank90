package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
//    private GUI gui;
    private JButton buttonPlay;
    private IOpenPlay inter;
    public void setInter(IOpenPlay inter){
        this.inter = inter;
    }
    public MenuPanel() {
        setSize(Const.WIDTH_F, Const.HEIGHT_F);
        setLayout(null);
        buttonPlay = new JButton("Play");
        buttonPlay.setSize(100, 100);
        buttonPlay.setLocation(
                (Const.WIDTH_F - 100)/2, 200
        );
        buttonPlay.setActionCommand("PLAY");
        buttonPlay.addActionListener(this);
        add(buttonPlay);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        Image img = new ImageIcon(
                getClass().getResource("/img/bg.jpg")
        ).getImage();
        g2d.drawImage(img, 0, 0, Const.WIDTH_F, Const.HEIGHT_F, null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = e.getActionCommand();
        switch (id) {
            case "PLAY":
//                gui=new GUI();
//                gui.changeComponent();
                inter.openPlay();
                break;
        }
    }
}
