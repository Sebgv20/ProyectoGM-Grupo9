package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;



// Configuración del nivel de juego
public class GameLevelConfig {
    private ILluvia lluvia;
    private List<Gota> gotas;
    private int dificultad;

    private GameLevelConfig(Builder builder) {
        this.lluvia = builder.lluvia;
        this.gotas = builder.gotas; // Correctamente inicializado
        this.dificultad = builder.dificultad;
    }

    public ILluvia getLluvia() {
        return lluvia;
    }

    public List<Gota> getGotas() {
        return gotas;
    }

    public int getDificultad() {
        return dificultad;
    }

    public static class Builder {
        private ILluvia lluvia;
        private List<Gota> gotas = new ArrayList<>(); // Tipo explícito para la lista
        private int dificultad;

        public Builder setLluvia(ILluvia lluvia) {
            this.lluvia = lluvia;
            return this;
        }

        public Builder addGota(Gota gota) {
            this.gotas.add(gota); // Agregar gotas a la lista
            return this;
        }

        public Builder setDificultad(int dificultad) {
            this.dificultad = dificultad;
            return this;
        }

        public GameLevelConfig build() {
            return new GameLevelConfig(this);
        }
    }
}
