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

public class GameScreen implements Screen {
    private GameLluviaMenu game;  // Referencia al objeto principal que maneja el ciclo de vida del juego
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private ShapeRenderer shapeRenderer; // Añadido para el suelo
   
    private Texture bucketLeftImage; // Imagen del player cuando camina a la izquierda
    private Texture bucketRightImage; // Imagen del player cuando camina a la derecha
    private Tarro tarro; // El player
    private Lluvia lluvia;
    
    private Music rainMusic;

    // Variables para el temporizador
    private float tiempoRestante; // Tiempo restante en segundos
    private boolean temporizadorActivo; // Estado del temporizador
    private boolean isGameOver;

    // Constructor que acepta una referencia al controlador de pantallas (MainGame)
    public GameScreen(GameLluviaMenu game) {
        this.game = game;
        this.isGameOver = false; // Agregar esto
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.getData().setScale(2.0f);
		 
        // Temporizador del juego
        tiempoRestante = 41; // Por ejemplo, 61 segundos
        temporizadorActivo = true;

        // Carga el tarro (player) y sus texturas
        bucketLeftImage = new Texture(Gdx.files.internal("bucketIzq.png"));
        bucketRightImage = new Texture(Gdx.files.internal("bucketDer.png"));
        
        tarro = new Tarro(bucketLeftImage, bucketRightImage);
        
        // Música de fondo
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("GameMusic.mp3"));
        lluvia = new Lluvia(rainMusic); // Genera la lluvia
		
        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        batch = new SpriteBatch();
        
        shapeRenderer = new ShapeRenderer(); // Inicializa el ShapeRenderer
		
        // Crea objetos
        tarro.crear();
        lluvia.crear();
    }

    @Override
    public void render(float delta) { 
    	ScreenUtils.clear(0, 0, 0, 1); // Limpia la pantalla

        // Si el juego ya terminó, deja de renderizar
        if (isGameOver) {
            return;
        }

        // Actualiza las matrices de la cámara
        camera.update();

        // Establece la proyección de la cámara en el batch
        batch.setProjectionMatrix(camera.combined);

        // Inicia el batch
        batch.begin();
        
        // Dibuja textos de puntuación, vidas, y temporizador
        font.draw(batch, "Puntuación: " + tarro.getPuntos(), 10, 1080-10);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 1920-130, 1080-10);
        font.draw(batch, "Tiempo: " + (int) tiempoRestante, 960-50, 1080-10); // Muestra el tiempo restante como un entero
        
        // Actualiza el temporizador mientras esté activo
        if (temporizadorActivo) {
            tiempoRestante -= delta; // Resta el tiempo transcurrido (delta)

            // Si el tiempo llega a 0, el juego termina
            if (tiempoRestante <= 0) {
                tiempoRestante = 0; // Para que tiempo no pueda ser negativo
                temporizadorActivo = false; // Detiene el temporizador

                // Termina el juego
                isGameOver = true;
                rainMusic.stop(); // Termina la música

                // Posterga el cambio de pantalla para el siguiente ciclo
                Gdx.app.postRunnable(() -> {
                    game.setScreen(new GameOverScreen(game, getPuntuacionFinal(), "¡Se acabó el tiempo!")); // Pasar la razón
                });
            }
        }

        // Solo se actualiza el juego si no estamos en Game Over
        if (!isGameOver) {
            // Si el tarro no está herido, actualizar su movimiento y el de la lluvia
            if (!tarro.estaHerido()) {
                tarro.actualizarMovimiento();       
                lluvia.actualizarMovimiento(tarro, game); 
            }

            // Verifica si el jugador ha perdido todas las vidas
            if (tarro.getVidas() <= 0) {
                isGameOver = true;  // Activamos la bandera de Game Over
                rainMusic.stop();
                
                // Posterga el cambio de pantalla para el siguiente ciclo
                Gdx.app.postRunnable(() -> {
                    game.setScreen(new GameOverScreen(game, getPuntuacionFinal(), "Te moristes :(")); // Pasar la razón
                });
            }
        }

        // Dibujar el tarro y las gotas de lluvia
        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);

        batch.end(); // Terminar el batch

        // Dibuja el suelo
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1); // Color blanco
        shapeRenderer.rect(0, 0, 1920, 30); // Dibuja el suelo (x, y, ancho, alto)
        shapeRenderer.end(); // Termina el dibujo del ShapeRenderer
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
        // Lógica para cuando el juego se pausa (se puede dejar vacío si no se usa)
    }
    
    public float getTiempoRestante() {
        return tiempoRestante;
    }

    @Override
    public void dispose() {
        tarro.destruir();  
        lluvia.destruir();  
        batch.dispose();
        font.dispose();  
        rainMusic.dispose();
        shapeRenderer.dispose(); // Libera el ShapeRenderer
    }
}
