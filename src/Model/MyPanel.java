package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements Const, ActionListener, KeyListener {
    private ManageGame manageGame;
    private JButton buttonBack;
    private IOpenPlay inter;
    private boolean isRunning;
    private SoundManager soundBG;
    public void setInter(IOpenPlay inter){
        this.inter = inter;
    }

    public MyPanel() {
        setBackground(Color.BLACK);
        //setSize(800, 800);
        setSize(WIDTH_F, HEIGHT_F);
        manageGame = new ManageGame();
        setRequestFocusEnabled(true);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        setLayout(null);
        isRunning = true;
        soundBG = new SoundManager("/sound/bg.wav");
        soundBG.loop(-1);
        createThread();
        initPlay();

    }
    private void initPlay() {
        buttonBack = new JButton("Back");
        buttonBack.setSize(100, 50);
        buttonBack.setLocation(
                (Const.WIDTH_F - 100)/2, 0
        );
        buttonBack.setActionCommand("Back");
        buttonBack.addActionListener(this);
        add(buttonBack);
    }
    private void createThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    manageGame.stepThread();
                    repaint();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void addKey() {

    }

    private void addBack() {
        JButton button = new JButton("Back");
        button.setSize(100, 50);
        add(button);
        button.addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        manageGame.draw(graphics2D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isRunning = false;
        soundBG.stop();
        inter.back();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        manageGame.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        manageGame.keyReleased(e.getKeyCode());
    }
}
