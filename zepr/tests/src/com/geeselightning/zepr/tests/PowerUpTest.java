package com.geeselightning.zepr.tests;

import com.geeselightning.zepr.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


@RunWith(GdxTestRunner.class)
public class PowerUpTest {

    @Test
    // Test 4.1
    public void powerUpHealthAddsHPToPlayer() {
        Player player = Player.getInstance();
        PowerUpHeal heal = new PowerUpHeal(null);
        player.takeDamage(50);
        double originalHealth = player.getHealth();
        heal.activate();
        heal.update(1);
        assertEquals("Heal powerup should give the player more hit points.",
                originalHealth + Constant.HEALUP, player.getHealth(), 0.1);
    }

    @Test
    // Test 4.2.1
    public void powerUpSpeedIncreasePlayersSpeed() {
        Player player = Player.getInstance();
        PowerUpSpeed speed = new PowerUpSpeed(null);
        double originalSpeed = player.speed;
        speed.activate();
        assertEquals("Speed powerup should increase the Players speed.", originalSpeed + Constant.SPEEDUP,
                player.speed, 0.1);
    }

    @Test
    // Test 4.2.2
    public void powerUpSpeedDeactivatesAfter10s() {
        Player player = Player.getInstance();
        PowerUpSpeed speed = new PowerUpSpeed(null);
        double originalSpeed = player.speed;
        speed.activate();
        speed.update(11);
        assertEquals("Speed should go back to the original speed after 10s.", originalSpeed, player.speed, 0.1);
    }

    @Test
    // Test 4.2.3
    public void powerUpSpeedDoesNotDeactiveBefore10s() {
        Player player = Player.getInstance();
        PowerUpSpeed speed = new PowerUpSpeed(null);
        double originalSpeed = player.speed;
        speed.activate();
        speed.update(9);
        assertNotEquals("Speed powerup should increase the Players speed.", originalSpeed,
                player.speed);
    }

    @Test
    // Test 4.2.4
    public void powerUpSpeedDeactivateMethodResetsPlayerSpeed() {
        Player player = Player.getInstance();
        PowerUpSpeed speed = new PowerUpSpeed(null);
        double originalSpeed = player.speed;
        speed.activate();
        speed.update(5);
        speed.deactivate();
        assertEquals("Player speed is reset if deactivate is used on the powerup.", originalSpeed,
                player.speed, 0.1);
    }

    @Test
    // Test 4.3.1
    public void playerCannotPickUpFarAwayPowerUp() {
        Player player = Player.getInstance();
        PowerUpHeal powerup = new PowerUpHeal(null);
        powerup.setPosition(0,0);
        player.setPosition(100,100);
        assertFalse("Player cannot pickup a power up if it is not touching it.", powerup.overlapsPlayer());
    }

    @Test
    //Test 4.3.2
    public void playerCanPickUpClosePowerUp() {
        Player player = Player.getInstance();
        PowerUpHeal powerup = new PowerUpHeal(null);
        powerup.setPosition(0,0);
        player.setPosition(31,31);
        assertTrue("Player can pickup a power up if it is touching it.", powerup.overlapsPlayer());
    }

    @Test
    // Test 4.4.1
    public void powerUpImmunityStopsThePlayerTakingDamge() {
        Player player = Player.getInstance();
        PowerUpImmunity immunity = new PowerUpImmunity(null);
        immunity.activate();
        double originalHealth = player.getHealth();
        player.takeDamage(30);
        assertEquals("Player health before and after taking damage should remain the same when immunity is activated.",
                originalHealth, player.getHealth(), 0.1);
    }

    @Test
    // Test 4.4.2
    public void powerUpImmunityDeactivatesAfter5s() {
        Player player = Player.getInstance();
        PowerUpImmunity immunity = new PowerUpImmunity(null);
        double originalHealth = player.getHealth();
        immunity.activate();
        player.takeDamage(40);
        immunity.update(6);
        player.takeDamage(30);
        assertEquals("Player should take 30 damage after the immunity expires", originalHealth - 30,
                player.getHealth(), 0.1);
    }

