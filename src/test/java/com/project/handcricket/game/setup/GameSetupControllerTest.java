package com.project.handcricket.game.setup;

import com.project.handcricket.model.Game;
import com.project.handcricket.model.Player;
import com.project.handcricket.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameSetupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameSetupService gameSetupService;

    @Autowired
    private TestUtils testUtils;

    @Test
    public void addGameTest() throws Exception {
        Game game = new Game();
        game.setId("ABC12");

        given(gameSetupService.getNewGame()).willReturn(game);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/game/addGame"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Game actualGame = testUtils.getResponseObject(result, Game.class);

        assertThat(actualGame.getId(), equalTo(game.getId()));
        assertThat(actualGame.getLastUpdated().toString(), equalTo(game.getLastUpdated().toString()));

    }

}
