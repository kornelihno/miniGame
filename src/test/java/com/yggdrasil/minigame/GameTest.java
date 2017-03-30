package com.yggdrasil.minigame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.yggdrasil.minigame.rewards.Box;
import com.yggdrasil.minigame.rewards.BoxProvider;
import com.yggdrasil.minigame.rewards.RewardFacade;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    Random random;
    @Mock
    BoxProvider boxProvider;
    @Mock
    Box box;
    GameState gameState = new GameStateImpl();

    @Test
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAvailableBoxes() {
        Game game = new GameImpl(gameState, random, boxProvider);
        List<Integer> availableIndices = Arrays.asList(1, 2, 3, 4, 5, 6);
        when(boxProvider.getAvailableIndices()).thenReturn(availableIndices);
        List<Integer> resultIndices = game.getAvailableBoxes();
        assertNotNull(resultIndices);
        assertEquals(availableIndices, resultIndices);
    }

    @Test
    public void testExtraLife() {
        Game game = new GameImpl(gameState, random, boxProvider);
        gameState.activateExtraLife();
        assertTrue(game.isExtraLifeActive());
        game.useExtraLife();
        assertFalse(game.isExtraLifeActive());
    }

    @Test
    public void testGetBox() {
        Game game = new GameImpl(gameState, random, boxProvider);
        when(boxProvider.isBoxAvailable(3)).thenReturn(true);
        when(boxProvider.get(3)).thenReturn(box);
        RewardFacade res = game.getBox(3);
        assertNotNull(res);
        assertSame(box, res);
        verify(box).preformAction();
    }

    @Test
    public void testFinishingReward() {
        Game game = new GameImpl(gameState, random, boxProvider);
        gameState.goToFinishingState();
        when(boxProvider.getFinishingRewards(anyBoolean())).thenReturn(Arrays.asList(box, box));
        RewardFacade rew = game.getFinishingReward();
        assertSame(rew, box);
        assertFalse(gameState.isGameOver());
    }
}
