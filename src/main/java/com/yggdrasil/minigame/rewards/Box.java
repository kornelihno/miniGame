package com.yggdrasil.minigame.rewards;

public interface Box extends RewardFacade {
    public void preformAction();
    public RewardType getRewardType();
}
