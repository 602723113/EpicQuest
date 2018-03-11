package cc.zoyn.epicquest.manager;

import cc.zoyn.epicquest.EpicQuest;
import cc.zoyn.epicquest.dto.User;
import cc.zoyn.epicquest.util.ConfigurationUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.bukkit.Bukkit.getConsoleSender;

/**
 * @author Zoyn
 * @since 2018-03-11
 */
public class UserManager {

    private volatile static UserManager instance;
    private List<User> users = Lists.newArrayList();

    private UserManager() {
    }

    /**
     * 获取用户管理器实例
     *
     * @return {@link UserManager}
     */
    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(Validate.notNull(user));
    }

    public void removeUser(User user) {
        Validate.notNull(user);

        if (users.contains(user)) {
            users.remove(user);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void loadUsers() {
        users.clear();

        Arrays.stream(Objects.requireNonNull(EpicQuest.getInstnace().getUserFolder().listFiles())).forEach(file -> {
            FileConfiguration config = ConfigurationUtils.loadYml(file);
            String name = config.getString("User.name");
            List<Integer> questIds = config.getIntegerList("User.quest-ids");
            List<Integer> completedTaskIds = config.getIntegerList("User.completed-quest-ids");

            addUser(new User(name, questIds, completedTaskIds));
        });

        getConsoleSender().sendMessage("§eLoading user datas success!");
    }

}
