package cc.zoyn.epicquest.listener;

import cc.zoyn.epicquest.dto.User;
import cc.zoyn.epicquest.manager.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (UserManager.getInstance().getUsers().stream().noneMatch(user -> user.getName().equals(event.getPlayer().getName()))) {
            UserManager.getInstance().addUser(new User(event.getPlayer()));
        }
    }

}
