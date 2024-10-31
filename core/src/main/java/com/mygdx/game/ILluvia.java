package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Interfaz para las variaciones de lluvia (así la lluvia puede ser distinta en cada nivel)
public interface ILluvia {
    // Inicia la creación de las gotas y la música de fondo.
    void crear();

    // Actualiza el movimiento de las gotas y su posición en función del tiempo y la interacción con el jugador.
    void actualizarMovimiento(Tarro tarro, GameLluviaMenu game);

    // Dibuja las gotas en pantalla.
    void actualizarDibujoLluvia(SpriteBatch batch);

    // Libera los recursos utilizados (dispose).
    void destruir();
}