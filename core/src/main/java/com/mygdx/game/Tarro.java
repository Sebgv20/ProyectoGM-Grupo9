package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Tarro {
	   private Rectangle bucket;
	   private Texture bucketImage;
	   private Texture bucketLeftImage;
	   private Texture bucketRightImage;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 650;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   
	   public Tarro(Texture texLeft, Texture texRight) {
	       bucketLeftImage = texLeft;
	       bucketRightImage = texRight;
	       bucketImage = bucketRightImage; // Inicia mirando a la derecha
	   }
	   
	   public int getVidas() {
	       return vidas;
	   }

	   public int getPuntos() {
	       return puntos;
	   }
	   
	   public Rectangle getArea() {
	       return bucket;
	   }
	   
	   public void sumarPuntos(int pp) {
	       puntos += pp;
	   }
	   
	   public void restarPuntos(int pp) {
	       puntos -= pp;
	   }
	   public void setVelocidad(int velocidad) {
	       velx = velocidad;
	   }

	   public void crear() {
	       bucket = new Rectangle();
	       //bucket.x = 800 / 2 - 64 / 2;
	       //bucket.y = 20;  
	       bucket.x = 1920 / 2 - 128 / 2;
	       bucket.y = 30;
	       
	       //bucket.width = 64;
	       //bucket.height = 64;
	       bucket.width = 138;
	       bucket.height = 85;
	   }

	   public void dañar() {
	      vidas--;
	      herido = true;
	      tiempoHerido = tiempoHeridoMax;
	      velx = 750;
	   }
	   
	   public void curar() {
		   vidas++; 
	   }

	   public void dibujar(SpriteBatch batch) {
		    // Escalar la imagen para que coincida con las dimensiones del "bucket"
		    if (!herido) {
		        batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
		    } else {
		        batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5), bucket.width, bucket.height);
		        tiempoHerido--;
		        if (tiempoHerido <= 0) herido = false;
		    }
		}
	   
	   public void actualizarMovimiento() { 
	       // Movimiento desde el teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
	           bucket.x -= velx * Gdx.graphics.getDeltaTime();
	           bucketImage = bucketLeftImage; // Cambia a la textura de la izquierda
	       }
	       
	       if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
	           bucket.x += velx * Gdx.graphics.getDeltaTime();
	           bucketImage = bucketRightImage; // Cambia a la textura de la derecha
	       }
	       
	       // Asegúrate de que no se salga de los bordes izquierdo y derecho
	       if(bucket.x < 0) bucket.x = 0;
	       //if(bucket.x > 800 - 64) bucket.x = 800 - 64;
	       if(bucket.x > 1920 - 138) bucket.x = 1920 - 138;
	   }
	   
	   public void destruir() {
	       bucketLeftImage.dispose();
	       bucketRightImage.dispose();
	   }

	   public boolean estaHerido() {
	       return herido;
	   }
	   
	   public void reiniciar() {
	        this.puntos = 0; // Restablecer puntos
	        this.vidas = 3;  // Restablecer vidas
	        // Restablecer otras propiedades si es necesario
	    }
	}

