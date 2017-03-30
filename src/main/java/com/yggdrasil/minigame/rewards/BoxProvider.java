package com.yggdrasil.minigame.rewards;

import java.util.List;
import java.util.Random;

public interface BoxProvider {
    public boolean isBoxAvailable(int index);
    public Box get(int index);
    public List<Integer> getAvailableIndices();
    public void setRandom(Random random);
    public List<Box> getFinishingRewards(boolean secondChanceAvailable);
    public void reset();
}
