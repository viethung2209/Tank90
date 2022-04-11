package Model;

import java.awt.*;
import java.util.List;

public class EnemyBullet extends Bullet{
    public boolean enemyBulletInteractWithMyTank(MyTank myTank) {
        Rectangle rEnemyTa = new Rectangle(x, y, w, h);
        Rectangle rMyTa = new Rectangle(myTank.x , myTank.y, myTank.w, myTank.h);
        if (rEnemyTa.intersects(rMyTa)) {
            return true;

        }
        return false;
    }
}
