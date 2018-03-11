package cc.zoyn.epicquest.manager;

import cc.zoyn.epicquest.dto.User;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import java.util.List;

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

}
