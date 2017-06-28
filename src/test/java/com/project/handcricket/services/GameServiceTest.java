package com.project.handcricket.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import com.project.handcricket.enums.GameStatus;
import com.project.handcricket.models.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

  @Autowired
  private GameService gameService;

  @Test
  public void setupGameTest() {
    Game game = gameService.setupGame();
    assert game.getId().length() == 5;
    assert game.getGameStatus() == GameStatus.NOT_STARTED;
  }

}
