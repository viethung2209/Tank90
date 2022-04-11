package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class MyTank extends Tank {
    protected List<MyBullet> myBullets = new ArrayList<>();
    private long delayTimeToFireBullet = 400;
    private long lastTimeFire;

    public MyTank() {
        images = new Image[4];
        images[LEFT] = new ImageIcon(
                getClass().getResource("/img/player_green_left.png")
        ).getImage();
        images[UP] = new ImageIcon(
                getClass().getResource("/img/player_green_up.png")
        ).getImage();
        images[RIGHT] = new ImageIcon(
                getClass().getResource("/img/player_green_right.png")
        ).getImage();
        images[DOWN] = new ImageIcon(
                getClass().getResource("/img/player_green_down.png")
        ).getImage();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        super.draw(graphics2D);
        for (int i = 0; i < myBullets.size(); i++) {
            myBullets.get(i).draw(graphics2D);
        }
    }

    public void canShoot() {
        MyBullet myBullet;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeFire >= delayTimeToFireBullet) {
            fireBullet();
            lastTimeFire = currentTime;
        }
    }

    public boolean interactAllBulletWithHome(Item home) {
        for (int i = 0; i < myBullets.size(); i++) {
            if (myBullets.get(i).isInteract(home)) {
                return true;
            }
        }
        return false;
    }

    public void interactAllBulletWithItems(Item[][] items) {
        for (int i = 0; i < myBullets.size(); i++) {
            if (myBullets.get(i).interactWithItem(items)) {
                myBullets.remove(i);
                i--;
            }
        }
    }

    public void moveAllBullet() {
        for (int i = 0; i < myBullets.size(); i++) {
            myBullets.get(i).canMove();
        }
    }

    public MyBullet fireBullet() {
        MyBullet myBullet = new MyBullet();
        myBullet.setOri(ori);
        myBullet.setImage(
                new ImageIcon(Tank.class.getResource(
                        "/img/bullet.png"
                )).getImage()
        );
        myBullet.setDelay(delay / 2);
        int sizeBullet = 8;
        myBullet.setW(sizeBullet);
        myBullet.setH(sizeBullet);
        switch (ori) {
            case LEFT:
                myBullet.setX(x - sizeBullet);
                myBullet.setY(y + (w - sizeBullet) / 2);
                break;
            case RIGHT:
                myBullet.setX(x + w);
                myBullet.setY(y + (h - sizeBullet) / 2);
                break;
            case UP:
                myBullet.setX(x + (w - sizeBullet) / 2);
                myBullet.setY(y - sizeBullet);
                break;
            case DOWN:
                myBullet.setX(x + (w - sizeBullet) / 2);
                myBullet.setY(y + h);
                break;
        }
        myBullets.add(myBullet);
       playSoundFire();
        return myBullet;
    }

    public void interactAllBulletWithEnemy(List<EnemyTank> enemyTanks) {
        for (int i = 0; i < myBullets.size(); i++) {
            boolean isInteract = myBullets.get(i).myBulletInteractWithEnemyTank(enemyTanks);
            if (isInteract) {
                myBullets.remove(i);
            }
        }

    }

    public boolean myBulletIsNearEnemy(List<EnemyTank> enemyTanks) {
        for (int i = 0; i < myBullets.size(); i++) {
            for (int j = 0; j < enemyTanks.size(); j++) {
                int Ix = enemyTanks.get(j).x + enemyTanks.get(j).w / 2;
                int Iy = enemyTanks.get(j).y + enemyTanks.get(j).h / 2;
                if (myBullets.get(i).getOri() == UP || myBullets.get(i).getOri() == DOWN) {
                    if (myBullets.get(i).x >= Ix && myBullets.get(i).x <= enemyTanks.get(j).x + enemyTanks.get(j).w) {
                        enemyTanks.get(j).setOri(Tank.LEFT);
                        return true;
                    } else if (myBullets.get(i).x <= Ix && myBullets.get(i).x >= enemyTanks.get(j).x) {
                        enemyTanks.get(j).setOri(Tank.RIGHT);
                        return true;
                    }
                }
                if (myBullets.get(i).getOri() == RIGHT || myBullets.get(i).getOri() == LEFT) {
                    if (myBullets.get(i).y >= Iy && myBullets.get(i).y <=   enemyTanks.get(j).y + enemyTanks.get(j).h) {
                        enemyTanks.get(j).setOri(UP);
                        return true;
                    } else if (myBullets.get(i).y < Iy && myBullets.get(i).y >= enemyTanks.get(j).y) {
                        enemyTanks.get(j).setOri(DOWN);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void myTankAheadEnemyTank(List<EnemyTank> enemyTanks, Item[][] items) {
        Line2D line2D;
        for (int i = 0; i < enemyTanks.size(); i++) {
            int check = 0;
            line2D = new Line2D.Double(enemyTanks.get(i).x, enemyTanks.get(i).y, x, y);
            for (int j = 0; j < Const.NUMBER_ROW; j++) {
                for (int k = 0; k < Const.NUMBER_COLUMN; k++) {
                    if (items[j][k] != null) {
                        if (items[j][k].getTypeID() == 0 || items[j][k].getTypeID() == Item.TREE) {
                            continue;
                        }
                        Rectangle rectangle = new Rectangle(items[j][k].x, items[j][k].y, items[j][k].w, items[j][k].h);
                        boolean isInteract = line2D.intersects(rectangle);
                        if (isInteract) {
                            check++;
                            enemyTanks.get(i).changeOriRandom();
                            break;
                        }
                    }
                }
                if (check != 0) {
                    break;
                }
            }
            if (check == 0) {
                int vertical = enemyTanks.get(i).x - x;
                int horizontal = enemyTanks.get(i).y - y;
                if (Math.abs(vertical) < w / 2) {
                    if (horizontal > 0) {
                        enemyTanks.get(i).setOri(UP);
                    } else enemyTanks.get(i).setOri(DOWN);
                }
                if (Math.abs(horizontal) < h / 2) {
                    if (vertical > 0) {
                        enemyTanks.get(i).setOri(LEFT);
                    } else enemyTanks.get(i).setOri(RIGHT);
                }
            }
        }
    }

    public long getDelayTimeToFireBullet() {
        return delayTimeToFireBullet;
    }

    public void setDelayTimeToFireBullet(long delayTimeToFireBullet) {
        this.delayTimeToFireBullet = delayTimeToFireBullet;
    }

    public long getLastTimeFire() {
        return lastTimeFire;
    }

    public void setLastTimeFire(long lastTimeFire) {
        this.lastTimeFire = lastTimeFire;
    }

}
