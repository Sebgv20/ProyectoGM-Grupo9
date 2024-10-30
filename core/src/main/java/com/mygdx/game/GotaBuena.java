package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaBuena extends Gota {
    private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("dropSound.wav"));

    public GotaBuena(float hitboxSize) {
        super(hitboxSize);
    }
    
    @Override
	public Texture definirTextura() {
	    Texture textura = new Texture(Gdx.files.internal("drop.png"),true);
	    textura.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
	    return textura;
	}
    
    @Override
    public float setVelocidad() {
        return 300;  // Velocidad fija para GotaBuena
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(5);
        dropSound.play();
    }
}