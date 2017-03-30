package com.yggdrasil.minigame.rewards;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yggdrasil.minigame.GameState;
import com.yggdrasil.minigame.GameStateImpl;

public class ExtraLifeBoxTest {

    @Test
    public void testRewardType() {
        Box box = new ExtraLifeBox(null);
        assertSame(RewardType.EXTRA_LIFE, box.getRewardType());
    }
    
    @Test
    public void testAction() {
        GameState gs = new GameStateImpl();
        Box box = new ExtraLifeBox(gs);
        assertFalse(gs.isExtraLifeActive());
        box.preformAction();
        assertTrue(gs.isExtraLifeActive());
    }
}
