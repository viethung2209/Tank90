package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManageGame extends JPanel {
    private Item[][] items;
    private List<Item> listItemCanAppear;
    private MyTank player;
    private List<EnemyTank> enemyTanks;
    protected Item Home;
    private boolean isLeft, isRight, isUp, isDown, isSpace;

    public ManageGame() {
        items = new ReadFile().readAllItems("map3.txt");
        Home = new ReadFile().findHome("map3.txt");
        enemyTanks = new ArrayList<>();
        listItemCanAppear = new ReadFile().checkAppear(items, items.length - 7, items[0].length);
        createPlayer();
        randomAppearTank();
    }

    public void draw(Graphics2D graphics2D) {
        player.draw(graphics2D);
        for (int i = 0; i < enemyTanks.size(); i++) {
            enemyTanks.get(i).draw(graphics2D);
        }
        boolean check = false;
        for (int i = 0; i < Const.NUMBER_ROW; i++) {
            for (int j = 0; j < Const.NUMBER_COLUMN; j++) {
                if (items[i][j] != null) {
                    if (items[i][j].getTypeID() != 0) {
                        items[i][j].draw(graphics2D);
                    }
                }
            }
        }

    }

    private EnemyTank createEnemyTank(int x, int y, int ori, int delay) {
        EnemyTank enemyTank = new EnemyTank();
        enemyTank.setX(x);
        enemyTank.setY(y);
        enemyTank.setW(Const.SIZE_TIME * 2 - 8);
        enemyTank.setH(Const.SIZE_TIME * 2 - 8);
        enemyTank.setDelay(delay);
        enemyTank.setOri(ori);
        enemyTank.setDelayTimeToFireBullet(2800);
        return enemyTank;
    }

    private void createPlayer() {
        player = new MyTank();
        player.setX(Home.x - Const.SIZE_TIME * 3);
        player.setY(Home.y);
//        player.setW(Const.SIZE_TIME * 2 - 8);
//        player.setH(Const.SIZE_TIME + 2 - 8);
//        player.setSpeed(30);
//        player.setOri(Tank.UP);
//        player.setX((Const.NUMBER_ROW /2 - 4) * Const.SIZE_TIME);
//        player.setY((Const.NUMBER_COLUMN - 3) * Const.SIZE_TIME + 8);
        player.setW(Const.SIZE_TIME * 2 - 8);
        player.setH(Const.SIZE_TIME * 2 - 8);
        player.setDelay(30);
        player.setOri(Tank.UP);
    }

    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                isLeft = true;
                break;
            case KeyEvent.VK_UP:
                isUp = true;
                break;
            case KeyEvent.VK_DOWN:
                isDown = true;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = true;
                break;
            case KeyEvent.VK_SPACE:
                isSpace = true;
                break;

        }
    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                isLeft = false;
                break;
            case KeyEvent.VK_UP:
                isUp = false;
                break;
            case KeyEvent.VK_DOWN:
                isDown = false;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = false;
                break;
            case KeyEvent.VK_SPACE:
                isSpace = false;
                break;

        }
    }

    public void stepThread() {
        moveTank();
        if (isSpace) {
            player.canShoot();
        }
        player.moveAllBullet();
        player.interactAllBulletWithItems(items);
        player.interactTankWithItems(items);
        player.interactAllBulletWithEnemy(enemyTanks);
        boolean nearing = player.myBulletIsNearEnemy(enemyTanks);
        if (!nearing) {
            player.myTankAheadEnemyTank(enemyTanks, items);
        }
//        boolean isWithHome = player.interactAllBulletWithHome(Home);
//        if (isWithHome) {
//
//        }
        //checkToRemoveItem();
        stepEnemyTank();
        checkRemoveItem();
    }

    private void stepEnemyTank() {
        for (int i = 0; i < enemyTanks.size(); i++) {

            enemyTanks.get(i).canShoot();
            enemyTanks.get(i).moveAllBullet();
            enemyTanks.get(i).interactTankWithItems(items);
            enemyTanks.get(i).interactAllBulletWithItems(items);
            enemyTanks.get(i).changeOriRandom();
            enemyTanks.get(i).interactAllBulletWithMyTank(player);
            //boolean isWithHome = enemyTanks.get(i).interactAllBulletWithHome(Home);
//            if (isWithHome) {
//
//            }
            enemyTanks.get(i).canMove(enemyTanks);
            boolean isInteract = enemyTanks.get(i).interactTankWithItems(items);
            if (isInteract) {
                enemyTanks.get(i).changOri();
            }
        }
    }

    private void checkToRemoveItem() {
        long current = System.currentTimeMillis();
        for (int i = 0; i < Const.NUMBER_ROW; i++) {
            for (int j = 0; j < Const.NUMBER_COLUMN; j++) {
                if (items[i][j] != null) {
                    if (items[i][j].getTypeID() == Item.BRICK) {
                        items[i][j] = null;
                    }
                }
            }
        }
    }


    private void moveTank() {
        if (isLeft) {
            player.setOri(Tank.LEFT);
            player.canMove();
            return;
        }
        if (isUp) {
            player.setOri(Tank.UP);
            player.canMove();
            return;
        }
        if (isRight) {
            player.setOri(Tank.RIGHT);
            player.canMove();
            return;
        }
        if (isDown) {
            player.setOri(Tank.DOWN);
            player.canMove();
        }
    }

    public void randomAppearTank() {
        Random random = new Random();
        int count = 0;
        while (count <= 5) {
            int rd = random.nextInt(listItemCanAppear.size());
            Item item = listItemCanAppear.get(rd);
            enemyTanks.add(createEnemyTank(item.x, item.y, Tank.DOWN, 26));
            listItemCanAppear.remove(rd);
            count++;
        }
    }

    private void checkRemoveItem() {
        for (int i = 0; i < Const.NUMBER_ROW; i++) {
            for (int j = 0; j < Const.NUMBER_COLUMN; j++) {
                if (items[i][j] != null) {
                    if (items[i][j].isTouchDie()) {
                        items[i][j].getAnimation().updateCurrentIndex();
                    }
                    if (items[i][j].isDie()) {
                        items[i][j] = null;
                    }

                }
            }

        }
    }
}