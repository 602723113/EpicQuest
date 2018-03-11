package cc.zoyn.epicquest.command;

import cc.zoyn.epicquest.command.subcommand.HelpCommand;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

import static cc.zoyn.epicquest.util.I18n.MESSAGE_PREFIX;
import static cc.zoyn.epicquest.util.I18n.UNKNOW_COMMAND;

/**
 * 命令管理
 *
 * @author Zoyn
 * @since 2018-03-10
 */
public class CommandHandler implements CommandExecutor {

    private static Map<String, SubCommand> commandMap = Maps.newHashMap();

    /**
     * Initialize all sub commands
     */
    public CommandHandler() {
        registerCommand("help", new HelpCommand());
    }

    private void registerCommand(String commandName, SubCommand subCommand) {
        if (commandMap.containsKey(commandName)) {
            Bukkit.getLogger().warning("duplicate add command!");
        }
        commandMap.put(commandName, subCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            commandMap.get("help").execute(sender, args);
            return true;
        }
        if (!commandMap.containsKey(args[0])) {
            sender.sendMessage(MESSAGE_PREFIX.getMessage() + UNKNOW_COMMAND.getMessage());
            return true;
        }

        SubCommand subCommand = commandMap.get(args[0]);
        subCommand.execute(sender, args);
        return true;
    }
}
