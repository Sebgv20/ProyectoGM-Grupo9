package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
    private Array<Rectangle> rainDropsPos;
    private Array<Gota> rainDrops;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Texture gotaSuper;
    private Music rainMusic;
    private float hitboxSize = 30; // Cambia el tamaño de la hitbox y de la imagen

    public Lluvia(Texture gotaBuena, Texture gotaMala, Texture gotaSuper, Music rainMusic) {
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.gotaSuper = gotaSuper;
        this.rainMusic = rainMusic;
        rainDrops = new Array<>();
        rainDropsPos = new Array<>();
    }

    public void crear() {
        crearGotaDeLluvia(); // Llama a la función sin parámetros
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = hitboxSize;
        raindrop.height = hitboxSize;
        rainDropsPos.add(raindrop);
        
        if (MathUtils.random(10, 100) <= 20) {	rainDrops.add(new GotaMala(gotaMala, hitboxSize));} 
        else if (MathUtils.random(1, 10) < 2){	rainDrops.add(new GotaSuper(gotaSuper, hitboxSize));}
        else {	rainDrops.add(new GotaBuena(gotaBuena, hitboxSize));}
        lastDropTime = TimeUtils.nanoTime();
    }

    public void actualizarMovimiento(Tarro tarro) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
        
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
            
            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i);
                rainDrops.removeIndex(i);
                continue;
            }

            if (raindrop.overlaps(tarro.getArea())) {
                rainDrops.get(i).aplicarEfecto(tarro);
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

