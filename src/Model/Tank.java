package Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tank extends Obj2DMoveable {
    protected Image[] images;
    SoundManager soundFire;

    @Override
    public void setOri(int ori) {
        super.setOri(ori);
        image = images[ori];
    }


//    public void fireBullet() {
//        //Bullet bullet = new Bullet();
//        MyBullet myBullet = new MyBullet();
//        EnemyBullet enemyBullet = new EnemyBullet();
//        myBullet.setOri(ori);
//        enemyBullet.setOri(ori);
//        myBullet.setImage(
//                new ImageIcon(Tank.class.getResource(
//                        "/imgs/bullet.png"
//                )).getImage()
//        );
//        enemyBullet.setImage(
//                new ImageIcon(Tank.class.getResource(
//                        "/imgs/bullet.png"
//                )).getImage()
//        );
//        enemyBullet.setSpeed(speed*2);
//        myBullet.setSpeed(speed*2);
////        bullet.setOri(ori);
////        bullet.setImage(
////                new ImageIcon(Tank.class.getResource(
////                        "/imgs/bullet.png"
////                )).getImage()
////        );
////        bullet.setSpeed(speed*2);
//        int sizeBullet = 8;
////        bullet.setW(sizeBullet);
////        bullet.setH(sizeBullet);
//        switch (ori){
//            case LEFT:
////                bullet.setX(x - sizeBullet);
////                bullet.setY(y + (w - sizeBullet) / 2);
//                enemyBullet.setX(x - sizeBullet);
//                enemyBullet.setY(y + (w - sizeBullet) / 2);
//                myBullet.setX(x - sizeBullet);
//                myBullet.setY(y + (w - sizeBullet) / 2);
//                break;
//            case RIGHT:
////                bullet.setX(x + w);
////                bullet.setY(y +(h - sizeBullet) / 2);
//                myBullet.setX(x + w);
//                myBullet.setY(y +(h - sizeBullet) / 2);
//                enemyBullet.setX(x + w);
//                enemyBullet.setY(y +(h - sizeBullet) / 2);
//                break;
//            case UP:
////                bullet.setX(x  + (w - sizeBullet) / 2);
////                bullet.setY(y - sizeBullet);
//                enemyBullet.setX(x  + (w - sizeBullet) / 2);
//                enemyBullet.setY(y - sizeBullet);
//                myBullet.setX(x  + (w - sizeBullet) / 2);
//                myBullet.setY(y - sizeBullet);
//                break;
//            case DOWN:
////                bullet.setX(x + (w - sizeBullet) / 2);
////                bullet.setY(y + h);
//                myBullet.setX(x + (w - sizeBullet) / 2);
//                myBullet.setY(y + h);
//                enemyBullet.setX(x + (w - sizeBullet) / 2);
//                enemyBullet.setY(y + h);
//                break;
//        }
////        bullets.add(bullet);
//        myBulletList.add(myBullet);
//        enemyBullets.add(enemyBullet);
//
//    }


    public boolean interactTankWithItems(Item[][] items) {
        Rectangle rT = new Rectangle(x, y, w, h);
        boolean isInteract = false;
        for (int i = 0; i < Const.NUMBER_ROW; i++) {
            for (int j = 0; j < Const.NUMBER_COLUMN; j++) {
                if (items[i][j] == null) {
                    continue;
                }
                if (items[i][j].getTypeID() == Item.TREE){
                    continue;
                }
                Rectangle rIt = new Rectangle(
                        items[i][j].getX(), items[i][j].getY(),
                        items[i][j].getW(), items[i][j].getH()
                );
                if (rT.intersects(rIt)) {
                    backLastPosition();
                    isInteract = true;
                    rT = new Rectangle(x, y, w, h);
                }
            }
        }
        return isInteract;
    }

    protected void backLastPosition() {
        switch (ori) {
            case LEFT:
                x += 2;
                break;
            case RIGHT:
                x -= 2;
                break;
            case UP:
                y += 2;
                break;
            case DOWN:
                y -= 2;
                break;
        }
    }

    public void changOri() {
        ori =(new Random().nextInt(3)+1+ori)%4;
        setOri(ori);
    }
    protected void playSoundFire() {
        if (soundFire != null) {
            soundFire.stop();
        }
        soundFire = new SoundManager("/sound/ok.wav");
        soundFire.play();
    }

}
