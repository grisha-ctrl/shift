package by.shift.minesweeper.service.impl;

import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.service.GameService;
import by.shift.minesweeper.singleton.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class DefaultGameService implements GameService {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Logger log = LoggerFactory.getLogger(DefaultGameService.class);

    @Override
    public Game startNewGame() {
        Game game = new Game(generateId(), 10, 10, 10);
        Cache.getGameCache().addGame(game);
        log.info("Создана игра с фиксированными параметрами");
        return game;
    }

    @Override
    public Game revealCell(String id, int row, int col) {
        idValidation(id);

        Game game = Cache.getGameCache().getGame(id);

        coordinatesValidation(game, row, col);

        log.info("Открываем клетку, строка {} столбец {}", row, col);
        if (game.isGameOver()) {
            return game;
        }

        Cell cell = game.getCell(row, col);

        if (cell.isFlagged()) return game;

        if (!isBoardInitialized(game)) {
            placeMines(game, row, col);
            calculateAdjacentMines(game);
        }

        if (reveal(cell)) {
            game.setGameOver(true);
            log.info("Вы проиграли");
        } else if (cell.getAdjacentMines() == 0) {
            revealAdjacentCells(game, row, col);
        }
        return game;
    }

    @Override
    public Game toggleFlag(String id, int row, int col) {
        idValidation(id);

        Game game = Cache.getGameCache().getGame(id);

        coordinatesValidation(game, row, col);

        if (game.isGameOver()) {
            return game;
        }

        Cell cell = game.getCell(row, col);

        if (!isBoardInitialized(game)) {
            placeMines(game, row, col);
            calculateAdjacentMines(game);
        }

        if (!cell.isRevealed()) {
            if (cell.isFlagged()) {
                log.info("Убирает состояние флажка на указанной клетке, строка {} столбец {}", row, col);
                cell.setFlagged(false);
                game.setFlagCount(game.getFlagCount() + 1);
                if (cell.isMine()) {
                    game.setFlaggedMines(game.getFlaggedMines() - 1);
                }
            } else if (game.getFlagCount() > 0) {
                log.info("Добавляет состояние флажка на указанной клетке, строка {} столбец {}", row, col);
                cell.setFlagged(true);
                game.setFlagCount(game.getFlagCount() - 1);
                if (cell.isMine()) {
                    game.setFlaggedMines(game.getFlaggedMines() + 1);
                }
            }
        }
        checkWinCondition(game);
        return game;
    }

    private void coordinatesValidation(Game game, int row, int col) {
        if (row < 0 || row >= game.getRows() || col < 0 || col >= game.getCols()) {
            throw new IllegalArgumentException("Координаты за пределами границ: row=" + row + ", col=" + col);
        }
    }

    private void idValidation(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Game ID не может быть null или empty.");
        }

        if (!Cache.getGameCache().containsGame(id)) {
            throw new IllegalArgumentException("Не существует игры с таким id");
        }
    }

    private void placeMines(Game game, int firstRow, int firstCol) {
        int minesPlaced = 0;

        while (minesPlaced < game.getMinesCount()) {
            int row = secureRandom.nextInt(game.getRows());
            int col = secureRandom.nextInt(game.getCols());
            if ((Math.abs(row - firstRow) <= 1 && Math.abs(col - firstCol) <= 1) || game.getCell(row, col).isMine()) {
                continue;
            }
            game.getCell(row, col).setMine(true);
            minesPlaced++;
        }
    }

    private void calculateAdjacentMines(Game game) {
        for (int i = 0; i < game.getRows(); i++) {
            for (int j = 0; j < game.getCols(); j++) {
                if (!game.getCell(i, j).isMine()) {
                    game.getCell(i, j).setAdjacentMines(countAdjacentMines(game, i, j));
                }
            }
        }
    }

    private int countAdjacentMines(Game game, int row, int col) {
        int mineCount = 0;
        int[] directions = {-1, 0, 1};

        for (int dx : directions) {
            for (int dy : directions) {
                if (dx == 0 && dy == 0) continue;

                int newRow = row + dx;
                int newCol = col + dy;

                if (newRow >= 0 && newRow < game.getRows() && newCol >= 0 && newCol < game.getCols()) {
                    if (game.getCell(newRow, newCol).isMine()) {
                        mineCount++;
                    }
                }
            }
        }
        return mineCount;
    }

    private boolean isBoardInitialized(Game game) {
        for (int i = 0; i < game.getRows(); i++) {
            for (int j = 0; j < game.getCols(); j++) {
                if (game.getCell(i, j).isMine()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean revealAdjacentCells(Game game, int row, int col) {
        boolean mineRevealed = false;
        int[] directions = {-1, 0, 1};

        for (int dx : directions) {
            for (int dy : directions) {
                if (dx == 0 && dy == 0) continue;

                int newRow = row + dx;
                int newCol = col + dy;

                if (newRow >= 0 && newRow < game.getRows() && newCol >= 0 && newCol < game.getCols()) {
                    Cell adjacentCell = game.getCell(newRow, newCol);
                    if (!adjacentCell.isRevealed() && !adjacentCell.isFlagged()) {
                        reveal(adjacentCell);
                        if (adjacentCell.isMine()) {
                            mineRevealed = true;
                        } else if (adjacentCell.getAdjacentMines() == 0) {
                            mineRevealed = revealAdjacentCells(game, newRow, newCol) || mineRevealed;
                        }
                    }
                }
            }
        }
        return mineRevealed;
    }

    private void checkWinCondition(Game game) {
        if (game.getFlaggedMines() == 10) {
            log.info("Вы победили");
            game.setGameOver(true);
        }
    }

    private String generateId() {
        return String.valueOf(secureRandom.nextInt(1000000));
    }

    public boolean reveal(Cell cell) {
        if (cell.isRevealed() || cell.isFlagged()) {
            return false;
        }
        cell.setRevealed(true);
        return cell.isMine();
    }
}
