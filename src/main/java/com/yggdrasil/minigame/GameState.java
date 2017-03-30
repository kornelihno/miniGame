package com.yggdrasil.minigame;

import java.math.BigDecimal;

public interface GameState {
    boolean isGameOver();
    boolean isSecondChanceAvailable();
    boolean isExtraLifeActive();
    BigDecimal getRewardAmount();
    void resetState();
    void addReward(BigDecimal reward);
    void activateExtraLife();
    void useExtraLife();
    boolean isInFinishingState();
    void goToFinishingState();
    void returnFromFinishingState();
    void finishGame();
    
}
