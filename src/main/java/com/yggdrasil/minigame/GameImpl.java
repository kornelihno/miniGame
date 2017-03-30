package com.yggdrasil.minigame;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import com.yggdrasil.minigame.rewards.Box;
import com.yggdrasil.minigame.rewards.BoxProvider;
import com.yggdrasil.minigame.rewards.RewardFacade;

public class GameImpl implements Game {

    protected GameState gameState;
    protected Random random;
    protected BoxProvider boxProvider;

    public GameImpl(GameState gameState, Random random, BoxProvider boxProvider) {
        this.gameState = gameState;
        this.random = random;
        this.boxProvider = boxProvider;
    }

    public BigDecimal getReward() {
        return gameState.getRewardAmount();
    }

    public RewardFacade getBox(int index) {
        checkGetBoxState();
        Box box = null;
        if (boxProvider.isBoxAvailable(index)) {
            box = boxProvider.get(index);
            box.preformAction();
        }
        return box;
    }

    private void checkGetBoxState() {
        if (gameState.isGameOver() || gameState.isInFinishingState()) {
            System.out.println(gameState);
            throw new IllegalStateException("Player can't get any new box now.");
        }
    }

    private void checkFinishingState() {
        if (gameState.isGameOver() || !gameState.isInFinishingState()) {
            System.out.println(gameState);
            throw new IllegalStateException("Player can't get finishing reward.");
        }
    }

    public boolean isGameOver() {
        return gameState.isGameOver();
    }

    @Override
    public List<Integer> getAvailableBoxes() {
        return boxProvider.getAvailableIndices();
    }

    @Override
    public RewardFacade getFinishingReward() {
        checkFinishingState();
        Box reward = drawFinishingReward();
        reward.preformAction();
        return reward;
    }

    private Box drawFinishingReward() {
        List<Box> choices = boxProvider.getFinishingRewards(gameState.isSecondChanceAvailable());
        int idx = random.nextInt(choices.size());
        return choices.get(idx);
    }

    @Override
    public boolean isExtraLifeActive() {
        return gameState.isExtraLifeActive();
    }

    @Override
    public void resetState() {
        gameState.resetState();
        boxProvider.reset();
    }

    public void setBoxProvider(BoxProvider boxProvider) {
        this.boxProvider = boxProvider;
    }

    @Override
    public void useExtraLife() {
        gameState.useExtraLife();
    }

}
