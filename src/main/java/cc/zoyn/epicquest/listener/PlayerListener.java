package cc.zoyn.epicquest.listener;

import cc.zoyn.epicquest.EpicQuest;
import cc.zoyn.epicquest.dto.User;
import cc.zoyn.epicquest.manager.QuestManager;
import cc.zoyn.epicquest.manager.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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

}
