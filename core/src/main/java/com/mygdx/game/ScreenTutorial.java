package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;

public class ScreenTutorial implements Screen {
    private final GameLluviaMenu game;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private OrthographicCamera camera;
    
    private Texture buttonBack;
    private Texture player;
    private Texture drop;
    private Texture dropBad;
    private Texture dropHeal;
    private Texture dropSuper;

    public ScreenTutorial(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);
        camera.setToOrtho(false, 1920, 1080);
    }
    
    @Override
    public void show() {
        // Cargar las imágenes de los botones con mipmaps habilitados
        buttonBack = new Texture(Gdx.files.internal("ButtonBack.png"), true); // Habilitar mipmaps
        player = new Texture(Gdx.files.internal("bucketDer.png"), true);
        drop = new Texture(Gdx.files.internal("drop.png"), true);
        dropBad = new Texture(Gdx.files.internal("dropBad.png"), true);
        dropHeal = new Texture(Gdx.files.internal("dropHeal.png"), true);
        dropSuper = new Texture(Gdx.files.internal("dropSuper.png"), true);
        
        // Aplicar filtrado trilineal
        buttonBack.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        player.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        drop.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        dropBad.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        dropHeal.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        dropSuper.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
    	ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        
        // Dibujar textos
        font.getData().setScale(2,2);
        
        batch.draw(buttonBack, 5, 1080-85, 200,80);
        
        // Imágenes de los elementos del juego y sus instrucciones
        batch.draw(player, 30, 1080-250, 183,112);
        font.draw(batch, "Este eres tu, dispones de 3 vidas y debes recoger los distintos orbes en pantalla\n"
        		+ "para acumular puntos. ¡Cuidado con lo que agarras!", 250, 1080-170);
        
        batch.draw(drop, 70, 1080-400, 100,100);
        font.draw(batch, "El orbe más básico y tu principal alternativa para aumentar tu puntaje.", 250, 1080-100-230);
        
        batch.draw(dropBad, 70, 1080-550, 100,100);
        font.draw(batch, "Tu mayor enemigo, cuidado con tomar una de estas,\n"
        		+ "¡Perderás una vida y parte de tus puntos!", 250, 1080-100-380);
        
        batch.draw(dropHeal, 70, 1080-700, 100,100);
        font.draw(batch, "Un orbe que no solo otorga puntos, si no que recupera una vida perdida.\n"
        		+ "No se pueden tener más de 3 vidas.", 250, 1080-100-530);
        
        batch.draw(dropSuper, 70, 1080-850, 100,100);
        font.draw(batch, "Un orbe con gran poder que otorga una cantidad considerable de puntos.\n"
        		+ "¡Recoge cuantas puedas!", 250, 1080-100-680);
        
        font.draw(batch, "Tu propósito será atrapar orbes para poder hacerte con el mayor puntaje posible.\n"
        		+ "Dispondrás de una cantidad de tiempo definida y solo 3 vidas para jugar. ¡Éxito!", 30, 1080-100-830);
        
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                game.setScreen(new ScreenMainMenu(game));  // Volver al menú principal
                dispose();  // Liberar recursos
            };
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
    	buttonBack.dispose();
        player.dispose();
        drop.dispose();
        dropBad.dispose();
        dropHeal.dispose();
        dropSuper.dispose();
    }
}