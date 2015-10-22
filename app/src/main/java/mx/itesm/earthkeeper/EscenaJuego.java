package mx.itesm.earthkeeper;

/**
 * Created by cooldarp on 10/11/2015.
 */

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;



public class EscenaJuego extends EscenaBase {



    // Regiones para imágenes
    private ITextureRegion regionFondo;
    private ITextureRegion Galaxias;
    private ITextureRegion Tierra;
    private ITextureRegion Marco;
    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteGalaxias;
    private Sprite spriteTierra;
    private Sprite spriteMarco;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoNegro.jpg");
        Galaxias = cargarImagen("Galaxias_Juntas.png");
        Tierra = cargarImagen("Tierra.png");
        Marco = cargarImagen("PantallaMarcoFINAL.png");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        spriteGalaxias = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Galaxias);
        spriteTierra = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Tierra);
        spriteMarco = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Marco);
        attachChild(spriteFondo);
        attachChild(spriteGalaxias);
        attachChild(spriteTierra);
        attachChild(spriteMarco);
    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaAcercaDe();
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_JUEGO;
    }

    @Override
    public void liberarEscena() {
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        regionFondo.getTexture().unload();
        Galaxias.getTexture().unload();
        Tierra.getTexture().unload();
        Marco.getTexture().unload();
        regionFondo = null;
        Galaxias = null;
        Tierra = null;
        Marco = null;
    }





}
