package com.yggdrasil.minigame.rewards;
/**
 * 
 * @author KWandzel
 *
 * EmptyBox representing used reward
 */
public enum EmptyBox implements Box {
    INSTANCE;

    @Override
    public void preformAction() {
    }

    @Override
    public RewardType getRewardType() {
        return null;
    }

}
