package kyubii.de.teamchat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TeamchatCommand implements CommandExecutor {
    Plugin teamChat = TeamChat.getPlugin(TeamChat.class);


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
                    if (!ToggleTeamchatCommand.isActive.containsKey(player)){
                        ToggleTeamchatCommand.isActive.put(player, true);
                    }
                    if (ToggleTeamchatCommand.isActive.get(player).equals(false)){
                        player.sendMessage("§cDein Teamchat ist deaktiviert!");
                        return true;
                    }
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
                                if (!ToggleTeamchatCommand.isActive.containsKey(targets)) {
                                    ToggleTeamchatCommand.isActive.put(targets, true);
                                }
                                if (ToggleTeamchatCommand.isActive.get(targets).equals(true)) {
                                    targets.sendMessage(teamChat.getConfig()
                                            .getString("Prefix")
                                            .replace("%PLAYER%", player.getDisplayName())
                                            .replaceAll("&", "§") + text);
                                }
                            }
                        }
                    }else {
                        player.sendMessage("§c/Teamchat <Nachricht>");
                    }
                }
            }
        }
        return true;
    }
}
