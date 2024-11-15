package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

// Lluvia para el nivel 2
public class LluviaLevel3 implements ILluvia {
    private Array<Rectangle> rainDropsPos;
    private Array<Gota> rainDrops;
    private long lastDropTime;
    private Music rainMusic;
    private float hitboxSize = 55;

    public LluviaLevel3(Music rainMusic) {
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
        float probabilidadGotaBuena = 0.55f;  // 55%
        float probabilidadGotaInstaKill = 0.25f;   // 25%
        float probabilidadGotaSuper = 0.10f;  // 10%
        float probabilidadGotaZap = 0.5f; 	  // 5%
        // gotaHeal tiene el último 5%

        if (tiempoRestante <= 30 && tiempoRestante > 15 ) { // Cambia la probabilidad a partir de 30 segundos
        	probabilidadGotaBuena = 0.45f;  // 45%
            probabilidadGotaInstaKill = 0.40f;   // 40%
            probabilidadGotaSuper = 0.07f;  // 5%
            probabilidadGotaZap = 0.06f;     // 6%
            // gotaHeal tiene el último 2%
        }
        if (tiempoRestante <= 15) { // Cambia la probabilidad a partir de 30 segundos
        	probabilidadGotaBuena = 0.35f;   // 60%
            probabilidadGotaInstaKill = 0.55f;    // 25%
            probabilidadGotaSuper = 0.033f;  // 9.9%
            probabilidadGotaZap = 0.06f;     // 5%
         // gotaHeal tiene el último %
        }

        // Generar un número aleatorio y seleccionar el tipo de gota
        float random = MathUtils.random();

        if (random < probabilidadGotaBuena) {
            rainDrops.add(new GotaBuena(hitboxSize));
        } else if (random < probabilidadGotaBuena + probabilidadGotaInstaKill) {
            rainDrops.add(new GotaInstaKill(hitboxSize));
        } else if (random < probabilidadGotaBuena + probabilidadGotaInstaKill + probabilidadGotaSuper) {
            rainDrops.add(new GotaSuper(hitboxSize));
        } else if (random < probabilidadGotaBuena + probabilidadGotaInstaKill + probabilidadGotaSuper + probabilidadGotaZap) {
            rainDrops.add(new GotaZap(hitboxSize));
        } else {
            rainDrops.add(new GotaHeal(hitboxSize));
        }

        lastDropTime = TimeUtils.nanoTime();
    }

    private float calcularMultiplicadorVelocidad(GameLluviaMenu game) {
        GameScreenLevel3 gameScreen = (GameScreenLevel3) game.getScreen();
        float tiempoRestante = gameScreen.getTiempoRestante();

        // Ajustar el multiplicador: a menor tiempo, mayor velocidad
        if (tiempoRestante > 30) {
            return 1.2f;  // Velocidad normal
        } else if (tiempoRestante > 16) {
            return 1.7f;  // Incrementar velocidad
        } else if (tiempoRestante > 6) {
            return 2.5f;  // Más rápido
        } else {
            return 3f;  // Velocidad máxima cuando queda muy poco tiempo
        }
    }

    @Override
    public void actualizarMovimiento(Tarro tarro, GameLluviaMenu game) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            GameScreenLevel3 gameScreen = (GameScreenLevel3) game.getScreen();
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
