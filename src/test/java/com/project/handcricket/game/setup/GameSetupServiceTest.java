package com.project.handcricket.game.setup;

import com.project.handcricket.data.GameData;
import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.model.Game;
import com.project.handcricket.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameSetupServiceTest {

    @Autowired
    private GameSetupService gameSetupService;

    @Test
    public void getNewGameTest() {
        Game game = gameSetupService.getNewGame();
        assert game.getId().length() == 5;
        assert game.getGameStatus() == GameStatus.NOT_STARTED;
    }

    @Test
    public void hostGameTest() {
        String gameId = gameSetupService.hostGame(new Player());
        assert gameId != null;
        assert gameId.length() == 5;
    }

    @Test
    public void joinGameTest() {
        Player player = new Player();
        Game game = new Game();

        game = gameSetupService.joinGame(game.getId(), player);
        assert game == null;

        game = new Game();
        GameData.getInstance().addGame(game);
        game = gameSetupService.joinGame(game.getId(), player);
        assert game != null;
        assert game.getBowler() != null;
        assert game.getBowler().equals(player);
    }

    @Test
    public void processJoinTest() {
        Game game = new Game();
        Player batsman = new Player();
        Player bowler = new Player();
        game.setBatsman(batsman);
        gameSetupService.processJoin(game, bowler);

        assert game.getBowler() != null;
        assert game.getBowler().equals(bowler);
        assert game.getGameStatus() == GameStatus.IN_PROGRESS;
        assert game.isConnected();
    }

    @Test
    public void isGameOpenToPlayTest() {
        Game game = new Game();
        assert gameSetupService.isGameOpenToPlay(game);
    }
}
