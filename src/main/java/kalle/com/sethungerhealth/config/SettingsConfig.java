package kalle.com.sethungerhealth.config;

import kalle.com.sethungerhealth.SetHungerHealth;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SettingsConfig {

    private SetHungerHealth plugin;

    private FileConfiguration config;
    private File file;

    public SettingsConfig(SetHungerHealth plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), "settings.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                config = YamlConfiguration.loadConfiguration(file);
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SetHungerHealth] settings.yml file has been created!");
                loadDefault();
            } catch (IOException e) {
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SetHungerHealth] CONFIG ERROR: could not create settings.yml file!");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    private void loadDefault() {
        config.set("Hunger", 20.0);
        config.set("Health", 20.0);
        try {
            config.save(file);
            config = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SetHungerHealth] CONFIG ERROR: could not load default of settings.yml file!");
        }
    }

    public double[] getValues() {
        double values[] = new double[2];
        values[0] = config.getDouble("Hunger");
        values[1] = config.getDouble("Health");
        if (values[0] < 1.0 || values[0] > 20.0 || values[1] < 1.0 || values[1] > 20.0) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[SetHungerHealth] CONFIG ERROR: values of hunger and health need to be between 1.0 and 20.0! (restoring config to default)");
            loadDefault();
            return new double[]{20.0, 20.0};
        }
        return values;
    }

}
