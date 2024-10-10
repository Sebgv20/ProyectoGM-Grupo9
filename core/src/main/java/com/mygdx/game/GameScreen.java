package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private GameLluviaMenu game;  // Referencia al objeto principal que maneja el ciclo de vida del juego
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
   
    private Texture bucketLeftImage;
    private Texture bucketRightImage;
    private Tarro tarro;
    private Lluvia lluvia;

    // Variables para el temporizador
    private float tiempoRestante; // Tiempo restante en segundos
    private boolean temporizadorActivo; // Estado del temporizador

    // Constructor que acepta una referencia al controlador de pantallas (MainGame)
    public GameScreen(GameLluviaMenu game) {
        this.game = game;
    }

    @Override
    public void show() {
        font = new BitmapFont();
		 
        // Inicializar el temporizador
        tiempoRestante = 20; // Por ejemplo, 60 segundos
        temporizadorActivo = true;

        // Cargar texturas y sonidos
        bucketLeftImage = new Texture(Gdx.files.internal("bucketIzq2.png"));
        bucketRightImage = new Texture(Gdx.files.internal("bucketDer2.png"));
        tarro = new Tarro(bucketLeftImage, bucketRightImage);
        
        // Texturas de gotas y música
        Texture gotaBuena = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        Texture gotaSuper = new Texture(Gdx.files.internal("dropSuper.png"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("GameMusic.mp3"));
        lluvia = new Lluvia(gotaBuena, gotaMala, gotaSuper, rainMusic);
		
        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
		
        // Crear objetos
        tarro.crear();
        lluvia.crear();
    }

    @Override
    public void render(float delta) { 
        // Limpiar la pantalla con color azul oscuro
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualizar matrices de la cámara
        camera.update();
        
        // Establecer la proyección de la cámara en el batch
        batch.setProjectionMatrix(camera.combined);
        
        // Iniciar el batch
        batch.begin();
        
        // Dibujar textos
        font.draw(batch, "Puntuación: " + tarro.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 720, 475);
        
        // Dibujar el temporizador
        font.draw(batch, "Tiempo: " + (int) tiempoRestante, 350, 475); // Muestra el tiempo restante como un entero
        
        // Si el temporizador está activo, actualizarlo
        if (temporizadorActivo) {
            tiempoRestante -= delta; // Restar el tiempo transcurrido (delta)
            if (tiempoRestante <= 0) {
                tiempoRestante = 0; // Asegúrate de que no sea negativo
                temporizadorActivo = false; // Detener el temporizador
                game.setScreen(new GameOverScreen(game, getPuntuacionFinal())); // Cambiar a Game Over si se acaba el tiempo
            }
        }
        
        // Si el tarro no está herido, actualizar su movimiento y el de la lluvia
        if (!tarro.estaHerido()) {
            tarro.actualizarMovimiento();       
            lluvia.actualizarMovimiento(tarro, game); 
        }
        
        // Verificar Game Over después de actualizar el movimiento
        if (tarro.getVidas() <= 0) {
        	game.setScreen(new GameOverScreen(game, getPuntuacionFinal()));  
        }
        
        // Dibujar el tarro y las gotas de lluvia
        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
        
        // Terminar el batch
        batch.end();		
    }
    
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }
    
    public float getPuntuacionFinal() {
    	return tarro.getPuntos();
    }
	
    @Override
    public void resume() {
    }

    @Override
    public void pause() {
        // Lógica para cuando el juego se pausa (puedes dejarlo vacío si no necesitas hacer nada)
    }

    @Override
    public void dispose() {
        tarro.destruir();  
        lluvia.destruir();  
        batch.dispose();
        font.dispose();  
    }
}

