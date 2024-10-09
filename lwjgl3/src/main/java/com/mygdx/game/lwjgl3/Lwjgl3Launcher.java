package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.EjemploLluvia;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // Esto maneja el soporte para macOS y ayuda en Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new EjemploLluvia(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("EjemploLluvia");
        // Vsync limita los cuadros por segundo a lo que tu hardware puede mostrar y ayuda a eliminar
        // el desgarro de pantalla. Esta configuración no siempre funciona en Linux, así que la línea siguiente es un respaldo.
        configuration.useVsync(true);
        // Limita los FPS a la tasa de refresco del monitor activo, más 1 para intentar igualar
        // tasas de refresco fraccionales. La configuración de Vsync anterior debería limitar el FPS real
        // para que coincida con el monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        // Si eliminas la línea anterior y configuras Vsync en falso, puedes obtener FPS ilimitados,
        // lo cual puede ser útil para probar el rendimiento, pero también puede ser muy estresante
        // para cierto hardware. También puede que necesites configurar los controladores de la GPU
        // para deshabilitar completamente Vsync; esto puede causar desgarro de pantalla.
        configuration.setWindowedMode(1024, 768); // Cambia el tamaño de la ventana aquí
        
        // Puedes cambiar estos archivos; están en lwjgl3/src/main/resources/ .
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
