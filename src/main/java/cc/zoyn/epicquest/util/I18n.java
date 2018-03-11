package cc.zoyn.epicquest.util;

import lombok.Getter;
import org.bukkit.ChatColor;

import static cc.zoyn.epicquest.EpicQuest.getInstnace;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
public enum I18n {

    MESSAGE_PREFIX(translateColorCode(getInstnace().getLanguageConfig().getString("message-prefix"))),
    UNKNOW_COMMAND(translateColorCode(getInstnace().getLanguageConfig().getString("unknown-command")));

    @Getter
    private String message;

    I18n(String message) {
        this.message = message;
    }

    private static String translateColorCode(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
