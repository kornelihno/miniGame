package com.yggdrasil.minigame.rewards;

import com.yggdrasil.minigame.GameState;

public abstract class BaseBox implements Box {
    protected final GameState gameState;
    public BaseBox(GameState gameState) {
        this.gameState = gameState;
    }
    @Override
    public String toString() {
        return getRewardType() + ", " + gameState;
    }
    
}
