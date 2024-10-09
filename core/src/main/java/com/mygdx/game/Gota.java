package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class Gota {
    private Texture textura;
    private float hitboxSize;
    private float velocidad;
    
    public Gota(Texture textura, float hitboxSize) {
        this.textura = textura;
        this.hitboxSize = hitboxSize;
        this.velocidad = setVelocidad();  // Cada subclase establece su velocidad
    }
    
    public abstract float setVelocidad();

    public float getVelocidad() {
        return velocidad;
    }
    
    public abstract void aplicarEfecto(Tarro tarro);

    public void dibujar(SpriteBatch batch, Rectangle posicion) {
        batch.draw(textura, posicion.x, posicion.y, hitboxSize, hitboxSize);
    }
}