package Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyTank extends Tank {
    protected List<EnemyBullet> enemyBullets = new ArrayList<>();
    protected long delayTimeToFireBullet = 2800;
    protected long lastTimeFire;

    public EnemyTank() {
        images = new Image[4];
        images[LEFT] = new ImageIcon(
                getClass().getResource("/img/bossyellow_left.png")
        ).getImage();
        images[UP] = new ImageIcon(
                getClass().getResource("/img/bossyellow_up.png")
        ).getImage();
        images[DOWN] = new ImageIcon(
                getClass().getResource("/img/bossyellow_down.png")
        ).getImage();
        images[RIGHT] = new ImageIcon(
                getClass().getResource("/img/bossyellow_right.png")
        ).getImage();
    }

    public boolean canMove(List<EnemyTank> enemyTanks) {
        boolean isMove = super.canMove();
        if (isMove) {
            interWithSame(enemyTanks);
        }
        return isMove;
    }

    public void interactAllBulletWithItems(Item[][] items) {
        for (int i = 0; i < enemyBullets.size(); i++) {
            if (enemyBullets.get(i).interactWithItem(items)) {
                enemyBullets.remove(i);
                i--;
            }
        }
    }

    public void canShoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeFire >= delayTimeToFireBullet) {
            fireBullet();
            lastTimeFire = currentTime;
        }
    }

    private void interWithSame(List<EnemyTank> enemyTanks) {
        Rectangle re = new Rectangle(x, y, w, h);
        for (int i = 0; i < enemyTanks.size(); i++) {
            if (enemyTanks.get(i) == this) {
                continue;
            }
            Rectangle re1 = new Rectangle(enemyTanks.get(i).x, enemyTanks.get(i).y
                    , enemyTanks.get(i).w, enemyTanks.get(i).h);
            if (re.intersects(re1)) {
                backLastPosition();
                changOri();
                re = new Rectangle(x, y, w, h);
            }
        }
    }

    public void changeOriRandom() {
        if (new Random().nextInt(4000) == 1) {
            changOri();
        }
    }

    public void fireBullet() {

        EnemyBullet enemyBullet = new EnemyBullet();
        enemyBullet.setOri(ori);
        enemyBullet.setImage(
                new ImageIcon(Tank.class.getResource(
                        "/img/bullet.png"
                )).getImage()
        );
        enemyBullet.setDelay(delay /2 );
        int sizeBullet = 8;
        enemyBullet.setW(sizeBullet);
        enemyBullet.setH(sizeBullet);
        switch (ori) {
            case LEFT:
                enemyBullet.setX(x - sizeBullet);
                enemyBullet.setY(y + (w - sizeBullet) / 2);
                break;
            case RIGHT:
                enemyBullet.setX(x + w);
                enemyBullet.setY(y + (h - sizeBullet) / 2);
                break;
            case UP:
                enemyBullet.setX(x + (w - sizeBullet) / 2);
                enemyBullet.setY(y - sizeBullet);
                break;
            case DOWN:
                enemyBullet.setX(x + (w - sizeBullet) / 2);
                enemyBullet.setY(y + h);
                break;
        }
        enemyBullets.add(enemyBullet);
    }

    public void moveAllBullet() {
        for (int i = 0; i < enemyBullets.size(); i++) {
            enemyBullets.get(i).canMove();
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        super.draw(graphics2D);
        for (int i = 0; i < enemyBullets.size(); i++) {
            enemyBullets.get(i).draw(graphics2D);
        }
    }

    public boolean interactAllBulletWithHome(Item home) {
        for (int i = 0; i < enemyBullets.size(); i++) {
            if (enemyBullets.get(i).isInteract(home)) {
                return true;
            }
        }
        return false;
    }

    public void interactAllBulletWithMyTank(MyTank myTank) {
        for (int i = 0; i < enemyBullets.size(); i++) {
            boolean isInteract = enemyBullets.get(i).enemyBulletInteractWithMyTank(myTank);
            if (isInteract) {
                System.out.println("Game Over");
                enemyBullets.remove(i);
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
    public void myBulletIsNear(){

    }
}
