package cc.zoyn.epicquest.dto;

import cc.zoyn.epicquest.manager.QuestManager;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * 表示一个任务
 *
 * @author Zoyn
 * @since 2018-03-10
 */
@Data
@AllArgsConstructor
public class Quest {

    private int id;
    private String displayName;
    private QuestType type;
    private Goal goal;
    private boolean needPreQuest;
    private int preQuestId;
    private List<String> rewards;

    /**
     * 获取前置任务的任务对象
     *
     * @return {@link Quest}
     */
    public Optional<Quest> getPreQuest() {
        if (!needPreQuest) {
            return Optional.empty();
        }
        return QuestManager.getInstance().getQuestById(preQuestId);
    }
}
