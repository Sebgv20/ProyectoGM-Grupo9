package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;

public class GameLluviaMenu extends Game {
    private SpriteBatch batch;
    private BitmapFont font;
    private Music menuMusic;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        // Cargar y reproducir la música del menú en la clase principal
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        menuMusic.setLooping(true);  // Repetir la música en bucle
        menuMusic.play();

        this.setScreen(new ScreenMainMenu(this));  // Comenzar en el menú principal
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public Music getMenuMusic() {
        return menuMusic;
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        menuMusic.dispose();  // Liberar la música cuando se cierra el juego
    }
}