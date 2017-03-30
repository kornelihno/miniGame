package com.yggdrasil.minigame.rewards;

import java.math.BigDecimal;

import org.junit.Test;

import com.yggdrasil.minigame.GameState;
import com.yggdrasil.minigame.GameStateImpl;

import static org.junit.Assert.*;

public class MoneyBoxTest {

    @Test
    public void testSettingValue() {
        BigDecimal bd = new BigDecimal(3);
        GameState gs = new GameStateImpl();
        MoneyBox mb = new MoneyBox(bd, gs);
        assertEquals(bd, mb.getValue());
    }
    
    @Test
    public void testRewardType() {
        BigDecimal bd = new BigDecimal(3);
        GameState gs = new GameStateImpl();
        MoneyBox mb = new MoneyBox(bd, gs);
        assertSame(RewardType.MONEY, mb.getRewardType());
    }
    
    @Test
    public void testPerformAction() {
        BigDecimal bd = new BigDecimal(3);
        GameState gs = new GameStateImpl();
        MoneyBox mb = new MoneyBox(bd, gs);
        mb.preformAction();
        mb.preformAction();
        assertEquals(bd.multiply(new BigDecimal(2)), gs.getRewardAmount());
    }
}
