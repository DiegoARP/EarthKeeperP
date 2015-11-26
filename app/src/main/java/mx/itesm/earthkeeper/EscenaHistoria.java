package mx.itesm.earthkeeper;

/**
 * Created by cooldarp on 10/4/2015.
 */

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

public class EscenaHistoria extends EscenaBase {


    // Regiones para imágenes
    private ITextureRegion regionFondo;
    // Sprite para el fondo
    private Sprite spriteFondo;
    //Botones
    private ITextureRegion btnSOS;
    private Sprite SOS;
    private ITextureRegion regionSOS;
    private ITextureRegion btnHow;
    private Sprite HowTo;
    private ITextureRegion regionHow;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("EARTHKEEPER-MASTER/Pantalla Galeria/Pantalla_ConsolaVACIA.jpg");

        btnSOS = cargarImagen ("EARTHKEEPER-MASTER/Pantalla Settings/SOS-Boton.png");
        btnHow = cargarImagen("EARTHKEEPER-MASTER/Pantalla Settings/HowToPlay-Boton.png");
        regionSOS = cargarImagen("EARTHKEEPER-MASTER/Pantalla Settings/sprite SOS.png");
        regionHow = cargarImagen("EARTHKEEPER-MASTER/Pantalla Settings/Sprite_Howtoplay.png");

    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);
        SOS = cargarSprite((ControlJuego.ANCHO_CAMARA/2)-490, ControlJuego.ALTO_CAMARA/2, regionSOS);
        HowTo = cargarSprite(200,-230, regionHow);


        Sprite spriteSOS = new Sprite(ControlJuego.ANCHO_CAMARA/2,
                (ControlJuego.ALTO_CAMARA/2)-320, btnSOS, actividadJuego.getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown() && !SOS.hasParent()) {

                    /*
                    detachChild(spriteL);

                    attachChild(spriteB);
                    return true;*/
                    attachChild(SOS);
                } else  if (pSceneTouchEvent.isActionUp() && SOS.hasParent()) {
                    detachChild(SOS);
                }


                return true; //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteSOS);
        setTouchAreaBindingOnActionDownEnabled(true);
        registerTouchArea(spriteSOS);

        Sprite spriteHowTo = new Sprite((ControlJuego.ANCHO_CAMARA /2)+45,(ControlJuego.ALTO_CAMARA/2)+320, btnHow, actividadJuego.getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown()&& !HowTo.hasParent()) {

                    /*
                    detachChild(spriteL);

                    attachChild(spriteB);
                    return true;*/
                    attachChild(HowTo);
                } else  if (pSceneTouchEvent.isActionUp()&& HowTo.hasParent()) {
                    detachChild(HowTo);
                }


                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(spriteHowTo);
        setTouchAreaBindingOnActionDownEnabled(true);
        registerTouchArea(spriteHowTo);



    }



   //* public void animHistoria(){
       // Animation animashion = AnimationUtils.loadAnimation(null, R.anim.animationfile);
        //regionFondo.setText("Some text view.");
        //regionFondo.setAnimation(spriteFondo);
    //}


    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaSettings();
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_SETTINGS;
    }

    @Override
    public void liberarEscena() {
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        regionFondo.getTexture().unload();
        regionFondo = null;
    }









}
