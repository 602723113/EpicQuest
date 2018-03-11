package cc.zoyn.epicquest;

import cc.zoyn.epicquest.command.CommandHandler;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static cc.zoyn.epicquest.util.ConfigurationUtils.loadYml;
import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getPluginCommand;

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

    @Getter
    private static EpicQuest instnace;
    @Getter
    private File questFolder;
    @Getter
    private String language;
    private File languageFolder;
    private File languageFile;

    @Override
    public void onEnable() {
        instnace = this;

        // 配置文件相关
        saveDefaultConfig();
        questFolder = new File(getDataFolder(), "quests");
        if (!questFolder.exists()) {
            questFolder.mkdirs();
        }
        languageFolder = new File(getDataFolder(), "language");
        if (!languageFolder.exists()) {
            languageFolder.mkdirs();
            try {
                writeToLocal(languageFolder.getAbsolutePath() + "zh-CN.yml", getResource("zh-CN.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 默认为中文
        language = getConfig().getString("language", "zh-CN");

        // 命令
        getPluginCommand("epicquest").setExecutor(new CommandHandler());

        getConsoleSender().sendMessage("§6======§8[§eEpicQuest§8]§6======");
        getConsoleSender().sendMessage(" ");
        getConsoleSender().sendMessage("  §eAuthor: §aZoyn");
    }

    /**
     * 取语言文件config
     *
     * @return {@link FileConfiguration}
     */
    public FileConfiguration getLanguageConfig() {
        if (languageFile == null) {
            languageFile = new File(languageFolder, language);
        }
        return loadYml(languageFile);
    }

    private void writeToLocal(String path, InputStream input) throws IOException {
        if (input == null) {
            return;
        }
        int index;
        byte[] bytes = new byte[2048];
        FileOutputStream downloadFile;
        downloadFile = new FileOutputStream(path);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }
}