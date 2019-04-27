/*
 * Added by Abstract Delete to help fulfil new requirements
 */
package com.geeselightning.zepr;

public class GameOverScreen extends TextScreen {

    public GameOverScreen(Zepr zepr, String text, String type, Player player) {
        super(zepr, text);
        // Change starts: ZOMBIESTORYGAMEOVER
        // If the player is a zombie when they die, reset the game, else turn them into a zombie
        // Change starts: GAMEOVERRESET
        if (type.equals("zombie")){
            getParent().setProgress(3);
            getParent().zeroPoints();
            getParent().zeroCureProg();
            getParent().setZombie(false);
            // Change ends: GAMEOVERRESET
        } else if (type.equals("human")){
            if (getParent().getProgress() >= 4) {
                getParent().decProgress();
            }
            getParent().setLastKnownCharacter(player.getType());
            getParent().setZombie(true);
        }
        // Change ends: ZOMBIESTORYGAMEOVER
    }
}
