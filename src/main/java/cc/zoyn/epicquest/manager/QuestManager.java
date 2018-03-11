package cc.zoyn.epicquest.manager;

import cc.zoyn.epicquest.dto.Quest;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Optional;

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

    public Optional<Quest> getQuestByName(String questName) {
        return quests.stream().filter(quest -> quest.getName().equals(questName)).findAny();
    }

    /**
     * 获取所有任务
     *
     * @return {@link Quest}
     */
    public List<Quest> getQuests() {
        return quests;
    }

    public List<Quest> loadQuests() {
        return quests;
    }
}
