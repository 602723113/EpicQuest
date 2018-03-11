package cc.zoyn.epicquest.dto;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
public enum GoalType {
    /**
     * 跟Citizens的NPC交互
     */
    INTERACT_NPC,
    /**
     * 提交物品给Cititzens的NPC
     */
    SUBMIT_ITEM_TO_NPC,
    /**
     * 说话
     */
    SPEAK,
    KILL_NAMED_MOB,
    KILL_PLAYER,
    KILL_ANY_PLAYER,
    BREAK_BLOCK,
    PLACE_BLOCK,
    LEVEL_UP,
    ENCHANTMENT,
    CRAFT_ITEM,
    CLICK_BLOCK,
    RUN_COMMAND,
    REACH_LOCATION
}
