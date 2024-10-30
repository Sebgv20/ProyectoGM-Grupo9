package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaZap extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("dropZapSound.mp3"));

    public GotaZap(float hitboxSize) {
        super(hitboxSize);
    }

    @Override
	public Texture definirTextura() {
	    Texture textura = new Texture(Gdx.files.internal("dropZap.png"),true);
	    textura.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
	    return textura;
	}
    
    public float setVelocidad() {
        // Velocidad fija para GotaBuena
        return 650;
    }
    
    @Override
    public void aplicarEfecto(Tarro tarro) {
    	tarro.setVelocidad(1250);
    	tarro.sumarPuntos(5);
        dropSound.play();
    }
}