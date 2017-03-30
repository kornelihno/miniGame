package com.yggdrasil.minigame;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class GameStateTest {

    private GameState gs;
    
    @Before
    public void setUp() {
        gs = new GameStateImpl();
    }
    
    
    @Test
    public void testAddReward() {
        gs.addReward(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, gs.getRewardAmount());
        gs.addReward(BigDecimal.ONE);
        assertEquals(new BigDecimal(2), gs.getRewardAmount());
    }
    
    @Test
    public void testExtraLife() {
        gs.activateExtraLife();
        assertTrue(gs.isExtraLifeActive());
        gs.useExtraLife();
        assertFalse(gs.isExtraLifeActive());        
    }
    
    @Test
    public void testFinishingState() {
        assertTrue(gs.isSecondChanceAvailable());
        gs.goToFinishingState();
        assertTrue(gs.isInFinishingState());
        gs.returnFromFinishingState();
        assertFalse(gs.isInFinishingState());
    }
    
    @Test
    public void testFinishingGame() {
        gs.goToFinishingState();
        gs.finishGame();
        assertFalse(gs.isInFinishingState());
        assertTrue(gs.isGameOver());
    }
    
    @Test
    public void testSecondChance() {
        assertTrue(gs.isSecondChanceAvailable());
        gs.goToFinishingState();
        gs.returnFromFinishingState();
        assertFalse(gs.isSecondChanceAvailable());
    }
    
    @Test(expected = IllegalStateException.class) 
    public void shouldNotAllowExtraLife() {
        assertFalse(gs.isExtraLifeActive());
        gs.useExtraLife();
    }
    
    @Test(expected = IllegalStateException.class) 
    public void shouldNotAllowReturningFromFinishingState() {
        assertFalse(gs.isInFinishingState());
        gs.returnFromFinishingState();
    }
    
}
