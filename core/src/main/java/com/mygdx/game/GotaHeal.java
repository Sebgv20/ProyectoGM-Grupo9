package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


public class GotaHeal extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("healDrop.mp3"));

    public GotaHeal(float hitboxSize) {
        super(hitboxSize);
    }
    
    @Override
	public Texture definirTextura() {
	    Texture textura = new Texture(Gdx.files.internal("dropHeal.png"),true);
	    textura.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
	    return textura;
	}
    
    public float setVelocidad() {
        return 150;
    }
    
    @Override
    public void aplicarEfecto(Tarro tarro) {
    	if (tarro.getVidas() < 3) {
    		tarro.curar();
    		tarro.sumarPuntos(5);
        } else {
        	tarro.sumarPuntos(5);
        }
        dropSound.play();
    }
}