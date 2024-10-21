package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    private Music menuMusic;  // La música del menú

    public MainMenuScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.getData().setScale(2, 2);
        font.draw(batch, "Bienvenido a The Game ", 100, camera.viewportHeight / 2 + 50);
        font.draw(batch, "Enter para jugar", 100, camera.viewportHeight / 2 - 50);
        batch.end();

        // Inicia el juego cuando el jugador presiona Enter
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            menuMusic.stop();  // Detener la música del menú
            game.setScreen(new GameScreen(game));  // Cambiar a la pantalla del juego
            dispose();  // Liberar recursos
        }
    }

    @Override
    public void show() {
        // Cargar y reproducir la música del menú una vez al mostrar la pantalla
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        menuMusic.setLooping(true);  // Que se repita mientras esté en el menú
        menuMusic.play();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        // Detener la música cuando se oculta la pantalla
        menuMusic.stop();
    }

    @Override
    public void dispose() {
        // Liberar la música del menú al finalizar
        menuMusic.dispose();
    }
}

