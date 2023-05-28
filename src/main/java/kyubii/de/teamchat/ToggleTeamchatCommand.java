package kyubii.de.teamchat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class ToggleTeamchatCommand implements CommandExecutor {

    public static HashMap<Player, Boolean> isActive = new HashMap<>();
    Plugin teamChat = TeamChat.getPlugin(TeamChat.class);
    private String getPermission(){
        return teamChat.getConfig().getString("Permission");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("§cDu bist kein Spieler");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission(getPermission())) {
            if (!isActive.containsKey(player)) {
                isActive.put(player, true);
            }
            if (isActive.get(player)) {
                isActive.put(player, false);
                player.sendMessage("§cDu siehst den Teamchat nun nicht mehr!");
            } else {
                isActive.put(player, true);
                player.sendMessage("§aDu siehst den Teamchat nun wieder!");
            }
        }else {
            player.sendMessage("§cDafür hast du keine Rechte!");
        }
        return true;
    }
}
