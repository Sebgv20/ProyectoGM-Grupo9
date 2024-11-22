package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

// Tarro = Jugador
public class Tarro {
	   private Rectangle bucket;
	   private Texture bucketImage;
	   private Texture bucketLeftImage;
	   private Texture bucketRightImage;
	   private int vidas = 3;
	   private int puntos = 0;
	   private float velx = 650;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   private boolean invulnerable = false;
	   private boolean velozBuff = false;
	   private List<Efecto> efectosActivos = new ArrayList<>();   
	   
	   public Tarro(Texture texLeft, Texture texRight) {
	       bucketLeftImage = texLeft;
	       bucketRightImage = texRight;
	       bucketImage = bucketRightImage; // Inicia mirando a la derecha
	   }
	   
	   public void actualizarMovimiento() { 
		   float delta = Gdx.graphics.getDeltaTime();
		    for (Efecto efecto : efectosActivos) {
		        efecto.actualizar(this, delta);
		    }
	       // Movimiento desde el teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
	           bucket.x -= velx * Gdx.graphics.getDeltaTime();
	           bucketImage = bucketLeftImage; // Cambia a la textura de la izquierda
	       }
	       
	       if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
	           bucket.x += velx * Gdx.graphics.getDeltaTime();
	           bucketImage = bucketRightImage; // Cambia a la textura de la derecha
	       }
	       
	       // Asegura de que no se salga de los bordes izquierdo y derecho
	       if(bucket.x < 0) bucket.x = 0;
	       //if(bucket.x > 800 - 64) bucket.x = 800 - 64;
	       if(bucket.x > 1920 - 138) bucket.x = 1920 - 138;
	   }
	   
	   public void actualizarEfectos(float deltaTime) {
	        Iterator<Efecto> iterador = efectosActivos.iterator();
	        while (iterador.hasNext()) {
	            Efecto efecto = iterador.next();
	            efecto.actualizar(this, deltaTime); // Actualiza el efecto

	            if (efecto.haTerminado()) {
	                iterador.remove(); // Remueve efectos expirados
	            }
	        }
	    }
	   
	   public void agregarEfecto(Efecto efecto) {
		    // Eliminar efectos expirados del mismo tipo antes de agregarlo nuevamente
		    Iterator<Efecto> iterador = efectosActivos.iterator();
		    while (iterador.hasNext()) {
		        Efecto e = iterador.next();
		        if (e.getClass().equals(efecto.getClass()) && e.haTerminado()) {
		            iterador.remove(); // Elimina el efecto expirado
		        }
		    }

		    efectosActivos.add(efecto); // Agrega el nuevo efecto
		    efecto.aplicar(this); // Aplica el efecto inmediatamente
		}
	   
	   public void dibujar(SpriteBatch batch) {
		    // Si el jugador está invulnerable, cambia su color a azul
		   if (isInvulnerable() && isVeloz()) batch.setColor(0, 1, 0, 1); // Establece el color azul (RGB: 0, 0, 1)
		   else if (isInvulnerable()) batch.setColor(0, 0, 1, 1); // Establece el color azul (RGB: 0, 0, 1)
		   else if (isVeloz()) batch.setColor(1, 1, 0, 1); // Establece el color amarillo
		    else batch.setColor(1, 1, 1, 1); // Restaura el color original (blanco)

		    // Dibuja la textura del tarro
		    if (!herido) {
		        batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
		    } else {
		        batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5), bucket.width, bucket.height);
		        tiempoHerido--;
		        if (tiempoHerido <= 0) herido = false;
		    }

		    // Restaura el color a blanco para evitar afectar otros elementos que se dibujen después
		    batch.setColor(1, 1, 1, 1);
		}
	   
	   public void crear() {
	       bucket = new Rectangle();
	       //bucket.x = 800 / 2 - 64 / 2; Antiguos parametros
	       //bucket.y = 20;  
	       bucket.x = 1920 / 2 - 128 / 2;
	       bucket.y = 30;
	       
	       //bucket.width = 64;
	       //bucket.height = 64;
	       bucket.width = 138;
	       bucket.height = 85;
	   }
	   
	   public Rectangle getArea() {
	       return bucket;
	   }
	   
	   //########## ################################ ##########
	   //########## Efectos y características ingame ##########
	   //########## ################################ ##########
	   
	   //Salud del jugador
	   public int getVidas() {
	       return vidas;
	   }
	   
	   public void curar() {
		   vidas++; 
	   }
	   
	   public void dañar() {
		    if (invulnerable) return; // Ignora el daño si es invulnerable
		    if (velozBuff) setVelozBuff(false); // Ignora el daño si es invulnerable

		    vidas--;
		    herido = true;
		    tiempoHerido = tiempoHeridoMax;
		    velx = 650; 
		}
	   
	   public void dañarPlus() {
		   if (invulnerable){
			   if(vidas > 1) vidas--;// Ignora el daño si es invulnerable
			   setInvulnerable(false);
		   } else {
			   vidas -= vidas;
			   herido = true;
			   tiempoHerido = tiempoHeridoMax;
		   }
		   if (velozBuff) setVelozBuff(false);
		   velx = 650;
	   }
	   
	   public boolean estaHerido() {
	       return herido;
	   }
	   public int getPuntos() {
	       return puntos;
	   }
	   
	   public void sumarPuntos(int pp) {
	       puntos += pp;
	   }
	   
	   public void restarPuntos(int pp) {
	       puntos -= pp;
	   }
	   
	   //Velocidad
	   public float getVelocidad() {
	       return velx;
	   }
	   public void setVelocidad(float velocidad) {
	       velx = velocidad;
	   }
	   
	   //Vulnerabilidad y efectos
	   public boolean isInvulnerable() {
		    return invulnerable;
		}

		public void setInvulnerable(boolean invulnerable) {
		    this.invulnerable = invulnerable;
		}
		
		public boolean isVeloz() {
		    return velozBuff;
		}

		public void setVelozBuff(boolean velozBuff) {
		    this.velozBuff = velozBuff;
		}

		public void eliminarEfecto(Efecto efecto) {
		    efectosActivos.remove(efecto);
		}
		
		
	   //Reset
	   public void destruir() {
	       bucketLeftImage.dispose();
	       bucketRightImage.dispose();
	   }
   
	   // Restablece los valores del jugador
	   public void reiniciar() {
	        this.puntos = 0; // Restablecer puntos
	        this.vidas = 3;  // Restablecer vidas
	    }
	}
