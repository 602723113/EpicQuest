package cc.zoyn.epicquest.util;

import cc.zoyn.epicquest.dto.Quest;
import cc.zoyn.epicquest.dto.User;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
public final class QuestUtils {

    public static boolean isCompletePreQuest(User user, Quest quest) {
        if (quest.isNeedPreQuest()) {
            return user.getCompletedTaskIds().contains(quest.getPreQuestId());
        }
        return true;
    }

}
