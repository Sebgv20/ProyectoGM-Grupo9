package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

// Gota estándar del juego
public class GotaInstaKill extends Gota {
	private Sound FinaldropSound = Gdx.audio.newSound(Gdx.files.internal("FinalHurt.ogg"));

    public GotaInstaKill(float hitboxSize) {
        super(hitboxSize);
    }
    
    @Override
	public Texture definirTextura() {
	    Texture textura = new Texture(Gdx.files.internal("dropInstaKill.png"),true);
	    textura.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
	    return textura;
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