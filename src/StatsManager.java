import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StatsManager {
    private static final String FILE_PATH = "player_stats.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveStats(PlayerStatistics newStats) {
        List<PlayerStatistics> allStats = loadStats();
        allStats.add(newStats);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(allStats, writer);
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio: " + e.getMessage());
        }
    }

    public static List<PlayerStatistics> loadStats() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<PlayerStatistics>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
