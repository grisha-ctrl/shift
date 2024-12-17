package by.shift.minesweeper.service.impl;

import by.shift.minesweeper.TestContextConfiguration;
import by.shift.minesweeper.model.Cell;
import by.shift.minesweeper.model.Game;
import by.shift.minesweeper.service.GameService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestContextConfiguration.class)
public class DefaultGameServiceTest {

    @Autowired
    private GameService gameService;
    private final Cell[][] mockBoard = new Cell[10][10];

    @Test
    void startGameTest() {
        Game expected = new Game("1", 10, 10, 10);
        Game actual = gameService.startNewGame();
        Assertions.assertThat(actual).isEqualToIgnoringGivenFields(expected, "id", "board");
    }

    @Test
    void revealCellTest() {
        Game game = gameService.startNewGame();
        game = gameService.revealCell(game.getId(), 0, 0);
        Cell cell = game.getCell(0, 0);
        Assertions.assertThat(cell.isRevealed()).isEqualTo(true);
        Assertions.assertThat(cell.isFlagged()).isEqualTo(false);
        Assertions.assertThat(cell.isMine()).isEqualTo(false);
    }

    @ParameterizedTest
    @MethodSource("provideIds")
    void revealCellWithWrongIdTest(String id) {
        gameService.startNewGame();
        Assertions.assertThatThrownBy(() -> gameService.revealCell(id, 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Game ID не может быть null или empty.");
    }

    @Test
    void revealCellNonExistentGameIdTest() {
        gameService.startNewGame();
        Assertions.assertThatThrownBy(() -> gameService.revealCell("id", 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Не существует игры с таким id");
    }

    @ParameterizedTest
    @MethodSource("provideCoordinates")
    void revealCellWithWrongCoordinatesTest(int row, int col) {
        Game game = gameService.startNewGame();
        Assertions.assertThatThrownBy(() -> gameService.revealCell(game.getId(), row, col))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Координаты за пределами границ: row=" + row + ", col=" + col);
    }

    @ParameterizedTest
    @MethodSource("provideIds")
    void toggleFlagWithWrongIdTest(String id) {
        gameService.startNewGame();
        Assertions.assertThatThrownBy(() -> gameService.toggleFlag(id, 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Game ID не может быть null или empty.");
    }

    @Test
    void toggleFlagNonExistentGameIdTest() {
        gameService.startNewGame();
        Assertions.assertThatThrownBy(() -> gameService.toggleFlag("id", 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Не существует игры с таким id");
    }

    @ParameterizedTest
    @MethodSource("provideCoordinates")
    void toggleFlagWithWrongCoordinatesTest(int row, int col) {
        Game game = gameService.startNewGame();
        Assertions.assertThatThrownBy(() -> gameService.toggleFlag(game.getId(), row, col))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Координаты за пределами границ: row=" + row + ", col=" + col);
    }

    @Test
    void toggleFlagTest() {
        fillMockedBoard();
        Game game = gameService.startNewGame();
        game = gameService.revealCell(game.getId(), 0, 0);
        game.setBoard(mockBoard);
        Game actual = gameService.revealCell(game.getId(), 0, 0);
        gameService.toggleFlag(game.getId(), 0, 3);
        Cell cell = actual.getCell(0, 3);
        Assertions.assertThat(cell.isRevealed()).isEqualTo(false);
        Assertions.assertThat(cell.isFlagged()).isEqualTo(true);
        gameService.toggleFlag(game.getId(), 0, 3);
        Assertions.assertThat(cell.isRevealed()).isEqualTo(false);
        Assertions.assertThat(cell.isFlagged()).isEqualTo(false);
    }

    @Test
    void revealCellWhenGameIsOverTest() {
        Game game = gameService.startNewGame();
        game = gameService.revealCell(game.getId(), 0, 0);
        game.setGameOver(true);
        Assertions.assertThat(game).isEqualTo(gameService.revealCell(game.getId(), 2, 2));
    }

    @Test
    void revealCellWhenCellIsMineTest() {
        fillMockedBoard();
        Game game = gameService.startNewGame();
        game = gameService.revealCell(game.getId(), 0, 0);
        game.setBoard(mockBoard);
        Game actual = gameService.revealCell(game.getId(), 0, 3);
        Assertions.assertThat(actual.isGameOver()).isEqualTo(true);
    }

    @Test
    void openCellAndRevealedAdjacentCellsTest() {
        fillMockedBoard();
        Game game = gameService.startNewGame();
        game = gameService.revealCell(game.getId(), 0, 0);
        game.setBoard(mockBoard);
        Game actual = gameService.revealCell(game.getId(), 0, 0);
        List<Cell> revealedCells = new ArrayList<>();
        revealedCells.add(actual.getCell(0, 0));
        revealedCells.add(actual.getCell(0, 1));
        revealedCells.add(actual.getCell(0, 2));
        revealedCells.add(actual.getCell(1, 0));
        revealedCells.add(actual.getCell(1, 1));
        revealedCells.add(actual.getCell(1, 2));
        revealedCells.add(actual.getCell(2, 0));
        revealedCells.add(actual.getCell(2, 1));
        Cell[][] actualBoard = actual.getBoard();
        for (int row = 0; row < actual.getRows(); row++) {
            for (int col = 0; col < actual.getCols(); col++) {
                Assertions.assertThat(actualBoard[row][col].isRevealed())
                        .isEqualTo(revealedCells.contains(actualBoard[row][col]));
            }
        }
    }

    @Test
    void allMinesFlaggedTest() {
        fillMockedBoard();
        Game game = gameService.startNewGame();
        game = gameService.revealCell(game.getId(), 0, 0);
        game.setBoard(mockBoard);
        gameService.revealCell(game.getId(), 0, 0);
        gameService.toggleFlag(game.getId(), 0, 3);
        gameService.toggleFlag(game.getId(), 1, 9);
        gameService.toggleFlag(game.getId(), 2, 2);
        gameService.toggleFlag(game.getId(), 3, 0);
        gameService.toggleFlag(game.getId(), 4, 3);
        gameService.toggleFlag(game.getId(), 4, 7);
        gameService.toggleFlag(game.getId(), 6, 2);
        gameService.toggleFlag(game.getId(), 6, 7);
        gameService.toggleFlag(game.getId(), 7, 5);
        Game actual = gameService.toggleFlag(game.getId(), 9, 5);

        Assertions.assertThat(actual.isGameOver()).isEqualTo(true);
    }

    private void fillMockedBoard() {
        String[] boardData = {
                "001*100011",
                "012210001*",
                "12*1000011",
                "*222101110",
                "111*101*10",
                "0122102220",
                "01*1112*10",
                "01111*2110",
                "0000222000",
                "00001*1000"
        };
        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length(); j++) {

                char cell = boardData[i].charAt(j);
                if (cell == '*') {
                    mockBoard[i][j] = new Cell();
                    mockBoard[i][j].setMine(true);
                    mockBoard[i][j].setRevealed(false);
                    mockBoard[i][j].setFlagged(false);
                    mockBoard[i][j].setAdjacentMines(-1);
                } else {
                    int value = Character.getNumericValue(cell);
                    mockBoard[i][j] = new Cell();
                    mockBoard[i][j].setMine(false);
                    mockBoard[i][j].setRevealed(false);
                    mockBoard[i][j].setFlagged(false);
                    mockBoard[i][j].setAdjacentMines(value);
                }
            }
        }
    }

    private static Stream<Arguments> provideIds() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of("")
        );
    }

    private static Stream<Arguments> provideCoordinates() {
        return Stream.of(
                Arguments.of(-1, 2),
                Arguments.of(-1, -2),
                Arguments.of(1, -2),
                Arguments.of(111, 2),
                Arguments.of(1, 222),
                Arguments.of(111, 222)
        );
    }
}
