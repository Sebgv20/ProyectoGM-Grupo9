package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;

//Pantalla para seleccionar el nivel que se quiere jugar
public class ScreenLevelSelector implements Screen {
    private final GameLluviaMenu game;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private OrthographicCamera camera;
    
    private Texture buttonBack;
    private Texture lvl1;
    private Texture lvl2;
    private Texture lvl3;

    public ScreenLevelSelector(final GameLluviaMenu game) {
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
        lvl1 = new Texture(Gdx.files.internal("ButtonLvl1.png"), true);
        lvl2 = new Texture(Gdx.files.internal("ButtonLvl2.png"), true);
        lvl3 = new Texture(Gdx.files.internal("ButtonLvl3.png"), true);
        
        // Aplicar filtrado trilineal
        buttonBack.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
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
        font.draw(batch, "Haz click en la tecla mostrada para cada opción", 960-300, 1080 - 20);
        
        // Imágenes de los elementos del juego y sus instrucciones
        batch.draw(lvl1, 155						, 1080-400, 400, 160);
        
        batch.draw(lvl2, 155+400	+200			, 1080-400-160-20, 400, 160);
        
        batch.draw(lvl3, 155+400+400+200	+200	, 1080-400-160-20-160-20, 400, 160);
        
        batch.end();

        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new ScreenMainMenu(game));  // Volver al menú principal
            dispose();  // Liberar recursos
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.getMenuMusic().stop();

            // Crear la música para la lluvia
            Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("GameMusic.mp3"));

            // Crear la pantalla usando el Builder
            GameScreenLevel1Builder builder = new GameScreenLevel1Builder(game);
            GameScreenLevel1 gameScreen = builder
                .setRainMusic(rainMusic)
                .setBucketTextures(new Texture(Gdx.files.internal("bucketIzq.png")),
                                   new Texture(Gdx.files.internal("bucketDer.png")))
                .setLluvia(new LluviaLevel1(rainMusic))
                .setTiempoRestante(46)  // Asumiendo que 46 es el tiempo restante para el nivel 1
                .build();

            game.setScreen(gameScreen);  // Cambiar a la pantalla del nivel 1
            dispose();  // Liberar recursos
        }
            
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            game.getMenuMusic().stop();

            // Crear la música para la lluvia en nivel 2
         // Crear la música para la lluvia
            Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("GameMusic.mp3"));

            // Crear la pantalla usando el Builder
            GameScreenLevel2Builder builder = new GameScreenLevel2Builder(game);
            GameScreenLevel2 gameScreen = builder
                .setRainMusic(rainMusic)
                .setBucketTextures(new Texture(Gdx.files.internal("bucketIzq.png")),
                                   new Texture(Gdx.files.internal("bucketDer.png")))
                .setLluvia(new LluviaLevel2(rainMusic))
                .setTiempoRestante(50)  
                .build();

            game.setScreen(gameScreen);  
            dispose();  // Liberar recursos
        }
            
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
        	game.getMenuMusic().stop();
            Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("GameMusic.mp3"));

            // Crear la pantalla usando el Builder
            GameScreenLevel3Builder builder = new GameScreenLevel3Builder(game);
            GameScreenLevel3 gameScreen = builder
                .setRainMusic(rainMusic)
                .setBucketTextures(new Texture(Gdx.files.internal("bucketIzq.png")),
                                   new Texture(Gdx.files.internal("bucketDer.png")))
                .setLluvia(new LluviaLevel3(rainMusic))
                .setTiempoRestante(60)  
                .build();

            game.setScreen(gameScreen);  
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
        lvl1.dispose();
        lvl2.dispose();
        lvl3.dispose();
    }
}