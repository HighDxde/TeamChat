package kyubii.de.teamchatg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamchatCommand implements CommandExecutor {
    TeamChat teamChat = new TeamChat();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (!player.hasPermission(teamChat.getPermission())){
                player.sendMessage("§cDafür hast du keine Rechte!");
            }else {
                if (args.length <= 1){
                    StringBuilder motdBuilder = new StringBuilder();
                    for (String arg : args) {
                        motdBuilder.append(arg);
                    }
                    String text = motdBuilder.toString();
                    player.sendMessage(teamChat.getPrefix() + player.getDisplayName() + ": " + text);
                    for (Player targets : Bukkit.getOnlinePlayers()){
                        if (targets.hasPermission(teamChat.getPermission())){
                            targets.sendMessage(teamChat.getPrefix() + player.getDisplayName() + ": " + text);
                        }
                    }
                }
            }
        }
        return true;
    }
}
