package com.yggdrasil.minigame.rewards;

import java.math.BigDecimal;

import com.yggdrasil.minigame.GameState;

public class FinishingMoneyBox extends MoneyBox implements Box {

    public FinishingMoneyBox(BigDecimal value, GameState gameState) {
        super(value, gameState);
    }

    @Override
    public void preformAction() {
        super.preformAction();
        gameState.finishGame();
    }

    
}
