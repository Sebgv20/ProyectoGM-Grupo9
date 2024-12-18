package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;

// Pantalla para el gameOver
public class ScreenGameOver implements Screen {
    private final GameLluviaMenu game;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private OrthographicCamera camera;
    
    private float puntuacionFinal;
    private String razonFinJuego;
    private boolean muerte;

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
        // Cargar y reproducir la música de Game Over
        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("GameOverMusic.mp3"));
        gameFinishMusic = Gdx.audio.newMusic(Gdx.files.internal("GameFinishMusic.mp3"));
        
        // Música si el jugador sobrevivió todo el tiempo del nivel
        if (!muerte) {
        	gameFinishMusic.play();
        } 
        // Música para cuando el jugador pierde
        else {
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
        font.draw(batch, "GAME OVER:", 1920/2 - 230, 1080/2 + 100);
        font.draw(batch, razonFinJuego, 1920/2 - 30, 1080/2 +100);
        font.draw(batch, "Tu puntuación fue de: " + puntuacionFinal, 1920/2 - 230, 1080/2);
        font.draw(batch, "Toca en cualquier lado para reiniciar.", 720, 1080/2 - 400);
        
        batch.end();

        if (Gdx.input.isTouched()) {
            Gdx.app.postRunnable(() -> {
            	gameFinishMusic.stop();
                gameOverMusic.stop();
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
        gameOverMusic.stop();  // Detiene la música al salir de la pantalla
        gameFinishMusic.stop();  // Detiene la música al salir de la pantalla
    }

    @Override
    public void dispose() {
        gameOverMusic.dispose();  // Liberar los recursos de la música
        gameFinishMusic.dispose();  // Liberar los recursos de la música
    }
}