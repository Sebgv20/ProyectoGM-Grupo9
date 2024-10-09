package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaBuena extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

    public GotaBuena(Texture textura, float hitboxSize) {
        super(textura, hitboxSize);
    }
    
    public float setVelocidad() {
        // Velocidad fija para GotaBuena
        return 200;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(5);
        dropSound.play();
    }
}
