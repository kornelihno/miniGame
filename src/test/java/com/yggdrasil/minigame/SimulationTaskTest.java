package com.yggdrasil.minigame;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimulationTaskTest {
    
    private static final int NUMBER_OF_TRIALS = 2;
    @Mock
    CountDownLatch cdl;
    @Mock
    Game game;
    @Mock
    PlayerImpl player;
    
    @Test
    public void test() throws Exception {
        SimulationTask sm = new SimulationTask(NUMBER_OF_TRIALS,cdl,game,player);
        when(player.getMoney()).thenReturn(BigDecimal.ONE);
        BigDecimal res = sm.call();
        assertEquals(new BigDecimal(2), res);
        verify(player, times(2)).playGame(game);
        verify(game, times(2)).resetState();
        verify(cdl).countDown();
    }
}
