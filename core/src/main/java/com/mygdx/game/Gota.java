package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class Gota {
    private Texture textura;
    private float hitboxSize;
    private float velocidad;
    
    public Gota(float hitboxSize) {
        this.hitboxSize = hitboxSize;
        this.textura = definirTextura(); // Cada subclase define su textura
        this.velocidad = setVelocidad();  // Cada subclase establece su velocidad
    }
    
    // Método abstracto para que cada subclase proporcione su textura
    public abstract Texture definirTextura();
    
    public abstract float setVelocidad();

    //Velocidad de las gotas
    public float getVelocidad() {
        return velocidad;
    }
    
    public abstract void aplicarEfecto(Tarro tarro);

    public void dibujar(SpriteBatch batch, Rectangle posicion) {
        batch.draw(textura, posicion.x, posicion.y, hitboxSize, hitboxSize);
    }
}