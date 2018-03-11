package cc.zoyn.epicquest.manager;

import cc.zoyn.epicquest.EpicQuest;
import cc.zoyn.epicquest.dto.Goal;
import cc.zoyn.epicquest.dto.GoalType;
import cc.zoyn.epicquest.dto.Quest;
import cc.zoyn.epicquest.dto.QuestType;
import cc.zoyn.epicquest.util.ConfigurationUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

import static org.bukkit.Bukkit.getConsoleSender;

/**
 * 用于管理所有的任务
 *
 * @author Zoyn
 * @since 2018-03-10
 */
public class QuestManager {

    private volatile static QuestManager instance;
    private List<Quest> quests = Lists.newArrayList();

    private QuestManager() {
    }

    /**
     * 获取任务管理器实例
     *
     * @return {@link QuestManager}
     */
    public static QuestManager getInstance() {
        if (instance == null) {
            synchronized (QuestManager.class) {
                if (instance == null) {
                    instance = new QuestManager();
                }
            }
        }
        return instance;
    }

    /**
     * 增加一个任务
     *
     * @param quest 任务对象
     */
    public void addQuest(Quest quest) {
        quests.add(Validate.notNull(quest));
    }

    /**
     * 移除一个任务
     *
     * @param quest 任务对象
     */
    public void removeQuest(Quest quest) {
        Validate.notNull(quest);

        if (quests.contains(quest)) {
            quests.remove(quest);
        }
    }

    public Optional<Quest> getQuestById(int id) {
        return quests.stream().filter(quest -> quest.getId() == id).findAny();
    }

    /**
     * 获取所有任务
     *
     * @return {@link Quest}
     */
    public List<Quest> getQuests() {
        return quests;
    }

    /**
     * 所有任务读取
     *
     * @return {@link Quest}
     */
    public List<Quest> loadQuests() {
        quests.clear();

        Arrays.stream(Objects.requireNonNull(EpicQuest.getInstnace().getQuestFolder().listFiles())).forEach(file -> {
            FileConfiguration config = ConfigurationUtils.loadYml(file);
            int id = config.getInt("Quest.id");
            String displayName = config.getString("Quest.display-name").replaceAll("&", "§");

            QuestType type = QuestType.valueOf(config.getString("Quest.quest-type"));

            /* 以下为任务目标的读取
             * 以下操作需要实例化Goal
             */
            GoalType goalType = GoalType.valueOf(config.getString("Quest.goal.type"));
            // 获取其所有键
            Set<String> keys = config.getConfigurationSection("Quest.goal").getKeys(false);
            // 内部数据 =>  键名:内容
            Map<String, String> goalContent = Maps.newHashMap();
            keys.forEach(s -> goalContent.put(s, config.getString("Quest.goal." + s)));
            Goal goal = new Goal(goalType, goalContent);
            /* 任务目标读取结束 */

            boolean needPreQuest = config.getBoolean("Quest.need-pre-quest");
            String preQuestName = config.getString("Quest.pre-quest-name").replaceAll("&", "§");
            List<String> rewards = config.getStringList("Quest.rewards");

            addQuest(new Quest(id, displayName, type, goal, needPreQuest, preQuestName, rewards));

            Bukkit.getConsoleSender().sendMessage("§eLoad quest: §r" + displayName + " §esucessfully!");
        });

        getConsoleSender().sendMessage("§eLoading quest datas success!");
        return quests;
    }
}
