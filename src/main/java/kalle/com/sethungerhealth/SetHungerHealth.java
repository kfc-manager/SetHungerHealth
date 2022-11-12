package kalle.com.sethungerhealth;

import kalle.com.sethungerhealth.config.SettingsConfig;
import kalle.com.sethungerhealth.events.PlayerRespawn;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SetHungerHealth extends JavaPlugin {

    public static double hunger;
    public static double health;

    private SettingsConfig settingsConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        settingsConfig = new SettingsConfig(this);
        settingsConfig.setup();
        double[] values = settingsConfig.getValues();
        hunger = values[0];
        health = values[1];
        getServer().getPluginManager().registerEvents(new PlayerRespawn(),this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SetHungerHealth] plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SetHungerHealth] plugin has been disabled!");
    }
}
