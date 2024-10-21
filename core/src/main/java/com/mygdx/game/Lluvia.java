package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
    private Array<Rectangle> rainDropsPos;
    private Array<Gota> rainDrops;
    private long lastDropTime;
    private Music rainMusic;
    private float hitboxSize = 55; // Cambia el tamaño de la hitbox y de la imagen

    public Lluvia(Music rainMusic) {
        this.rainMusic = rainMusic;
        rainDrops = new Array<>();
        rainDropsPos = new Array<>();
    }

    public void crear() {
        crearGotaDeLluvia(30); // Llama a la función con un valor inicial para el tiempo restante
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

        // Ajustar probabilidades en función del tiempo restante
        float probabilidadGotaBuena = 0.65f; // 65%
        float probabilidadGotaMala = 0.20f;   // 20%
        float probabilidadGotaSuper = 0.12f;  // 12%
        // gotaHeal tiene el último 3%
        // Todas deben sumar en total 1

        if (tiempoRestante <= 30 && tiempoRestante > 15 ) { // Cambia la probabilidad a partir de 30 segundos
        	probabilidadGotaBuena = 0.55f; // 55%
            probabilidadGotaMala = 0.30f;   // 30%
            probabilidadGotaSuper = 0.12f;  // 10%
            // gotaHeal tiene el último 3%
        }
        if (tiempoRestante <= 15) { // Cambia la probabilidad a partir de 30 segundos
        	probabilidadGotaBuena = 0.50f; // 60%
            probabilidadGotaMala = 0.40f;   // 25%
            probabilidadGotaSuper = 0.095f;  // 10%
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

    private float calcularMultiplicadorVelocidad(GameLluviaMenu game) {
        // Obtener el tiempo restante del juego
        GameScreen gameScreen = (GameScreen) game.getScreen();
        float tiempoRestante = gameScreen.getTiempoRestante();

        // Ajustar el multiplicador: a menor tiempo, mayor velocidad
        if (tiempoRestante > 31) {
            return 1.0f;  // Velocidad normal
        } else if (tiempoRestante > 16) {
            return 1.5f;  // Incrementar velocidad
        } else if (tiempoRestante > 6) {
            return 2.0f;  // Más rápido
        } else {
            return 3.0f;  // Velocidad máxima cuando queda muy poco tiempo
        }
    }

    public void actualizarMovimiento(Tarro tarro, GameLluviaMenu game) {
        // Crear gotas de lluvia a intervalos
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            GameScreen gameScreen = (GameScreen) game.getScreen();
            float tiempoRestante = gameScreen.getTiempoRestante();
            crearGotaDeLluvia(tiempoRestante); // Pasar el tiempo restante
        }

        float multiplicadorVelocidad = calcularMultiplicadorVelocidad(game);

        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            Gota gota = rainDrops.get(i);

            // Ajustar la velocidad con el multiplicador basado en el tiempo restante
            raindrop.y -= gota.getVelocidad() * multiplicadorVelocidad * Gdx.graphics.getDeltaTime();

            // Si la gota sale de la pantalla, elimínala
            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i);
                rainDrops.removeIndex(i);
                continue;
            }

            // Verificar si colisiona con el tarro
            if (raindrop.overlaps(tarro.getArea())) {
                gota.aplicarEfecto(tarro);  // Aplicar el efecto (dañar o sumar puntos)
                rainDropsPos.removeIndex(i);
                rainDrops.removeIndex(i);
            }
        }
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (int i = 0; i < rainDropsPos.size; i++) {
            Gota gota = rainDrops.get(i);
            Rectangle raindrop = rainDropsPos.get(i);
            gota.dibujar(batch, raindrop);
        }
    }

    public void destruir() {
        rainMusic.dispose();
    }
}
