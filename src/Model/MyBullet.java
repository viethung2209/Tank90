package Model;

import java.awt.*;
import java.util.List;

public class MyBullet extends Bullet {
    public boolean myBulletInteractWithEnemyTank(List<EnemyTank> enemyTanks) {
        Rectangle rMyBu = new Rectangle(x, y, w , h);
        for (int i = 0; i < enemyTanks.size(); i++) {
            Rectangle rEnemyT = new Rectangle(
                    enemyTanks.get(i).x, enemyTanks.get(i).y, enemyTanks.get(i).w, enemyTanks.get(i).h
            );
            boolean isInteract = rMyBu.intersects(rEnemyT);
            if (isInteract) {
                enemyTanks.remove(i);
                return true;
            }
        }
        return false;
    }


}
