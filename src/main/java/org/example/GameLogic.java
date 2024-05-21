package org.example;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class GameLogic implements Runnable {
    public static final int MAPHIGHT = 100;
    public static final int MAPWIDTH = 100;
    protected static final Logger logger = LogManager.getLogger();
    public boolean ongoing = true;
    private Player leftPlayer;
    private Player rightPlayer;
    private Ball ball;

    public GameLogic(String leftPlayerName, String rightPlayerName) {
        leftPlayer = new Player(MAPHIGHT / 2.0, leftPlayerName, true);
        rightPlayer = new Player(MAPWIDTH / 2.0, rightPlayerName, false);
        ball = new Ball(MAPHIGHT / 2.0, MAPWIDTH / 2.0);
    }

    @Data
    private static class Player {
        private double y;
        private String name;
        private boolean isLeftPlayer;
        private final int PLAYERSPEED = 5;

        public Player(double y, String name, boolean isLeftPlayer) {
            this.y = y;
            this.name = name;
            this.isLeftPlayer = isLeftPlayer;
        }
        public void move(double deltaY) {
            y = (y + deltaY % MAPHIGHT);
        }

    }
    @Data
    private static class Ball {
        private double x;
        private double y;
        private double angleDirection;
        private static final int BALLSPEED = 10;
        public Ball(double x, double y) {
            this.x = x;
            this.y = y;
            angleDirection = generateAngle();
        }
        private double generateAngle() {
            Random rand = new Random();
            double angel = rand.nextBoolean() ? 95 : 185;
            angel -= rand.nextInt(11);
            return angel;
        }
    }
    public void updateGameState() {
        //TODO
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        while (ongoing) {
            long currentTime = System.nanoTime();
            double deltaTime = currentTime - lastTime;
            if(deltaTime > 60) {
                updateGameState();
                lastTime = currentTime;
            }
            else{
                try {
                    wait(0,20);
                } catch (InterruptedException e) {
                    logger.debug(e.getMessage());
                }
            }
        }
    }

}
