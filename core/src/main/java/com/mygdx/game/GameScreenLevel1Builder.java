package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

public class GameScreenLevel1Builder {
    private GameLluviaMenu game;
    private Music rainMusic;
    private Texture bucketLeftImage;
    private Texture bucketRightImage;
    private ILluvia lluvia;
    private float tiempoRestante;
    
    // Constructor
    public GameScreenLevel1Builder(GameLluviaMenu game) {
        this.game = game;
        this.tiempoRestante = 46; // Duración de la partida
    }
    
    // Métodos para configurar los parámetros
    public GameScreenLevel1Builder setRainMusic(Music music) {
        this.rainMusic = music;
        return this;
    }

    public GameScreenLevel1Builder setBucketTextures(Texture leftTexture, Texture rightTexture) {
        this.bucketLeftImage = leftTexture;
        this.bucketRightImage = rightTexture;
        return this;
    }
    
    public GameScreenLevel1Builder setLluvia(ILluvia lluvia) {
        this.lluvia = lluvia;
        return this;
    }

    public GameScreenLevel1Builder setTiempoRestante(float tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
        return this;
    }
    
    // Método para construir el objeto final
    public GameScreenLevel1 build() {
        GameScreenLevel1 screen = new GameScreenLevel1(game);
        screen.setBucketLeftImage(this.bucketLeftImage);
        screen.setBucketRightImage(this.bucketRightImage);
        screen.setTarro(new Tarro(bucketLeftImage, bucketRightImage));
        screen.setLluvia(this.lluvia);
        screen.setRainMusic(this.rainMusic);
        screen.setTiempoRestante(this.tiempoRestante);
        return screen;
    }
}
