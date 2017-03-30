package com.yggdrasil.minigame.rewards;

import java.math.BigDecimal;

import com.yggdrasil.minigame.GameState;

public class MoneyBox extends BaseBox {
    private BigDecimal value;
    public BigDecimal getValue() {
        return value;
    }
    public MoneyBox(BigDecimal value, GameState gameState) {
        super(gameState);
        this.value = value;
    }
    @Override
    public RewardType getRewardType() {
        return RewardType.MONEY;
    }
    @Override
    public void preformAction() {
        gameState.addReward(value);
    }
    
}