    @Test
    // Test 4.4.3
    public void powerUpImmunityDeactivateMethodCancelsImmunity() {
        Player player = Player.getInstance();
        PowerUpImmunity immunity = new PowerUpImmunity(null);
        double originalHealth = player.getHealth();
        immunity.activate();
        immunity.update(2);
        player.takeDamage(40);
        immunity.deactivate();
        player.takeDamage(30);
        assertEquals("Player should take 30 damage afrer immunity is deactivated.", originalHealth-30,
                player.getHealth(), 0.1);
    }

    @Test
    // Test 4.5.1
    public void powerUpSlowDecreasePlayersSpeed() {
        Player player = Player.getInstance();
        PowerUpSlow slow = new PowerUpSlow(null);
        double originalSpeed = player.speed;
        slow.activate();
        assertEquals("Slow powerup should decrease the Players speed.", originalSpeed - Constant.SLOW,
                player.speed, 0.1);
    }

    @Test
    // Test 4.5.2
    public void powerUpSlowDeactivatesAfter15s() {
        Player player = Player.getInstance();
        PowerUpSlow slow = new PowerUpSlow(null);
        double originalSpeed = player.speed;
        slow.activate();
        slow.update(16);
        assertEquals("Speed should go back to the original speed after 15s.", originalSpeed, player.speed, 0.1);
    }

    @Test
    // Test 4.5.3
    public void powerUpSlowDoesNotDeactiveBefore15s() {
        Player player = Player.getInstance();
        PowerUpSlow slow = new PowerUpSlow(null);
        double originalSpeed = player.speed;
        slow.activate();
        slow.update(9);
        assertNotEquals("Slow powerup should decrease the Players speed.", originalSpeed,
                player.speed);
    }

    @Test
    // Test 4.5.4
    public void powerUpSlowDeactivateMethodResetsPlayerSpeed() {
        Player player = Player.getInstance();
        PowerUpSlow slow = new PowerUpSlow(null);
        double originalSpeed = player.speed;
        slow.activate();
        slow.update(5);
        slow.deactivate();
        assertEquals("Player speed is reset if deactivate is used on the powerup.", originalSpeed,
                player.speed, 0.1);
    }

    @Test
    // Test 4.6.1
    public void powerUpDamageIncreasePlayersAttackDamage() {
        Player player = Player.getInstance();
        PowerUpDamage damage = new PowerUpDamage(null);
        double originalDamage = player.attackDamage;
        damage.activate();
        assertEquals("Damage powerup should increase the Players attackDamage.",
                originalDamage + Constant.DAMAGEUP,
                player.attackDamage, 0.1);
    }

    @Test
    // Test 4.6.2
    public void powerUpDamageDeactivatesAfter5s() {
        Player player = Player.getInstance();
        PowerUpDamage damage = new PowerUpDamage(null);
        double originalDamage = player.attackDamage;
        damage.activate();
        damage.update(6);
        assertEquals("Damage should go back to the original Damage after 5s.", originalDamage, player.speed, 0.1);
    }

    @Test
    // Test 4.6.3
    public void powerUpDamageDoesNotDeactiveBefore5s() {
        Player player = Player.getInstance();
        PowerUpDamage damage = new PowerUpDamage(null);
        double originalDamage = player.attackDamage;
        damage.activate();
        damage.update(4);
        assertNotEquals("Damage powerup should increase the Players damage.", originalDamage,
                player.speed);
    }

    @Test
    // Test 4.6.4
    public void powerUpDamageDeactivateMethodResetsPlayerDamage() {
        Player player = Player.getInstance();
        PowerUpDamage damage = new PowerUpDamage(null);
        double originalDamage = player.attackDamage;
        damage.activate();
        damage.update(5);
        damage.deactivate();
        assertEquals("Player damage is reset if deactivate is used on the powerup.", originalDamage,
                player.attackDamage, 0.1);
    }
}
