package com.yggdrasil.minigame.rewards;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.yggdrasil.minigame.GameState;
import com.yggdrasil.minigame.GameStateImpl;

public class FinishingMoneyBoxTest {

    @Test
    public void performActionTest() {
        GameState gs = new GameStateImpl();
        gs.goToFinishingState();
        Box box = new FinishingMoneyBox(BigDecimal.ZERO, gs);
        box.preformAction();
        assertTrue(gs.isGameOver());
        assertFalse(gs.isInFinishingState());
    }
    
}
