package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaBuena extends Gota {
    private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

    public GotaBuena(float hitboxSize) {
        super(hitboxSize);
    }
    
    @Override
	public Texture definirTextura() {
	    Texture textura = new Texture(Gdx.files.internal("drop.png"));
	    textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);  // Aplicar filtrado lineal
	    return textura;
	}
    
    @Override
    public float setVelocidad() {
        return 250;  // Velocidad fija para GotaBuena
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(5);
        dropSound.play();
    }
}