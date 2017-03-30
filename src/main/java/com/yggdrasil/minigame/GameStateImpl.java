package com.yggdrasil.minigame;

import java.math.BigDecimal;

public class GameStateImpl implements GameState {

    private boolean gameOver = false;
    private boolean secondChanceAvailable = true;
    private boolean extraLifeActive = false;
    private boolean inFinishingState = false;
    private BigDecimal rewardAmount = BigDecimal.ZERO;
    
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isSecondChanceAvailable() {
        return secondChanceAvailable;
    }

    public void setSecondChanceAvailable(boolean secondChanceAvailable) {
        this.secondChanceAvailable = secondChanceAvailable;
    }

    public boolean isExtraLifeActive() {
        return extraLifeActive;
    }

    public void setExtraLifeActive(boolean extraLifeActive) {
        this.extraLifeActive = extraLifeActive;
    }

    @Override
    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    @Override
    public void resetState() {
        gameOver = false;
        secondChanceAvailable = true;
        extraLifeActive = false;
        inFinishingState = false;
        rewardAmount = BigDecimal.ZERO;
    }

    @Override
    public void addReward(BigDecimal reward) {
        rewardAmount = rewardAmount.add(reward);
    }

    @Override
    public void activateExtraLife() {
        extraLifeActive = true;        
    }

    @Override
    public void useExtraLife() {
        if(!extraLifeActive) {
            throw new IllegalStateException("Extra Life not available");
        }
        extraLifeActive = false;
    }

    @Override
    public boolean isInFinishingState() {
        return inFinishingState;
    }

    @Override
    public void goToFinishingState() {
        inFinishingState = true;
    }

    @Override
    public void returnFromFinishingState() {
        if(!isInFinishingState())
            throw new IllegalStateException("Cannot return from finishing state.");
        inFinishingState = false;        
        secondChanceAvailable = false;
    }

    @Override
    public void finishGame() {
        returnFromFinishingState();
        gameOver = true;        
    }

    @Override
    public String toString() {
        return "GameStateImpl [gameOver=" + gameOver + ", secondChanceAvailable="
                + secondChanceAvailable + ", extraLifeActive=" + extraLifeActive
                + ", inFinishingState=" + inFinishingState + ", rewardAmount=" + rewardAmount + "]";
    }
    
    

}
