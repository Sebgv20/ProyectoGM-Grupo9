package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaMala extends Gota {
	private Sound FinaldropSound = Gdx.audio.newSound(Gdx.files.internal("FinalHurt.ogg"));
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("hurt.mp3"));
	
	public GotaMala(float hitboxSize) {
		super(hitboxSize);
    }
	
	@Override
	public Texture definirTextura() {
	    Texture textura = new Texture(Gdx.files.internal("dropBad.png"),true);
	    textura.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
	    return textura;
	}
	
	public float setVelocidad() {
        // Velocidad fija para GotaBuena
        return 250;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
        if (tarro.getVidas() >= 1) {
        	dropSound.play();
        } else {
        	FinaldropSound.play();
        }

        if (tarro.getPuntos() <= 50) {
            tarro.restarPuntos(tarro.getPuntos());
        } else {
            tarro.restarPuntos(50);
        }
    }
}
