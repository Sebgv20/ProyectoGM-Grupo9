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
    private Texture buttonPagSiguiente;
    private Texture buttonPagAnterior;
    
    private Texture player;
    private Texture teclaA;
    private Texture teclaD;
    private Texture flechaDer;
    private Texture flechaIzq;
    
    private Texture drop;
    private Texture dropBad;
    private Texture dropInstaKill;
    private Texture dropHeal;
    private Texture dropSuper;
    private Texture dropZap;
    
    private int currentPage = 1;  // Variable para controlar la página actual

    public ScreenTutorial(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
    }

    @Override
    public void show() {
        // Usamos TextureManager para cargar las texturas
        TextureManager textureManager = TextureManager.getInstance();
        
        buttonBack = textureManager.getTexture("ButtonBack.png");
        buttonPagSiguiente = textureManager.getTexture("ButtonDNext.png");
        buttonPagAnterior = textureManager.getTexture("ButtonABack.png");
        
        player = textureManager.getTexture("bucketDer.png");
        teclaA = textureManager.getTexture("TeclaA.png");
        flechaIzq = textureManager.getTexture("TeclaFlechaIzq.png");
        teclaD = textureManager.getTexture("TeclaD.png");
        flechaDer = textureManager.getTexture("TeclaFlechaDer.png");
        
        drop = textureManager.getTexture("drop.png");
        dropBad = textureManager.getTexture("dropBad.png");
        dropInstaKill = textureManager.getTexture("dropInstaKill.png");
        dropHeal = textureManager.getTexture("dropHeal.png");
        dropSuper = textureManager.getTexture("dropSuper.png");
        dropZap = textureManager.getTexture("dropZap.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.getData().setScale(2, 2);
        batch.draw(buttonBack, 5, 1080 - 85, 200, 80);

        // Dibuja la página 1 del tutorial
        if (currentPage == 1) {
            batch.draw(buttonPagSiguiente, 1920-105-10, 1080/2 - 43, 105, 71);
            font.draw(batch, "1 / 2", 1920-80, 30);
            
            batch.draw(player, 1920/2 - 183, 1080 - 400, 366, 224);    
            font.draw(batch, "Este eres tu, al inicio de cada partida contarás con 3 vidas.\n"
                    + "Solo podrás moverte horizontalmente para cumplir tu propósito.", 550, 1080 - 450);
            
            font.draw(batch, "Para moverte a la izquierda", 450, 1080 - 580);
            batch.draw(teclaA, 470, 1080-480-250, 100, 100);
            font.draw(batch, "///", 620, 1080-480-190);
            batch.draw(flechaIzq, 690, 1080-480-250, 100, 100);                        
            
            font.draw(batch, "Para moverte a la derecha", 1130, 1080 - 580);
            batch.draw(teclaD, 1150, 1080-480-250, 100, 100);
            font.draw(batch, "///", 1300, 1080-480-190);
            batch.draw(flechaDer, 1370, 1080-480-250, 100, 100);  
            
            font.draw(batch, "Tu propósito será atrapar distintos tipos de orbes, cada uno con su respectivo efecto sobre el jugador. "
            		+ "Ciertos orbes otorgarán puntuación al \njugador, y tu deber será acumular la mayor cantidad de puntos en un tiempo "
            		+ "definido. Si pierdes todas tus vidas la partida terminará \nabruptamente, pero si sobrevives el tiempo que dure "
            		+ "el nivel (independiente de tu puntaje) completarás el nivel. ¡Éxito!", 50, 1080 - 850);
        } 
        
        // Dibuja la página 2 del tutorial
        else if (currentPage == 2) {
            batch.draw(buttonPagAnterior, 10, 1080/2 - 43, 105, 71);
            font.draw(batch, "2 / 2", 1920-80, 30);
            
            //Primera fila de elementos
            batch.draw(drop, 130, 1080 - 250, 100, 100);
            font.draw(batch, "El orbe más básico y tu principal fuente \npara aumentar tu puntuación.", 240, 1080-250+80);
            
            batch.draw(dropSuper, 1050, 1080 - 250, 100, 100);
            font.draw(batch, "Un orbe con gran poder que otorga una \ncantidad considerable de puntos.\n¡Recoge cuantas puedas!", 1160, 1080-250+95); 
            
            //Segunda fila de elementos
            batch.draw(dropBad, 130, 1080-450, 100, 100);
            font.draw(batch, "Un orbe peligroso, ten cuidado con tomar \nuna de estas, ¡perderás una vida y puntos!", 240, 1080-450+80);
            
            batch.draw(dropInstaKill, 1050, 1080-450, 100, 100);
            font.draw(batch, "Un orbe letal, si no estás protegido estás fuera.", 1160, 1080-450+80);
            
            //Tercera fila de elementos
            batch.draw(dropHeal, 130, 1080-650, 100, 100);
            font.draw(batch, "Un orbe que otorga pocos puntos, pero \nque recupera una de las vidas perdidas.\n"
            		+ "No se pueden tener más de 3 vidas.", 240, 1080-650+90);
            
            //Cuarta fila de elementos
            batch.draw(dropZap, 130, 1080-850, 100, 100);
            font.draw(batch, "Un orbe que otorga pocos puntos, pero \n"
            		+ "otorga un buff de velocidad de movimiento \nal jugador. Se puede acumular pero si \nrecibes daño perderás todo el bono.", 240, 1080-850+105);
        }
        batch.end();

        // Cambio de páginas
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (currentPage == 1) {
                currentPage = 2;  // Cambia a la segunda página
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (currentPage == 2) {
                currentPage = 1;  // Regresa a la primera página
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new ScreenMainMenu(game));  // Volver al menú principal
            dispose();
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
    }
}
