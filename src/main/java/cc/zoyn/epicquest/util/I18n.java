package cc.zoyn.epicquest.util;

import cc.zoyn.epicquest.EpicQuest;
import lombok.Getter;
import org.bukkit.ChatColor;


/**
 * @author Zoyn
 * @since 2018-03-11
 */
public enum I18n {

    MESSAGE_PREFIX(translateColorCode(EpicQuest.getInstnace().getLanguageConfig().getString("message-prefix"))),
    UNKNOW_COMMAND(translateColorCode(EpicQuest.getInstnace().getLanguageConfig().getString("unknown-command"))),
    NOT_COMPLETED_PRE_QUEST(translateColorCode(EpicQuest.getInstnace().getLanguageConfig().getString("not-completed-pre-quest")));

    @Getter
    private String message;

    I18n(String message) {
        this.message = message;
    }

    private static String translateColorCode(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
