package Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Item extends Obj2D{
    protected int typeID;
    private Animation animation;
    private long timeToTouchDie;
    public static final int ROCK = 5;
    public static final int BRICK = 1;
    public static final int WATER  = 2;
    public static final int TREE = 4;
    public static final int HOME = 9;

    public boolean isTouchDie(){
        return animation!=null;
    }

    public Animation getAnimation() {
        return animation;
    }

    public boolean isDie(){
        if ( animation != null ){
            if ( System.currentTimeMillis()-timeToTouchDie >= 2000){
                return true;
            }
        }
        return false;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public static int getROCK() {
        return ROCK;
    }

    public static int getBRICK() {
        return BRICK;
    }

    public static int getWATER() {
        return WATER;
    }

    public static int getTREE() {
        return TREE;
    }

    public static int getHOME() {
        return HOME;
    }
    public Image getImage( int typeID) {
        switch (typeID) {
            case BRICK:
                return new ImageIcon(
                        Item.class.getResource("/img/brick.png")
                ).getImage();
            case WATER:
                return new ImageIcon(
                        Item.class.getResource("/img/water.png")
                ).getImage();
            case TREE:
                return new ImageIcon(
                        Item.class.getResource("/img/tree.png")
                ).getImage();
            case HOME:
                return new ImageIcon(
                        Item.class.getResource("/img/bird.png")
                ).getImage();
            case ROCK:
                return new ImageIcon(
                        Item.class.getResource("/img/rock.png")
                ).getImage();
            default:
                return null;
        }

    }
    public void initAnimation(){
        if (animation != null){
            return;
        }
        timeToTouchDie = System.currentTimeMillis();
        animation = new Animation();
        List<Image> imgs = new ArrayList<>();
        for ( int i = 1; i <=8; i++){
            imgs.add(getImageAnimation(i));
        }
        animation.setImgs(imgs);
        animation.updateCurrentIndex();
        animation.setTimeToLiveEachFrame(100);
        animation.setX(getX());
        animation.setY(getY());
        animation.setW(getW());
        animation.setH(getH());
    }
    private Image getImageAnimation(int index){
        return new ImageIcon(
                getClass().getResource("/img/fire_0" +index+".png")
        ).getImage();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (animation != null){
            animation.draw(graphics2D);
        }else {
            super.draw(graphics2D);
        }
    }
}
