package kalle.com.sethungerhealth.events;

import kalle.com.sethungerhealth.SetHungerHealth;
import org.bukkit.entity.Player;

public class RespawnTask extends Thread {

    private Player player; //player who respawns

    public RespawnTask(Player player) {
        super();
        this.player = player;
    }

    public void run() {
        try {
            String location = player.getLocation().toString();
            String tmpLocation = location;
            while (location.equals(tmpLocation)) {
                tmpLocation = player.getLocation().toString();
            }
            player.setFoodLevel((int) SetHungerHealth.hunger);
            player.setHealth(SetHungerHealth.health);
        } catch (NullPointerException e) {
            return;
        }
    }

}
