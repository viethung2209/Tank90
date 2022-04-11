package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Animation extends Obj2D{
    private List<Image> imgs = new ArrayList<>();
    private int timeToLiveEachFrame;
    private long lastTimeChange;
    private int currentIndex;

    public List<Image> getImgs() {
        return imgs;
    }

    public void setImgs(List<Image> imgs) {
        this.imgs = imgs;
    }

    public int getTimeToLiveEachFrame() {
        return timeToLiveEachFrame;
    }

    public void setTimeToLiveEachFrame(int timeToLiveEachFrame) {
        this.timeToLiveEachFrame = timeToLiveEachFrame;
    }

    public long getLastTimeChange() {
        return lastTimeChange;
    }

    public void setLastTimeChange(long lastTimeChange) {
        this.lastTimeChange = lastTimeChange;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void updateCurrentIndex(){
        if (imgs.size() == 0){
            return;
        }
        long current = System.currentTimeMillis();
        if (current - lastTimeChange < timeToLiveEachFrame){
            return;
        }
        lastTimeChange = current;
        currentIndex= (currentIndex+1)%imgs.size();
        image = imgs.get(currentIndex);
    }
}
