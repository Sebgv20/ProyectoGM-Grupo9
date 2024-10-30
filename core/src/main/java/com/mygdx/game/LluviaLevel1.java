package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

// Lluvia para el nivel 1
public class LluviaLevel1 implements ILluvia {
    private Array<Rectangle> rainDropsPos;
    private Array<Gota> rainDrops;
    private long lastDropTime;
    private Music rainMusic;
    private float hitboxSize = 55;

    public LluviaLevel1(Music rainMusic) {
        this.rainMusic = rainMusic;
        rainDrops = new Array<>();
        rainDropsPos = new Array<>();
    }

    @Override
    public void crear() {
        crearGotaDeLluvia(30);
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia(float tiempoRestante) {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 1920 - 64);
        raindrop.y = 1080;
        raindrop.width = hitboxSize;
        raindrop.height = hitboxSize;
        rainDropsPos.add(raindrop);

        // Todas las gotas deben sumar en total 1
        // Ajustar probabilidades en función del tiempo restante
        float probabilidadGotaBuena = 0.65f; // 65%
        float probabilidadGotaMala = 0.15f;   // 15%
        float probabilidadGotaSuper = 0.1f;  // 10%
        // gotaHeal tiene el último 5%

        if (tiempoRestante <= 30 && tiempoRestante > 15 ) { // Cambia la probabilidad a partir de 30 segundos
        	probabilidadGotaBuena = 0.57f; // 57%
            probabilidadGotaMala = 0.30f;   // 30%
            probabilidadGotaSuper = 0.10f;  // 10%
            // gotaHeal tiene el último 3%
        }
        if (tiempoRestante <= 15) { // Cambia la probabilidad a partir de 30 segundos
        	probabilidadGotaBuena = 0.50f; // 50%
            probabilidadGotaMala = 0.40f;   // 40%
            probabilidadGotaSuper = 0.099f;  // 9%
         // gotaHeal tiene el último %
        }

        // Generar un número aleatorio y seleccionar el tipo de gota
        float random = MathUtils.random();

        if (random < probabilidadGotaBuena) {
            rainDrops.add(new GotaBuena(hitboxSize));
        } else if (random < probabilidadGotaBuena + probabilidadGotaMala) {
            rainDrops.add(new GotaMala(hitboxSize));
        } else if (random < probabilidadGotaBuena + probabilidadGotaMala + probabilidadGotaSuper) {
            rainDrops.add(new GotaSuper(hitboxSize));
        } else {
            rainDrops.add(new GotaHeal(hitboxSize));
        }

        lastDropTime = TimeUtils.nanoTime();
    }
    
    // Cambia la velocidad de las gotas a medida que disminuye el tiempo
    private float calcularMultiplicadorVelocidad(GameLluviaMenu game) {
        // Obtener el tiempo restante del juego
        GameScreenLevel1 gameScreen = (GameScreenLevel1) game.getScreen();
        float tiempoRestante = gameScreen.getTiempoRestante();

        // Ajustar el multiplicador: a menor tiempo, mayor velocidad
        if (tiempoRestante > 30) {
            return 1.0f;  // Velocidad normal
        } else if (tiempoRestante > 16) {
            return 1.2f;  // Incrementar velocidad
        } else if (tiempoRestante > 6) {
            return 1.6f;  // Más rápido
        } else {
            return 2.0f;  // Velocidad máxima cuando queda muy poco tiempo
        }
    }

    @Override
    public void actualizarMovimiento(Tarro tarro, GameLluviaMenu game) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            GameScreenLevel1 gameScreen = (GameScreenLevel1) game.getScreen();
            float tiempoRestante = gameScreen.getTiempoRestante();
            crearGotaDeLluvia(tiempoRestante);
        }

        float multiplicadorVelocidad = calcularMultiplicadorVelocidad(game);

        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            Gota gota = rainDrops.get(i);

            raindrop.y -= gota.getVelocidad() * multiplicadorVelocidad * Gdx.graphics.getDeltaTime();

            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i);
                rainDrops.removeIndex(i);
                continue;
            }

            if (raindrop.overlaps(tarro.getArea())) {
                gota.aplicarEfecto(tarro);
                rainDropsPos.removeIndex(i);
                rainDrops.removeIndex(i);
            }
        }
    }

    @Override
    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (int i = 0; i < rainDropsPos.size; i++) {
            Gota gota = rainDrops.get(i);
            Rectangle raindrop = rainDropsPos.get(i);
            gota.dibujar(batch, raindrop);
        }
    }

    @Override
    public void destruir() {
        rainMusic.dispose();
    }
}

