package com.yggdrasil.minigame.rewards;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.yggdrasil.minigame.GameState;
import com.yggdrasil.minigame.GameStateImpl;

public class MiniGameBoxProviderTest {

    BoxProvider boxProvider;
    GameState gameState;
    
    @Before
    public void setUp() {
        gameState = new GameStateImpl();
        Random rnd = new Random();
        boxProvider = new MiniGameBoxProvider(rnd, gameState);
    }
    
    @Test
    public void testAvailableIndices() {
        List<Integer> availableIndices = boxProvider.getAvailableIndices();
        assertEquals(12, availableIndices.size());
        for(int i = 0; i < 12; i++) {
            assertEquals(i, availableIndices.get(i).intValue());
        }
        boxProvider.get(1);
        boxProvider.get(3);
        boxProvider.get(5);
        boxProvider.get(7);
        boxProvider.get(9);
        boxProvider.get(11);
        availableIndices = boxProvider.getAvailableIndices();
        assertEquals(6, availableIndices.size());
        for(int i = 0; i < 6; i++) {
            assertEquals(i*2, availableIndices.get(i).intValue());
        }
    }
    
    @Test
    public void testIsAvailable() {
        for(int i = 0; i < 12; i++) {
            assertTrue(boxProvider.isBoxAvailable(i));
        }
        boxProvider.get(1);
        boxProvider.get(3);
        boxProvider.get(5);
        boxProvider.get(7);
        boxProvider.get(9);
        boxProvider.get(11);
        for(int i = 0; i < 12; i++) {
            if(i % 2 == 0) {
                assertTrue(boxProvider.isBoxAvailable(i));
            } else {
                assertFalse(boxProvider.isBoxAvailable(i));
            }
        }
    }
    
    @Test
    public void testGet() {
        Box box = boxProvider.get(0);
        assertNotNull(box);
        assertFalse(boxProvider.isBoxAvailable(0));
    }
    
    @Test
    public void testFinishingRewards() {
        List<Box> boxes = boxProvider.getFinishingRewards(true);
        int moneyBoxCnt = 0;
        int secondChanceCnt = 0;
        assertEquals(4, boxes.size());
        for(Box b : boxes) {
            if(b.getRewardType()==RewardType.MONEY) {
                moneyBoxCnt++;
            } else if(b.getRewardType() == RewardType.SECOND_CHANCE) {
                secondChanceCnt++;
            } else {
                fail();
            }
        }
        assertEquals(3, moneyBoxCnt);
        assertEquals(1, secondChanceCnt);
    }
    
    @Test
    public void testFinishingRewardsWithoutSecondChance() {
        List<Box> boxes = boxProvider.getFinishingRewards(false);
        int moneyBoxCnt = 0;
        int secondChanceCnt = 0;
        assertEquals(3, boxes.size());
        for(Box b : boxes) {
            if(b.getRewardType()==RewardType.MONEY) {
                moneyBoxCnt++;
            } else if(b.getRewardType() == RewardType.SECOND_CHANCE) {
                secondChanceCnt++;
            } else {
                fail();
            }
        }
        assertEquals(3, moneyBoxCnt);
        assertEquals(0, secondChanceCnt);
    }
}
