package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


// Gota estándar del juego
public class GotaInstaKill extends Gota {
	private Sound FinaldropSound = Gdx.audio.newSound(Gdx.files.internal("FinalHurt.ogg"));

    public GotaInstaKill(float hitboxSize) {
        super(hitboxSize);
    }
    
    @Override
    protected String definirRutaTextura() {
        return "dropInstaKill.png";
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.dañarPlus();
        FinaldropSound.play();
        if(tarro.getPuntos() <= 500) {
        	tarro.restarPuntos(tarro.getPuntos());
        }
        else {
        	tarro.restarPuntos(500);
        }
    }
    
    @Override
    public float setVelocidad() {
        return 150;  // Velocidad fija para GotaBuena
    }
}