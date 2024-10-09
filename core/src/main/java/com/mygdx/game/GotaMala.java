package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaMala extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	
	public GotaMala(Texture textura, float hitboxSize) {
		super(textura, hitboxSize);
    }
	
	public float setVelocidad() {
        // Velocidad fija para GotaBuena
        return 200;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
        dropSound.play();
        if (tarro.getPuntos() <= 30) {
            tarro.restarPuntos(tarro.getPuntos());
        } else {
            tarro.restarPuntos(30);
        }
    }
}
