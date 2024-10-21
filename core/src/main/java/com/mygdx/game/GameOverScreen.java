package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
    private final GameLluviaMenu game;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private OrthographicCamera camera;
    private float puntuacionFinal;
    private String razonFinJuego;  // Nueva variable para la razón del fin del juego

    public GameOverScreen(final GameLluviaMenu game, float puntuacionFinal, String razonFinJuego) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.puntuacionFinal = puntuacionFinal;
        this.razonFinJuego = razonFinJuego;  // Inicializa la razón
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
    	ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        font.draw(batch, "GAME OVER ", 200, 200);
        font.draw(batch, "Tu puntuación fue de: " + puntuacionFinal, 200, 150);
        font.draw(batch, razonFinJuego, 200, 100); // Mostrar la razón por la que terminó el juego
        font.draw(batch, "Toca en cualquier lado para reiniciar.", 150, 50);
        batch.end();

        if (Gdx.input.isTouched()) {
            Gdx.app.postRunnable(() -> {
                game.setScreen(new MainMenuScreen(game));
            });
        }
    }

    @Override
    public void show() {
        // Aquí puedes inicializar lo que necesites al mostrar la pantalla
    }

    @Override
    public void resize(int width, int height) {
        // Ajustar el tamaño de la pantalla si es necesario
    }

    @Override
    public void pause() {
        // Lógica para cuando se pausa
    }

    @Override
    public void resume() {
        // Lógica para cuando se reanuda
    }

    @Override
    public void hide() {
        // Lógica para cuando se oculta la pantalla
    }

    @Override
    public void dispose() {
    }
}
