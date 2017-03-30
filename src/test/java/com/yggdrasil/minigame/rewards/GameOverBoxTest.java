package com.yggdrasil.minigame.rewards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yggdrasil.minigame.GameState;
import com.yggdrasil.minigame.GameStateImpl;

public class GameOverBoxTest {

    @Test
    public void testRewardType() {
        Box gob = new GameOverBox(null);
        assertSame(RewardType.GAME_OVER, gob.getRewardType());
    }
    
    @Test
    public void testAction() {
        GameState gs = new GameStateImpl();
        Box gob = new GameOverBox(gs);
        gob.preformAction();
        assertTrue(gs.isInFinishingState());
    }
    
    @Test
    public void testActionExtraLifeActive() {
        GameState gs = new GameStateImpl();
        Box gob = new GameOverBox(gs);
        gs.activateExtraLife();
        gob.preformAction();
        assertFalse(gs.isInFinishingState());
    }
}
