package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


// Gota que cura
public class GotaHeal extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("dropHealSound.mp3"));

    public GotaHeal(float hitboxSize) {
        super(hitboxSize);
    }
    
    @Override
    protected String definirRutaTextura() {
        return "dropHeal.png";
    }
    
    @Override
    public void aplicarEfecto(Tarro tarro) {
    	if (tarro.getVidas() < 3) {
    		tarro.curar();
    		tarro.sumarPuntos(2);
        } else {
        	tarro.sumarPuntos(5);
        }
        dropSound.play();
    }
    
    public float setVelocidad() {
        return 150; // Velocidad fija para GotaHeal
    }
}