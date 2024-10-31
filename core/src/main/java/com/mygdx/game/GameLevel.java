package com.mygdx.game;

// Interfaz para los diferentes niveles
public interface GameLevel {
    void initialize();
    void update(float delta);
    void draw();
    boolean isLevelOver();
    float getFinalScore();
    float getTiempoRestante();
}