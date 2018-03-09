package cc.zoyn.epicquest.dto;

import java.util.List;
import java.util.UUID;

/**
 * 表示一个任务
 *
 * @author Zoyn
 * @since 2018-03-10
 */
public class Quest {

    private String name;
    private UUID uuid;
    private QuestType type;
    private Goal goal;
    private boolean needPreQuest;
    private String preQuestName;
    private List<String> rewards;

}
