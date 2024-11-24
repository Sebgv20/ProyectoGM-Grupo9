package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

public class GameScreenLevel3Builder {
    private GameLluviaMenu game;
    private Music rainMusic;
    private Texture bucketLeftImage;
    private Texture bucketRightImage;
    private LluviaLevel3 lluvia;
    private float tiempoRestante;
    
    // Constructor
    public GameScreenLevel3Builder(GameLluviaMenu game) {
        this.game = game;
        this.tiempoRestante = 60; // Duración de la partida
    }
    
    
    // Métodos para configurar los parámetros
    public GameScreenLevel3Builder setRainMusic(Music music) {
        this.rainMusic = music;
        return this;
    }

    public GameScreenLevel3Builder setBucketTextures(Texture leftTexture, Texture rightTexture) {
        this.bucketLeftImage = leftTexture;
        this.bucketRightImage = rightTexture;
        return this;
    }
    
    public GameScreenLevel3Builder setLluvia(LluviaLevel3 lluvia) {
        this.lluvia = lluvia;
        return this;
    }

    public GameScreenLevel3Builder setTiempoRestante(float tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
        return this;
    }
    
    // Método para construir el objeto final
    public GameScreenLevel3 build() {
        GameScreenLevel3 screen = new GameScreenLevel3(game);
        screen.setBucketLeftImage(this.bucketLeftImage);
        screen.setBucketRightImage(this.bucketRightImage);
        screen.setTarro(new Tarro(bucketLeftImage, bucketRightImage));
        screen.setLluvia(this.lluvia);
        screen.setRainMusic(this.rainMusic);
        screen.setTiempoRestante(this.tiempoRestante);
        return screen;
    }
}