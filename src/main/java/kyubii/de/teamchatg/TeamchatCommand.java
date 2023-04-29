package kyubii.de.teamchatg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TeamchatCommand implements CommandExecutor {
    Plugin teamChat = TeamChat.getPlugin(TeamChat.class);

    private String getPrefix(){
        return teamChat.getConfig().getString("Prefix").replaceAll("&", "§");
    }
    private String getPermission(){
        return teamChat.getConfig().getString("Permission");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("Teamchat") || command.getName().equalsIgnoreCase("Tc")) {
                if (!player.hasPermission(getPermission())) {
                    player.sendMessage("§cDafür hast du keine Rechte!");
                } else {
                    if (args.length <= 1) {
                        StringBuilder motdBuilder = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            motdBuilder.append(args[i]);
                        }
                        String text = motdBuilder.toString();
                        if (text.isEmpty()) {
                            return true;
                        }
                        for (Player targets : Bukkit.getOnlinePlayers()) {
                            if (targets.hasPermission(getPermission())) {
                                targets.sendMessage(getPrefix() + player.getDisplayName() + ": " + text);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
