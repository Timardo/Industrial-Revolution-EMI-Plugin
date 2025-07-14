package net.timardo.indrevemiplugin.config;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

import net.fabricmc.loader.api.FabricLoader;

import static net.timardo.indrevemiplugin.IndustrialRevolutionEMIPlugin.*;

public class IREmiPluginConfig {
    private static final File FILE = new File(Path.of(FabricLoader.getInstance().getConfigDir().toString(), MOD_ID + ".json").toString());
    private static final Gson GSON;
    public static final IREmiPluginConfig INSTANCE = new IREmiPluginConfig();
    
    // trying new funky stuff, don't laugh, I know this is unneccesserily overcomplicated
    static {
        GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(IREmiPluginConfig.class, (InstanceCreator<?>) type -> INSTANCE).create();
        INSTANCE.reload();
    }
    
    public boolean addMachineRecipes = true;
    public boolean addChargedVersions = true;
    public boolean hideNotImplementedItems = true;
    public boolean addMissingItems = true;
    public boolean disableExtraModIntegration = true;
    
    private IREmiPluginConfig() {}
    
    public IREmiPluginConfig reload() {
        LOGGER.info("Reloading config");
        
        try {
            if (FILE.createNewFile()) {
                LOGGER.info("No config file found, creating new one");
                saveConfig(GSON.toJson(this));
            } else {
                if (GSON.fromJson(new String(Files.readAllBytes(FILE.toPath())), IREmiPluginConfig.class) == null) {
                    throw new NullPointerException("The config file was empty.");
                } else {
                    LOGGER.info("Config successfully reloaded");
                }
            }
        } catch (Exception e) {
            LOGGER.error("The config file was empty, loading defaults");
        }
        
        return this;
    }

    private static void saveConfig(String json) {
        try (PrintWriter printWriter = new PrintWriter(FILE)) {
            printWriter.write(json);
            printWriter.flush();
        } catch (IOException e) {
            LOGGER.error("The config failed to save ", e);
        }
    }
}
