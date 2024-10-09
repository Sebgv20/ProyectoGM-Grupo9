package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class Gota {
    private Texture textura;
    private float hitboxSize;
    
    public Gota(Texture textura, float hitboxSize) {
        this.textura = textura;
        this.hitboxSize = hitboxSize;
    }
    
    public abstract void aplicarEfecto(Tarro tarro);

    public void dibujar(SpriteBatch batch, Rectangle posicion) {
        batch.draw(textura, posicion.x, posicion.y, hitboxSize, hitboxSize);
    }
}
