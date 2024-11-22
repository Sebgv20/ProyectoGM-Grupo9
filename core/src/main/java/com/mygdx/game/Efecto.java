package com.mygdx.game;

public interface Efecto {
	void aplicar(Tarro tarro);
	void actualizar(Tarro tarro, float deltaTime);
	boolean haTerminado();
}