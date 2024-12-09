package by.shift.minesweeper.singleton;

import by.shift.minesweeper.model.Game;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static Cache gameCache;
    private static final Map<String, Game> cacheRepository = new HashMap<>();

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

    public Game addGame(Game game) {
        return cacheRepository.put(game.getId(), game);
    }

    public Game getGame(String id) {
        return cacheRepository.get(id);
    }
}
