/*
 * Added by Abstract Delete to help fulfil new requirements
 */
package com.geeselightning.zepr;

public class GameOverScreen extends TextScreen {

    public GameOverScreen(Zepr zepr, String text, String type, Player player) {
        super(zepr, text);
        // Change starts: ZOMBIESTORY
        // If the player is a zombie when they die, reset the game, else turn them into a zombie
        if (type.equals("zombie")){
            // Change starts: PROGRESSFUNCS
            getParent().setProgress(3);
            // Change ends: PROGRESSFUNCS
            // Change starts
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
        // Change ends: ZOMBIESTORY
    }
}
