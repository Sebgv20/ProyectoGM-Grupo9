package com.mygdx.game;

public class EfectoInvulnerableTemporal implements Efecto {
    private float tiempoRestante;

    public EfectoInvulnerableTemporal(float duracion) {
        this.tiempoRestante = duracion;
    }

    @Override
    public void aplicar(Tarro tarro) {
        tarro.setInvulnerable(true);  // Activa la invulnerabilidad
    }

    @Override
    public void actualizar(Tarro tarro, float deltaTime) {
        // Resta el tiempo restante basado en el tiempo transcurrido (deltaTime)
        if (tiempoRestante > 0) {
            tiempoRestante -= deltaTime;
        } else {
            tarro.setInvulnerable(false);  // Desactiva la invulnerabilidad
        }
    }

    @Override
    public boolean haTerminado() {
        return tiempoRestante <= 0;
    }
}