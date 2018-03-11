package cc.zoyn.epicquest.listener;

import cc.zoyn.epicquest.EpicQuest;
import cc.zoyn.epicquest.dto.GoalType;
import cc.zoyn.epicquest.dto.QuestType;
import cc.zoyn.epicquest.dto.User;
import cc.zoyn.epicquest.manager.QuestManager;
import cc.zoyn.epicquest.manager.UserManager;
import cc.zoyn.epicquest.util.I18n;
import cc.zoyn.epicquest.util.QuestUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // 以下为用户任务数据检查
        if (UserManager.getInstance().getUsers().stream().noneMatch(user -> user.getName().equals(event.getPlayer().getName()))) {
            // 若不存在数据, 则自动创建用户任务数据, 并且自动领取默认任务
            User user = new User(event.getPlayer());
            user.addQuest(QuestManager.getInstance().getQuestById(
                    EpicQuest.getInstnace().getConfig().getInt("default-quest")).get());

            UserManager.getInstance().addUser(user);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        UserManager.getInstance().getUserByName(event.getPlayer().getName()).ifPresent(user -> user.getQuests().forEach(quest -> {
            // 前置任务检测
            if (!QuestUtils.isCompletePreQuest(user, quest)) {
                if (quest.getType().equals(QuestType.EXTENSION_QUEST) || quest.getType().equals(QuestType.DAILY_QUEST)) {
                    quest.getPreQuest().ifPresent(preQuest -> event.getPlayer().sendMessage(I18n.NOT_COMPLETED_PRE_QUEST.getMessage().replaceAll("%quest_name%", quest.getDisplayName())));
                }
                return;
            }

            Map<String, Object> goalContent = quest.getGoal().getContent();
            if (quest.getGoal().getType().equals(GoalType.SPEAK)) {
                if (event.getMessage().equals(((String) goalContent.get("message")).replaceAll("&", "§"))) {
                }
            }
        }));
    }
}
