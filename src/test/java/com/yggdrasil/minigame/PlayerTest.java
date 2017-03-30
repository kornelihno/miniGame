package com.yggdrasil.minigame;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yggdrasil.minigame.rewards.Box;
import com.yggdrasil.minigame.rewards.RewardType;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Mock
    Random rnd;
    @Mock
    Game game;
    @Mock
    Box box;

    @Test
    public void test() {
        Player p = new PlayerImpl(rnd);
        when(rnd.nextInt()).thenReturn(0);
        when(game.getAvailableBoxes()).thenReturn(Arrays.asList(Integer.valueOf(0)));
        when(game.getBox(0)).thenReturn(box);
        when(box.getRewardType()).thenReturn(RewardType.GAME_OVER);
        when(game.isExtraLifeActive()).thenReturn(false);
        when(game.getFinishingReward()).thenReturn(box);
        when(game.getReward()).thenReturn(BigDecimal.ONE);
        p.playGame(game);
        assertEquals(BigDecimal.ONE, p.getMoney());
    }
}
