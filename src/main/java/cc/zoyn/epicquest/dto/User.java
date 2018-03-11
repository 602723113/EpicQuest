package cc.zoyn.epicquest.dto;

import cc.zoyn.epicquest.manager.QuestManager;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private List<String> questNames;

    public User(Player player) {
        if (player != null) {
            this.name = player.getName();
            questNames = Lists.newArrayList();
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

    /**
     * 获取该用户所有的任务对象
     *
     * @return {@link Quest}
     */
    public List<Quest> getQuests() {
        List<Quest> quests = Lists.newArrayList();
        if (questNames != null && !questNames.isEmpty()) {
            questNames.forEach(s -> {
                if (QuestManager.getInstance().getQuestByName(s).isPresent()) {
                    quests.add(QuestManager.getInstance().getQuestByName(s).get());
                }
            });
        }
        return quests;
    }

}
