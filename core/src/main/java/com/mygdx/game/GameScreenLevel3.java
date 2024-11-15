package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreenLevel3 implements Screen, GameLevel {
    private GameLluviaMenu game;  // Referencia al objeto principal que maneja el ciclo de vida del juego
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Texture bucketLeftImage;
    private Texture bucketRightImage;
    private Tarro tarro;
    private LluviaLevel3 lluvia;
    private Music rainMusic;
    private float tiempoRestante;
    private boolean temporizadorActivo;
    private boolean isGameOver;

    public GameScreenLevel3(GameLluviaMenu game) {
        this.game = game;
        this.isGameOver = false;
    }

    @Override
    public void initialize() {
        font = new BitmapFont();
        font.getData().setScale(2.0f);
        tiempoRestante = 46; // Duración de la partida
        temporizadorActivo = true;
        bucketLeftImage = new Texture(Gdx.files.internal("bucketIzq.png"));
        bucketRightImage = new Texture(Gdx.files.internal("bucketDer.png"));
        tarro = new Tarro(bucketLeftImage, bucketRightImage);
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("GameMusic.mp3"));
        lluvia = new LluviaLevel3(rainMusic);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        tarro.crear();
        lluvia.crear();
    }

    @Override
    public void show() {
        initialize();
    }

    @Override
    public void update(float delta) {
        if (temporizadorActivo) {
            tiempoRestante -= delta;
            if (tiempoRestante <= 0) {
                tiempoRestante = 0;
                temporizadorActivo = false;
                isGameOver = true;
                rainMusic.stop();
                Gdx.app.postRunnable(() -> game.setScreen(new ScreenGameOver(game, getFinalScore(), "¡Se acabó el tiempo!", false)));
            }
        }

        if (!isGameOver) {
            if (!tarro.estaHerido()) {
                tarro.actualizarMovimiento();       
                lluvia.actualizarMovimiento(tarro, game); 
            }
            if (tarro.getVidas() <= 0) {
                isGameOver = true;
                rainMusic.stop();
                Gdx.app.postRunnable(() -> game.setScreen(new ScreenGameOver(game, getFinalScore(), "Te moristes :(", true)));
            }
        }
    }

    @Override
    public void render(float delta) { 
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Puntuación: " + tarro.getPuntos(), 10, 1080 - 10);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 1920 - 130, 1080 - 10);
        font.draw(batch, "Tiempo: " + (int) tiempoRestante, 960 - 50, 1080 - 10);
        batch.end();
        update(delta);
        draw();
    }

    @Override
    public void draw() {
        batch.begin();
        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(0, 0, 1920, 30);
        shapeRenderer.end();
    }

    @Override
    public boolean isLevelOver() {
        return isGameOver;
    }

    @Override
    public float getFinalScore() {
        return tarro.getPuntos();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void hide() {}

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void dispose() {
        tarro.destruir();
        lluvia.destruir();
        batch.dispose();
        font.dispose();
        rainMusic.dispose();
        shapeRenderer.dispose();
    }
    @Override
    public float getTiempoRestante() {
        return tiempoRestante;
    }
}