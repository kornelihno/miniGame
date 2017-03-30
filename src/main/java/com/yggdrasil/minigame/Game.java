package com.yggdrasil.minigame;

import java.math.BigDecimal;
import java.util.List;

import com.yggdrasil.minigame.rewards.BoxProvider;
import com.yggdrasil.minigame.rewards.RewardFacade;

public interface Game {
    public RewardFacade getBox(int index);
    public BigDecimal getReward();
    public boolean isGameOver();
    public boolean isExtraLifeActive();
    public void useExtraLife();
    public List<Integer> getAvailableBoxes();
    public RewardFacade getFinishingReward();
    public void resetState();
    public void setBoxProvider(BoxProvider boxProvider);
}
