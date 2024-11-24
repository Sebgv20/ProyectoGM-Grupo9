package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

// Clase abstracta para la creación de las distintas gotas de lluvia
public abstract class Gota {
    private Texture textura;
    private float hitboxSize;
    private float velocidad;
    
    public Gota(float hitboxSize) {
        this.hitboxSize = hitboxSize;
        this.textura = TextureManager.getInstance().getTexture(definirRutaTextura()); // Cada subclase tendrá su propia textura
        this.velocidad = setVelocidad();  // Cada subclase establece su propia velocidad
    }
    
    // Método abstracto para que cada subclase proporcione su textura
    protected abstract String definirRutaTextura();  
    public abstract float setVelocidad();
    public abstract void aplicarEfecto(Tarro tarro);

    // Método común entre todas las gotas
    public float getVelocidad() {
        return velocidad;
    }
    
    // Template Method
    // Template Method sin SpriteBatch
    public final void procesarGota(Rectangle posicion, Tarro tarro) {
        if (posicion.overlaps(tarro.getArea())) { // Lógica genérica de colisión
            aplicarEfecto(tarro); // Personalizable
        }
    }
       
    // Dibuja la textura, la posición en x e y, y el tamaño será igual al de su hitbox
    public void dibujar(SpriteBatch batch, Rectangle posicion) {
        batch.draw(textura, posicion.x, posicion.y, hitboxSize, hitboxSize);
    }
}