package com.geeselightning.zepr;

public class GameOverScreen extends TextScreen {

    public GameOverScreen(Zepr zepr, String text, String type, Player player) {
        super(zepr, text);
        if (type.equals("zombie")){
            getParent().setProgress(3);
            getParent().zeroPoints();
            getParent().zeroCureProg();
            getParent().setZombie(false);
        } else if (type.equals("human")){
            if (getParent().getProgress() >= 4) {
                getParent().decProgress();
            }
            getParent().setLastKnownCharacter(player.getType());
            getParent().setZombie(true);
        }
    }
}
