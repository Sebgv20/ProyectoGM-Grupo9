package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaSuper extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("superDrop.wav"));

    public GotaSuper(Texture textura, float hitboxSize) {
        super(textura, hitboxSize);
    }

    
    public float setVelocidad() {
        // Velocidad fija para GotaBuena
        return 500;
    }
    
    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(30);
        dropSound.play();
    }
}
