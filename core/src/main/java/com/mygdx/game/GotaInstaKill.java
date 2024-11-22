package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


// Gota estándar del juego
public class GotaInstaKill extends Gota {
	private Sound FinaldropSound = Gdx.audio.newSound(Gdx.files.internal("FinalHurt.ogg"));
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("hurt.mp3"));

    public GotaInstaKill(float hitboxSize) {
        super(hitboxSize);
    }
    
    @Override
    protected String definirRutaTextura() {
        return "dropInstaKill.png";
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
    	if(!tarro.isInvulnerable()) {
    		FinaldropSound.play();
    	} else {
    		dropSound.play();
    	}
    	tarro.dañarPlus();   
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