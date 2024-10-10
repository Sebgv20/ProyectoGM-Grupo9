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

    public GameOverScreen(final GameLluviaMenu game, float puntuacionFinal) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.puntuacionFinal = puntuacionFinal;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		font.draw(batch, "GAME OVER ", 100, 200);
		font.draw(batch, "Tu puntuación fue de: " + puntuacionFinal, 100, 150);
		font.draw(batch, "Toca en cualquier lado para reiniciar.", 100, 100);
		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new MainMenuScreen(game));
			dispose();
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
