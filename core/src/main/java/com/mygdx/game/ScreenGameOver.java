package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

public class ScreenGameOver implements Screen {
    private final GameLluviaMenu game;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private OrthographicCamera camera;
    private float puntuacionFinal;
    private String razonFinJuego;
    private boolean muerte;

    private Texture wip;
    private Music gameOverMusic;  // Música para cuando se termina el juego por morir
    private Music gameFinishMusic;  // Música para cuando se sobrevive hasta el final

    public ScreenGameOver(final GameLluviaMenu game, float puntuacionFinal, String razonFinJuego, boolean muerte) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.puntuacionFinal = puntuacionFinal;
        this.razonFinJuego = razonFinJuego;
        this.muerte = muerte;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
    }

    @Override
    public void show() {
        wip = new Texture(Gdx.files.internal("WIP.png"));
        
        // Cargar y reproducir la música de Game Over
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("GameOverMusic.mp3"));
        gameFinishMusic = Gdx.audio.newMusic(Gdx.files.internal("GameFinishMusic.mp3"));
        
        if (!muerte) {
        	gameFinishMusic.play();
        } else {
        	gameOverMusic.setLooping(true);
            gameOverMusic.play();
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        batch.draw(wip, 20, 1080 - 180, 400, 160);
        
        font.draw(batch, "GAME OVER ", 200, 200);
        font.draw(batch, "Tu puntuación fue de: " + puntuacionFinal, 200, 150);
        font.draw(batch, razonFinJuego, 200, 100);
        font.draw(batch, "Toca en cualquier lado para reiniciar.", 150, 50);
        
        batch.end();

        if (Gdx.input.isTouched()) {
            Gdx.app.postRunnable(() -> {
                gameOverMusic.stop();  // Detener la música antes de cambiar de pantalla
                game.getMenuMusic().play();
                game.setScreen(new ScreenMainMenu(game));
            });
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        gameOverMusic.stop();  // Asegurarse de detener la música al salir de la pantalla
        gameFinishMusic.stop();  // Asegurarse de detener la música al salir de la pantalla
    }

    @Override
    public void dispose() {
        wip.dispose();
        gameOverMusic.dispose();  // Liberar los recursos de la música
        gameFinishMusic.dispose();  // Liberar los recursos de la música
    }
}