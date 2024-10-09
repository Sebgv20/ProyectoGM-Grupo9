package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class EjemploLluvia extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
   
    private Texture bucketLeftImage;
    private Texture bucketRightImage;
    private Tarro tarro;
    private Lluvia lluvia;

    @Override
    public void create () {
        font = new BitmapFont(); // Usa la fuente Arial predeterminada de libGDX
		 
        // Cargar las texturas para el tarro y el sonido
        bucketLeftImage = new Texture(Gdx.files.internal("bucketIzq2.png"));
        bucketRightImage = new Texture(Gdx.files.internal("bucketDer2.png"));
        tarro = new Tarro(bucketLeftImage, bucketRightImage);
        
        // Cargar las texturas de las gotas y el sonido/música de fondo
        Texture gotaBuena = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        lluvia = new Lluvia(gotaBuena, gotaMala, rainMusic);
		
        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
		
        // Creación de objetos
        tarro.crear();  // Crear el tarro
        lluvia.crear(); // Crear las gotas de lluvia
    }

    @Override
    public void render () {
        // Limpiar la pantalla con color azul oscuro
        ScreenUtils.clear(0, 0, 0.2f, 1);
        
        // Actualizar matrices de la cámara
        camera.update();
        
        // Establecer la proyección de la cámara en el batch
        batch.setProjectionMatrix(camera.combined);
        
        // Iniciar el batch
        batch.begin();
        
        // Dibujar textos
        font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 720, 475);
        
        // Si el tarro no está herido, actualizar su movimiento y el de la lluvia
        if (!tarro.estaHerido()) {
            tarro.actualizarMovimiento();       // Movimiento del tarro con teclado
            lluvia.actualizarMovimiento(tarro); // Caída de la lluvia
        }
        
        // Dibujar el tarro y las gotas de lluvia
        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);
        
        // Terminar el batch
        batch.end();		
    }
    
    @Override
    public void dispose () {
        tarro.destruir();
        lluvia.destruir();
        batch.dispose();
        font.dispose();
    }
}

