package com.mygdx.game;

public class EfectoVelocidadTemporal implements Efecto {
    private float duracion;
    private float tiempoTranscurrido;
    private float factorVelocidad;
    private float velocidadOriginal; // Ahora dinámico

    public EfectoVelocidadTemporal(float duracion, float factorVelocidad) {
        this.duracion = duracion;
        this.factorVelocidad = factorVelocidad;
        this.tiempoTranscurrido = 0;
    }

    @Override
    public void aplicar(Tarro tarro) {
        // Guardar la velocidad original antes de modificarla
        velocidadOriginal = tarro.getVelocidad();
        tarro.setVelocidad(velocidadOriginal * factorVelocidad);
        tarro.setVelozBuff(true); // Indicar que está bajo el efecto de velocidad
    }

    @Override
    public void actualizar(Tarro tarro, float deltaTime) {
        tiempoTranscurrido += deltaTime;
        if (haTerminado()) {
            // Restaurar la velocidad original una vez que el efecto termine
            tarro.setVelocidad(velocidadOriginal);
            tarro.setVelozBuff(false); // Indicar que el efecto ha terminado
        }
    }

    @Override
    public boolean haTerminado() {
        return tiempoTranscurrido >= duracion;
    }
}