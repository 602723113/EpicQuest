package cc.zoyn.epicquest.command.subcommand;

import cc.zoyn.epicquest.command.SubCommand;
import cc.zoyn.epicquest.util.I18n;
import org.bukkit.command.CommandSender;

/**
 * @author Zoyn
 * @since 2017-3-11
 */
public class HelpCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("eq.help")) {
            I18n.HELP.getAsStringList().forEach(sender::sendMessage);
        } else {
            sender.sendMessage(I18n.NO_PERMISSION.getMessage());
        }
    }
}
