package cc.zoyn.epicquest.command;

import org.bukkit.command.CommandSender;

/**
 * @author Zoyn
 * @since 2017-3-11
 */
public interface SubCommand {

    void execute(CommandSender sender, String[] args);

}
