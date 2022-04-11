package Model;

import java.awt.*;

public class Bullet extends Obj2DMoveable{
    public boolean interactWithItem(Item[][] items){
        Rectangle rBul = new Rectangle(x, y, w, h);
        for (int i = 0; i < Const.NUMBER_ROW; i++) {
            for (int j = 0; j < Const.NUMBER_COLUMN; j++) {
                if (items[i][j] != null) {
                    if (items[i][j].getTypeID() == Item.TREE) {
                        continue;
                    }
                    Rectangle rIt = new Rectangle(
                            items[i][j].x, items[i][j].y,
                            items[i][j].w, items[i][j].h
                    );
                    boolean isInteract = rBul.intersects(rIt);
                    if (isInteract) {
                        if (items[i][j].getTypeID() == Item.BRICK &&
                        !items[i][j].isTouchDie()){
//                            items[i][j] = null;
                            items[i][j].initAnimation();
                        }
                        return true;
                    }

                }
            }
        }
        return false;
    }
}
