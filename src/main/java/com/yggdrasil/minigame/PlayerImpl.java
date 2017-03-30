package com.yggdrasil.minigame;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import com.yggdrasil.minigame.rewards.RewardFacade;
import com.yggdrasil.minigame.rewards.RewardType;

public class PlayerImpl implements Player {
    protected Random random;
    protected BigDecimal money = BigDecimal.ZERO;
    public PlayerImpl(Random random) {
        this.random = random;
    }

    @Override
    public BigDecimal getMoney() {
        BigDecimal result = money;
        money = BigDecimal.ZERO;
        return result;
    }

    @Override
    public void playGame(Game game) {
        boolean canPlay = true;
        while (canPlay) {
            RewardFacade box = singleMove(game);
            if (!canPlay(game, box)) {
                canPlay = false;
            }
        }
        RewardFacade finishingReward = finishingReward(game);
        if (wonSecondChance(finishingReward)) {
            playGame(game);
        }
        money = money.add(game.getReward());
    }

    private boolean canPlay(Game game, RewardFacade box) {
        if(box.getRewardType() == RewardType.GAME_OVER) {
            if(game.isExtraLifeActive()) {
                game.useExtraLife();
                return true;
            }
            return false;
        }            
        return true;
    }

    private boolean wonSecondChance(RewardFacade finishingReward) {
        return finishingReward.getRewardType() == RewardType.SECOND_CHANCE;
    }

    private int getBoxIndex(Game game) {
        List<Integer> availableNumbers = game.getAvailableBoxes();
        int index = random.nextInt(availableNumbers.size());
        int boxNumber = availableNumbers.get(index);
        return boxNumber;
    }

    private RewardFacade singleMove(Game game) {
        int boxIndex = getBoxIndex(game);
        RewardFacade box = game.getBox(boxIndex);
        return box;
    }

    private RewardFacade finishingReward(Game game) {
        RewardFacade box = game.getFinishingReward();
        return box;
    }

}
