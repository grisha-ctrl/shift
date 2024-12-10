package by.shift.minesweeper.singleton;

import by.shift.minesweeper.model.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private static volatile Cache gameCache;
    private static final Map<String, Game> cacheRepository = new ConcurrentHashMap<>();

    private Cache() {
    }

    public static synchronized Cache getGameCache() {
        if (gameCache == null) {
            gameCache = new Cache();
        }
        return gameCache;
    }

    public boolean containsGame(String gameId){
        return cacheRepository.containsKey(gameId);
    }

    public void addGame(Game game) {
        cacheRepository.put(game.getId(), game);
    }

    public Game getGame(String id) {
        return cacheRepository.get(id);
    }
}
