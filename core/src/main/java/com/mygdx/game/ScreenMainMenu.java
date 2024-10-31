package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;

// Pantalla principal del juego
public class ScreenMainMenu implements Screen {
    final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    private Texture buttonPlay;
    private Texture buttonTutorial;
    private Texture buttonExit;
    private Texture logoBorder;
    private Texture logoName;

    private float rotationAngle = 0; // Ángulo de rotación inicial
    private float rotationSpeed = 30; // Velocidad de rotación en grados por segundo

    public ScreenMainMenu(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
    }

    @Override
    public void show() {
        buttonPlay = new Texture(Gdx.files.internal("ButtonPlay.png"));
        buttonTutorial = new Texture(Gdx.files.internal("ButtonTutorial.png"));
        buttonExit = new Texture(Gdx.files.internal("ButtonExit.png"));
        logoBorder = new Texture(Gdx.files.internal("LogoBorder.png"));
        logoName = new Texture(Gdx.files.internal("LogoName.png"));
        
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        font.getData().setScale(2,2);
        font.draw(batch, "Haz click en la tecla mostrada para cada opción", 960-300, 1080 - 20);

        batch.draw(buttonPlay, 200, camera.viewportHeight / 2 + 120, 400, 160);
        batch.draw(buttonTutorial, 200, camera.viewportHeight / 2 - 80, 400, 160);
        batch.draw(buttonExit, 200, camera.viewportHeight / 2 - 280, 400, 160);

        batch.draw(logoName, 1000, camera.viewportHeight / 2 - 300, 600, 600);

        // Actualizar ángulo de rotación
        rotationAngle += rotationSpeed * delta;

        // Dibujar logoBorder con rotación
        batch.draw(logoBorder, 1000, camera.viewportHeight / 2 - 300, 300, 300, 600, 600, 1, 1, rotationAngle, 0, 0, 3145, 3145, false, false);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.setScreen(new ScreenLevelSelector(game));
            dispose();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            game.setScreen(new ScreenTutorial(game));
            dispose();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            Gdx.app.exit();
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        buttonPlay.dispose();
        buttonTutorial.dispose();
        buttonExit.dispose();
        logoBorder.dispose();
        logoName.dispose();
    }
}
