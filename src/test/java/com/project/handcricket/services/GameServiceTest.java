package com.project.handcricket.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.game.setup.GameSetupService;
import com.project.handcricket.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

  @Autowired
  private GameSetupService gameSetupService;

  @Test
  public void setupGameTest() {
    Game game = gameSetupService.getNewGame();
    assert game.getId().length() == 5;
    assert game.getGameStatus() == GameStatus.NOT_STARTED;
  }

}
