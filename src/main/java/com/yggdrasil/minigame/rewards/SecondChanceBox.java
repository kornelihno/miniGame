package com.yggdrasil.minigame.rewards;

import com.yggdrasil.minigame.GameState;

public class SecondChanceBox extends BaseBox {

    public SecondChanceBox(GameState gameState) {
        super(gameState);
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.SECOND_CHANCE;
    }

    @Override
    public void preformAction() {
        gameState.returnFromFinishingState();  
    }

}
