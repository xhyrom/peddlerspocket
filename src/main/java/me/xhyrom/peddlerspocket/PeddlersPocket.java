package me.xhyrom.peddlerspocket;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import lombok.Getter;
import me.xhyrom.peddlerspocket.commands.PeddlersCommand;
import me.xhyrom.peddlerspocket.structs.*;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class PeddlersPocket extends JavaPlugin {
    @Getter
    private static PeddlersPocket instance;
    @Getter
    private HashMap<Material, Double> prices = new HashMap<>();
    @Getter
    private HashMap<Result, ArrayList<Action>> actions = new HashMap<>();
    public FileConfiguration config = getConfig();

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIConfig().silentLogs(true));
    }

    @Override
    public void onEnable() {
        instance = this;
        CommandAPI.onEnable(this);

        saveDefaultConfig();

        config.getConfigurationSection("materials").getKeys(false).forEach(key -> {
            config.getConfigurationSection("materials."+key).getValues(false).forEach((k, v) -> {
                prices.put(Material.getMaterial(k.toString()), Double.parseDouble(v.toString()));
            });
        });

        config.getConfigurationSection("actions").getKeys(false).forEach(key -> {
            ArrayList<Action> actions = new ArrayList<>();

            ArrayList<HashMap<String, Object>> config_actions = (ArrayList<HashMap<String, Object>>) config.get("actions."+key);

            config_actions.forEach(config_action -> {
                if (config_action.containsKey("title"))
                    actions.add(new TitleAction(
                            config_action.get("title").toString(),
                            config_action.containsKey("subtitle") ? config_action.get("subtitle").toString() : ""
                    ));
                else if (config_action.containsKey("message"))
                    actions.add(new MessageAction(
                            config_action.get("message").toString()
                    ));
                else if (config_action.containsKey("command"))
                    actions.add(new CommandAction(
                            config_action.get("command").toString()
                    ));
                else if (config_action.containsKey("sound"))
                    actions.add(new SoundAction(
                            config_action.get("sound").toString(),
                            config_action.containsKey("volume") ? Float.parseFloat(config_action.get("volume").toString()) : 1,
                            config_action.containsKey("pitch") ? Float.parseFloat(config_action.get("pitch").toString()) : 1
                    ));
            });

            this.actions.put(Result.valueOf(key.toUpperCase()), actions);
        });

        PeddlersCommand.register();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }
}
