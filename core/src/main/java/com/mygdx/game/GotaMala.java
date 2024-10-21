package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaMala extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	
	public GotaMala(float hitboxSize) {
		super(hitboxSize);
    }
	
	@Override
	public Texture definirTextura() {
	    Texture textura = new Texture(Gdx.files.internal("dropBad.png"));
	    textura.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);  // Aplicar filtrado lineal
	    return textura;
	}
	
	public float setVelocidad() {
        // Velocidad fija para GotaBuena
        return 250;
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
        dropSound.play();
        if (tarro.getPuntos() <= 50) {
            tarro.restarPuntos(tarro.getPuntos());
        } else {
            tarro.restarPuntos(50);
        }
    }
}
