package cc.zoyn.epicquest.dto;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
@Data
public class User {

    private String name;
    private List<Quest> quests;

    /**
     * 返回该用户的Player对象, 当玩家不在线或不存在时返回null
     *
     * @return {@link Player}
     */
    public Player getPlayer() {
        return Bukkit.getPlayerExact(name);
    }
}
