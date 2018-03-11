package cc.zoyn.epicquest.listener;

import cc.zoyn.epicquest.dto.GoalType;
import cc.zoyn.epicquest.manager.UserManager;
import cc.zoyn.epicquest.util.QuestUtils;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
public class NPCListener implements Listener {

    @EventHandler
    public void onInteractNPC(NPCClickEvent event) {
        Player player = event.getClicker();
        NPC npc = event.getNPC();

        /* 任务监听处理 */
        UserManager.getInstance().getUserByName(player.getName()).ifPresent(user -> user.getQuests().forEach(quest -> {
            // 前置任务检测
            if (!QuestUtils.isCompletePreQuest(user, quest)) {
                return;
            }

            GoalType type = quest.getGoal().getType();
            Map<String, Object> goalContent = quest.getGoal().getContent();

            // npc 判断
            if (npc.getName().equals(quest.getGoal().getContent().get("name"))) {
                switch (type) {
                    case SUBMIT_ITEM_TO_NPC:
                        int id = (int) goalContent.get("item.id");
                        int data = (int) goalContent.get("item.data");
                        String displayName = (String) goalContent.get("item.display-name");
                        int amount = (short) goalContent.get("item.amount");
                        ItemStack itemStack = player.getItemInHand();
                        if (itemStack.getTypeId() == id || itemStack.getData().getData() == data) {
                            // 原版物品检测
                            if (displayName != null) {
                                if (!itemStack.hasItemMeta() || !itemStack.getItemMeta().hasDisplayName() || !itemStack.getItemMeta().getDisplayName().equals(displayName.replaceAll("&", "§"))) {
                                    // fail
                                    player.sendMessage(((String) goalContent.get("on-fail")).replaceAll("&", "§"));
                                    return;
                                }
                            }
                            if (amount >= itemStack.getAmount()) {
                                // success
                                // 做物品扣除操作
                                if (amount == itemStack.getAmount()) {
                                    player.setItemInHand(new ItemStack(Material.AIR));
                                }
                                if (amount > itemStack.getAmount()) {
                                    itemStack.setAmount(itemStack.getAmount() - amount);
                                    player.setItemInHand(itemStack);
                                }
                                player.sendMessage(((String) goalContent.get("on-success")).replaceAll("&", "§"));
                            } else {
                                player.sendMessage(((String) goalContent.get("on-fail")).replaceAll("&", "§"));
                                return;
                            }
                        }
                        // 这里没有用break, 因为想给两者都可以使用operation
                    case INTERACT_NPC:
                        // 服务器做的操作, 可以是告诉玩家一些剧情, 也可以是执行一些命令
                        List<String> commands = (List<String>) goalContent.get("operation");
                        commands.forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("&", "§").replaceAll("%player%", player.getName())));
                }
            }
        }));
    }

}
