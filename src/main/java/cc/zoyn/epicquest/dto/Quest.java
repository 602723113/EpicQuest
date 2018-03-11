package cc.zoyn.epicquest.dto;

import lombok.Data;

import java.util.List;

/**
 * 表示一个任务
 *
 * @author Zoyn
 * @since 2018-03-10
 */
@Data
public class Quest {

    private String name;
    private QuestType type;
    private Goal goal;
    private boolean needPreQuest;
    private String preQuestName;
    private List<String> rewards;

}
