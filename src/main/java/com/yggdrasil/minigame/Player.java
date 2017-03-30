package com.yggdrasil.minigame;

import java.math.BigDecimal;

public interface Player {

    BigDecimal getMoney();

    void playGame(Game game);

}