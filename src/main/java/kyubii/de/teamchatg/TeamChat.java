package kyubii.de.teamchatg;

import org.bukkit.plugin.java.JavaPlugin;

public final class TeamChat extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        getConfig().addDefault("Prefix", "&8[&bTeamchat&8] &7");
        getConfig().addDefault("Permission", "teamchat.chat");
        getCommand("Teamchat").setExecutor(new TeamchatCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public String getPrefix(){
        return getConfig().getString("Prefix").replaceAll("&", "§");
    }
    public String getPermission(){
        return getConfig().getString("Permission");
    }
}
