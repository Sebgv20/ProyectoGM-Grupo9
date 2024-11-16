package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


// Gota estándar del juego
public class GotaBuena extends Gota {
    private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("dropSound.wav"));

    public GotaBuena(float hitboxSize) {
        super(hitboxSize);
    }
    
    @Override
    protected String definirRutaTextura() {
        return "drop.png";
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(10);
        dropSound.play();
    }
    
    @Override
    public float setVelocidad() {
        return 300;  // Velocidad fija para GotaBuena
    }
}