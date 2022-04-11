package Model;

import java.awt.*;
import java.util.concurrent.RecursiveAction;

public class Obj2D {
    protected int x, y ;
    protected int w, h;
    protected Image image;

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, x, y,w, h,  null);
    }

    public boolean isInteract(Obj2D obj2D){
        Rectangle r1 = new Rectangle(x, y, w, h);
        Rectangle r2 = new Rectangle(obj2D.x , obj2D.y , obj2D.w, obj2D.h);
        return r1.intersects(r2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
