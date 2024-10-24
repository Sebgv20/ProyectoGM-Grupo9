package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;

public class ScreenMainMenu implements Screen {
    final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    // Texturas para los botones
    private Texture buttonPlay;
    private Texture buttonTutorial;
    private Texture buttonExit;
    private Texture logo;

    public ScreenMainMenu(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {
        // Cargar las imágenes de los botones
        buttonPlay = new Texture(Gdx.files.internal("ButtonPlay.png"));
        buttonTutorial = new Texture(Gdx.files.internal("ButtonTutorial.png"));
        buttonExit = new Texture(Gdx.files.internal("ButtonExit.png"));
        logo = new Texture(Gdx.files.internal("Logo.png"));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar los textos
        font.getData().setScale(1, 1);
        font.draw(batch, "Haz click en la tecla indicada para las opciones", 249, 470);

        // Dibujar las imágenes de los botones
        batch.draw(buttonPlay, 100, camera.viewportHeight / 2 + 50, 200, 80);
        batch.draw(buttonTutorial, 100, camera.viewportHeight / 2 - 50, 200, 80);
        batch.draw(buttonExit, 100, camera.viewportHeight / 2 - 150, 200, 80);
        batch.draw(logo, 380, camera.viewportHeight / 2-158, 300, 300);

        batch.end();

        // Inicia el juego cuando el jugador presiona 1
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.getMenuMusic().stop();
            game.setScreen(new GameScreen(game));  // Cambiar a la pantalla del juego
            dispose();  // Liberar recursos de esta pantalla
        }

        // Cambia al tutorial cuando el jugador presiona 2
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            game.setScreen(new ScreenTutorial(game));  // Cambiar a la pantalla del tutorial
            dispose();  // Liberar recursos de esta pantalla
        }

        // Cierra el juego cuando el jugador presiona Esc
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            Gdx.app.exit();  // Cierra la aplicación
        }
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
    }

    @Override
    public void dispose() {
        // Liberar los recursos de las texturas
        buttonPlay.dispose();
        buttonTutorial.dispose();
        buttonExit.dispose();
    }
}