package com.yggdrasil.minigame.rewards;

import com.yggdrasil.minigame.GameState;

public class GameOverBox extends BaseBox  {

    public GameOverBox(GameState gameState) {
        super(gameState);
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.GAME_OVER;
    }

    @Override
    public void preformAction() {
        if (!gameState.isExtraLifeActive()) {
            gameState.goToFinishingState();
        }        
    }

}
