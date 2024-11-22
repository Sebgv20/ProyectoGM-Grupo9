package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

// Gota que otorga velocidad de movimiento al jugador
public class GotaZap extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("dropZapSound.mp3"));

    public GotaZap(float hitboxSize) {
        super(hitboxSize);
    }

    @Override
    protected String definirRutaTextura() {
        return "dropZap.png";
    }
    
    @Override
    public void aplicarEfecto(Tarro tarro) {
        // Aplica un efecto de velocidad por 5 segundos
        tarro.agregarEfecto(new EfectoVelocidadTemporal(5, 1.3f)); // +30% de velocidad y dura 5 segundos
        tarro.sumarPuntos(5);
        dropSound.play();
    }
    
    public float setVelocidad() {
        return 650; // Velocidad fija para GotaZap
    }
}
