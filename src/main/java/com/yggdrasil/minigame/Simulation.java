package com.yggdrasil.minigame;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.yggdrasil.minigame.rewards.BoxProvider;
import com.yggdrasil.minigame.rewards.MiniGameBoxProvider;

public class Simulation {
    private int noOfTrials;
    private int noOfCores;

    public Simulation(int noOfTrials, int noOfCores) {
        this.noOfTrials = noOfTrials;
        this.noOfCores = noOfCores;
    }

    public BigDecimal doSimluation() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(noOfCores);
        Set<Future<BigDecimal>> futures = new HashSet<>();
        int trialsPerTask = noOfTrials / noOfCores;
        int reminder = noOfTrials % noOfCores;
        CountDownLatch cdl = new CountDownLatch(8);
        for(int i = 0; i < noOfCores; i++) {
            if(i == noOfCores - 1) {
                trialsPerTask += reminder;
            }
            Callable<BigDecimal> task = createTask(trialsPerTask, cdl);
            Future<BigDecimal> future = es.submit(task);
            futures.add(future);
        }
        cdl.await();        
        BigDecimal total = BigDecimal.ZERO;
        for(Future<BigDecimal> f : futures) {
            total = total.add(f.get());
        }
        es.shutdown();
        return total.divide(new BigDecimal(noOfTrials));
    }

    private SimulationTask createTask(int noOfTrials, CountDownLatch cdl) {
        Random rnd = new Random();
        GameState gs = new GameStateImpl();
        BoxProvider bp = new MiniGameBoxProvider(rnd, gs);
        Game game = new GameImpl(gs, rnd, bp);
        Player p = new PlayerImpl(rnd);
        return new SimulationTask(noOfTrials, cdl, game, p);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Simulation simulation = new Simulation(10_000_000, 8);
        BigDecimal result = simulation.doSimluation();
        System.out.println("Avg: " + result);
    }
}
