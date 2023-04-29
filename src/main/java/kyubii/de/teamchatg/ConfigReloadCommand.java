package kyubii.de.teamchatg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ConfigReloadCommand implements CommandExecutor{
    Plugin plugin = TeamChat.getPlugin(TeamChat.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("Rltcconfig") || command.getName().equalsIgnoreCase("Realoadteamchatconfig")) {
            if (sender.hasPermission(getPermission())) {
                plugin.reloadConfig();
                sender.sendMessage("§aDie Teamchat-Config wurde erfolgreich reloadet!");
            }
        }
        return true;
    }
    private String getPermission(){
        return plugin.getConfig().getString("Permission");
    }
}
