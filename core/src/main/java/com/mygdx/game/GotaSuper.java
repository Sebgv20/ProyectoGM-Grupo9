package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


// Gota que otorga puntos extra
public class GotaSuper extends Gota {
	private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("dropSuperSound.wav"));

    public GotaSuper(float hitboxSize) {
        super(hitboxSize);
    }

    @Override
    protected String definirRutaTextura() {
        return "dropSuper.png";
    }
    
    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.sumarPuntos(35);
        dropSound.play();
    } 
    
    public float setVelocidad() {
        return 500; // Velocidad fija para GotaSuper
    }
}
