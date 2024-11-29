package by.shift.minesweeper.controller;

import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game")
    public Game startNewGame() {
        return gameService.startNewGame();
    }

    @PostMapping("/game/{id}/reveal")
    public Game revealCell(@PathVariable("id") String id, @RequestParam("row") int row, @RequestParam("col") int col) {
        return gameService.revealCell(id, row, col);
    }

    @PostMapping("/game/{id}/toggle-flag")
    public Game toggleFlag(@PathVariable("id") String id, @RequestParam("row") int row, @RequestParam("col") int col) {
        return gameService.toggleFlag(id, row, col);
    }
}
