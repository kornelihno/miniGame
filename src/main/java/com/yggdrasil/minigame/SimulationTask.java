package com.yggdrasil.minigame;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class SimulationTask implements Callable<BigDecimal> {

    private int noOfTrials;
    private CountDownLatch countDownLatch;
    private Game game;
    private Player player;

    public SimulationTask(int noOfTrials, CountDownLatch countDownLatch, Game game, Player player) {
        this.noOfTrials = noOfTrials;
        this.countDownLatch = countDownLatch;
        this.game = game;
        this.player = player;
    }

    @Override
    public BigDecimal call() throws Exception {
        BigDecimal reward = BigDecimal.ZERO;
        for (int i = 0; i < noOfTrials; i++) {
            player.playGame(game);
            BigDecimal won = player.getMoney();
            reward = reward.add(won);
            game.resetState();
        }
        countDownLatch.countDown();
        return reward;
    }

}
