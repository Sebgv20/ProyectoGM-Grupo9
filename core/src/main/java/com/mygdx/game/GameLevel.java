package com.mygdx.game;

public interface GameLevel {
    void initialize();
    void update(float delta);
    void draw();
    boolean isLevelOver();
    float getFinalScore();
    float getTiempoRestante();
}
