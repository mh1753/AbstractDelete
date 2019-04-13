package com.geeselightning.zepr;

public class GameOverScreen extends TextScreen {

    public GameOverScreen(Zepr zepr, String text, String type) {
        super(zepr, text);
        if (type == "zombie"){
            getParent().setProgress(3);
            getParent().zeroPoints();
            getParent().setZombie(false);
        } else if (type == "human"){
            if (getParent().getProgress() >= 4) {
                getParent().decProgress();
            }
            getParent().setZombie(true);
        }
    }
}
