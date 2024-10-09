package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaSuper extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

    public GotaSuper(Texture textura, float hitboxSize) {
        super(textura, hitboxSize);
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(100);
        dropSound.play();
    }
}
