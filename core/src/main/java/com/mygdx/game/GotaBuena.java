package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaBuena extends Gota {
    private Sound dropSound;

    public GotaBuena(Texture textura, float hitboxSize, Sound dropSound) {
        super(textura, hitboxSize);
        this.dropSound = dropSound;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(10);
        dropSound.play();
    }
}
