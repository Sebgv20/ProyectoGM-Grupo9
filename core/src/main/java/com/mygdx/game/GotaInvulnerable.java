package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

// Gota que otorga invulnerabilidad temporal al jugador
public class GotaInvulnerable extends Gota {
    private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("dropHealSound.mp3"));

    public GotaInvulnerable(float hitboxSize) {
        super(hitboxSize);
    }

    @Override
    protected String definirRutaTextura() {
        return "dropShield.png";
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        // Aplica el efecto de invulnerabilidad por 5 segundos
        tarro.agregarEfecto(new EfectoInvulnerableTemporal(7));
        tarro.sumarPuntos(10); // Bonificación de puntos por recoger la gota
        dropSound.play();
    }

	@Override
	public float setVelocidad() {
		return 500;
	}
    
}