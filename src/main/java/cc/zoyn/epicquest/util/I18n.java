package cc.zoyn.epicquest.util;

import cc.zoyn.epicquest.EpicQuest;
import lombok.NonNull;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
public enum I18n {

    MESSAGE_PREFIX(translateColorCode(EpicQuest.getInstnace().getLanguageConfig().getString("message-prefix"))),
    UNKNOW_COMMAND(translateColorCode(EpicQuest.getInstnace().getLanguageConfig().getString("unknown-command"))),
    NOT_COMPLETED_PRE_QUEST(translateColorCode(EpicQuest.getInstnace().getLanguageConfig().getString("not-completed-pre-quest"))),
    NO_PERMISSION(translateColorCode(EpicQuest.getInstnace().getLanguageConfig().getString("no-permission"))),
    HELP(translateColorCode(EpicQuest.getInstnace().getLanguageConfig().getStringList("help")));

    private Object message;

    I18n(Object message) {
        this.message = message;
    }

    public String getMessage() {
        return (String) this.message;
    }

    public List<String> getAsStringList() {
        return (List<String>) this.message;
    }

    private static String translateColorCode(@NonNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private static List<String> translateColorCode(@NonNull List<String> messages) {
        return messages.stream().map(I18n::translateColorCode).collect(Collectors.toList());
    }
}
