package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public class TextureManager {
    // Atributo estático que contiene la única instancia de la clase
    private static TextureManager instance;

    // Mapa para almacenar y reutilizar texturas
    private HashMap<String, Texture> textures;

    // Constructor privado para evitar la creación de múltiples instancias
    private TextureManager() {
        textures = new HashMap<>();
    }

    // Método estático para obtener la única instancia de la clase
    public static TextureManager getInstance() {
        if (instance == null) {
            instance = new TextureManager();
        }
        return instance;
    }

    // Método para obtener una textura, cargándola si aún no existe
    public Texture getTexture(String filePath) {
        if (!textures.containsKey(filePath)) {
            // Crea la textura y la almacena en el mapa
            Texture texture = new Texture(filePath);
            textures.put(filePath, texture);
        }
        return textures.get(filePath);
    }

    // Método para liberar todas las texturas cargadas
    public void dispose() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        textures.clear();
    }
}