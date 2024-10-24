package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaSuper extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("superDrop.wav"));

    public GotaSuper(float hitboxSize) {
        super(hitboxSize);
    }

    @Override
	public Texture definirTextura() {
	    Texture textura = new Texture(Gdx.files.internal("dropSuper.png"),true);
	    textura.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
	    return textura;
	}
    
    public float setVelocidad() {
        // Velocidad fija para GotaBuena
        return 550;
    }
    
    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(30);
        dropSound.play();
    }
}
