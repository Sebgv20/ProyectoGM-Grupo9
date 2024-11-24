package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

public class GameScreenLevel2Builder {
    private GameLluviaMenu game;
    private Music rainMusic;
    private Texture bucketLeftImage;
    private Texture bucketRightImage;
    private LluviaLevel2 lluvia;
    private float tiempoRestante;
    
    // Constructor
    public GameScreenLevel2Builder(GameLluviaMenu game) {
        this.game = game;
        this.tiempoRestante = 50; // Duración de la partida
    }
    
    
    // Métodos para configurar los parámetros
    public GameScreenLevel2Builder setRainMusic(Music music) {
        this.rainMusic = music;
        return this;
    }

    public GameScreenLevel2Builder setBucketTextures(Texture leftTexture, Texture rightTexture) {
        this.bucketLeftImage = leftTexture;
        this.bucketRightImage = rightTexture;
        return this;
    }
    
    public GameScreenLevel2Builder setLluvia(LluviaLevel2 lluvia) {
        this.lluvia = lluvia;
        return this;
    }

    public GameScreenLevel2Builder setTiempoRestante(float tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
        return this;
    }
    
    // Método para construir el objeto final
    public GameScreenLevel2 build() {
        GameScreenLevel2 screen = new GameScreenLevel2(game);
        screen.setBucketLeftImage(this.bucketLeftImage);
        screen.setBucketRightImage(this.bucketRightImage);
        screen.setTarro(new Tarro(bucketLeftImage, bucketRightImage));
        screen.setLluvia(this.lluvia);
        screen.setRainMusic(this.rainMusic);
        screen.setTiempoRestante(this.tiempoRestante);
        return screen;
    }
}

