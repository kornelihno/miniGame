package com.yggdrasil.minigame.rewards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yggdrasil.minigame.GameState;
import com.yggdrasil.minigame.GameStateImpl;

public class SecondChanceBoxTest {

    @Test
    public void rewardTypeTest() {
        SecondChanceBox box = new SecondChanceBox(null);
        assertSame(RewardType.SECOND_CHANCE, box.getRewardType());
    }
    
    @Test
    public void performActionTest() {
        GameState gs = new GameStateImpl();
        gs.goToFinishingState();
        SecondChanceBox box = new SecondChanceBox(gs);
        assertTrue(gs.isSecondChanceAvailable());
        box.preformAction();
        assertFalse(gs.isInFinishingState());
        assertFalse(gs.isSecondChanceAvailable());
    }
}
