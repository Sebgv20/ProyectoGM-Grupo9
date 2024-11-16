package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public class TextureManager {
    private static TextureManager instance;
    private HashMap<String, Texture> textures;

    private TextureManager() {
        textures = new HashMap<>();
    }

    public static TextureManager getInstance() {
        if (instance == null) {
            instance = new TextureManager();
        }
        return instance;
    }

    public Texture getTexture(String filePath) {
        if (!textures.containsKey(filePath)) {
            // Crea la textura con mipmaps habilitados
            Texture texture = new Texture(filePath);
            textures.put(filePath, texture);
        }
        return textures.get(filePath);
    }

    public void dispose() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        textures.clear();
    }
}