package com.yggdrasil.minigame.rewards;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.yggdrasil.minigame.GameState;

public class MiniGameBoxProvider implements BoxProvider {
    private static final int BOXES_NUMBER = 12;

    public MiniGameBoxProvider(Random random, GameState gameState) {
        this.random = random;
        this.gameState = gameState;
        init();
    }
    
    protected List<Box> boxes = new ArrayList<>();
    protected Random random;
    protected GameState gameState;
    private void init() {
        boxes.add(new MoneyBox(new BigDecimal(100), gameState));
        boxes.add(new MoneyBox(new BigDecimal(20), gameState));
        boxes.add(new MoneyBox(new BigDecimal(20), gameState));
        boxes.add(new MoneyBox(new BigDecimal(5), gameState));
        boxes.add(new MoneyBox(new BigDecimal(5), gameState));
        boxes.add(new MoneyBox(new BigDecimal(5), gameState));
        boxes.add(new MoneyBox(new BigDecimal(5), gameState));
        boxes.add(new MoneyBox(new BigDecimal(5), gameState));
        boxes.add(new ExtraLifeBox(gameState));
        boxes.add(new GameOverBox(gameState));
        boxes.add(new GameOverBox(gameState));
        boxes.add(new GameOverBox(gameState));
        assert boxes.size() == 12;
        Collections.shuffle(boxes, random);
    }
    
    public boolean isBoxAvailable(int index) {
        if(index < 0 || index >= BOXES_NUMBER) {
            throw new IllegalArgumentException("Wrong index of box.");
        }
        return boxes.get(index) != EmptyBox.INSTANCE;
    }

    public Box get(int index) {
        if(index < 0 || index >= BOXES_NUMBER) {
            throw new IllegalArgumentException("Wrong index of box.");
        }
        Box result = boxes.get(index);
        boxes.set(index, EmptyBox.INSTANCE);
        return result;
    }

    public List<Integer> getAvailableIndices() {
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < BOXES_NUMBER; i++) {
            if(boxes.get(i) != EmptyBox.INSTANCE) {
                result.add(i);
            }
        }
        return new ArrayList<Integer>(result);
    }

    @Override
    public void setRandom(Random random) {
        this.random = random;        
    }

    @Override
    public List<Box> getFinishingRewards(boolean secondChanceAvailable) {
        List<Box> choices = new ArrayList<>();
        choices.add(new FinishingMoneyBox(new BigDecimal(5), gameState));
        choices.add(new FinishingMoneyBox(new BigDecimal(10), gameState));
        choices.add(new FinishingMoneyBox(new BigDecimal(15), gameState));
        if(secondChanceAvailable)
            choices.add(new SecondChanceBox(gameState));
        return choices;
    }

    @Override
    public void reset() {
        boxes.clear();
        init();        
    }

}
