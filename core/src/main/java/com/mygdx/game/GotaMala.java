package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;


public class GotaMala extends Gota {

    public GotaMala(Texture textura, float hitboxSize) {
        super(textura, hitboxSize);
    }

    @Override
    public void aplicarEfecto(Tarro tarro) {
        tarro.dañar();
        if (tarro.getPuntos() <= 30) {
            tarro.restarPuntos(tarro.getPuntos());
        } else {
            tarro.restarPuntos(30);
        }
    }
}
