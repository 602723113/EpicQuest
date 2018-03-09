package cc.zoyn.epicquest;

import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getConsoleSender;

/**
 * 主类
 *
 * @author Zoyn
 * @since 2018-03-10
 */
public class EpicQuest extends JavaPlugin {

    @SuppressWarnings("unused")
    private static final String INFORMATION = "You do not have to decompile the plugin to get the source code, this plugin has push the source code to Github, URL: https://github.com/DeepinMC/Glyph";
    @SuppressWarnings("unused")
    private static final String INFORMATION_CN = "你不必反编译本插件来获取源码, 本插件已将源码托管到Github, URL: https://github.com/602723113/EpicQuest";

    private static EpicQuest instnace;

    @Override
    public void onEnable() {
        instnace = this;

        getConsoleSender().sendMessage("§6======§8[§eEpicQuest§8]§6======");
        getConsoleSender().sendMessage(" ");
        getConsoleSender().sendMessage("  §eAuthor: §aZoyn");
    }

    /**
     * @return {@link EpicQuest}
     */
    public static EpicQuest getInstnace() {
        return instnace;
    }
}
