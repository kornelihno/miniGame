package com.yggdrasil.minigame.rewards;

import com.yggdrasil.minigame.GameState;

public class ExtraLifeBox extends BaseBox {

    public ExtraLifeBox(GameState gameState) {
        super(gameState);
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.EXTRA_LIFE;
    }

    @Override
    public void preformAction() {
        gameState.activateExtraLife();
    }

}
