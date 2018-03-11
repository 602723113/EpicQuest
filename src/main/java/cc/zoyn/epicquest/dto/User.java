package cc.zoyn.epicquest.dto;

import cc.zoyn.epicquest.manager.QuestManager;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
@Data
@AllArgsConstructor
public class User {

    private String name;
    private List<Integer> questIds;
    private List<Integer> completedTaskIds;

    public User(Player player) {
        if (player != null) {
            this.name = player.getName();
            questIds = Lists.newArrayList();
            completedTaskIds = Lists.newArrayList();
        }
    }

    /**
     * 返回该用户的Player对象, 当玩家不在线或不存在时返回null
     *
     * @return {@link Player}
     */
    public Player getPlayer() {
        return Bukkit.getPlayerExact(name);
    }

    public void addQuest(@NonNull Quest quest) {
        this.questIds.add(quest.getId());
    }

    public void removeQuest(@NonNull Quest quest) {
        if (questIds.contains(quest.getId())) {
            this.questIds.remove(quest.getId());
        }
    }

    /**
     * 获取该用户所有的任务对象
     *
     * @return {@link Quest}
     */
    public List<Quest> getQuests() {
        List<Quest> quests = Lists.newArrayList();
        if (questIds != null && !questIds.isEmpty()) {
            questIds.forEach(id -> QuestManager.getInstance().getQuestById(id).ifPresent(
                    quest -> quests.add(QuestManager.getInstance().getQuestById(id).get()))
            );
        }
        return quests;
    }

}
