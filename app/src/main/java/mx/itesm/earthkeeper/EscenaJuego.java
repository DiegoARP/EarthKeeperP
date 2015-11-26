package mx.itesm.earthkeeper;

/**
 * Created by cooldarp on 10/11/2015.
 */

import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.Toast;

import org.andengine.audio.sound.Sound;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IDrawHandler;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.controller.MultiTouch;
import org.andengine.input.touch.controller.MultiTouchController;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.IDisposable;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import java.util.ArrayList;
import java.util.Random;

//RECUPERACION
public class EscenaJuego extends EscenaBase {

    private boolean juegoCorriendo = true;
    private Camera mCamera;
    // Regiones para imágenes
    private ITextureRegion regionFondo;
    private ITextureRegion Galaxias;
    private ITextureRegion Tierra;
    private ITextureRegion Marco;
    private ITextureRegion GalaxiaVerde;
    private ITextureRegion GalaxiaRojo;
    private ITextureRegion GalaxiaAmarillo;
    private ITextureRegion GalaxiaAzul;
    private ITextureRegion Vida_uno;
    private ITextureRegion Vida_dos;
    private ITextureRegion Vida_tres;
    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteGalaxias;
    private Sprite spriteTierra;
    private Sprite spriteMarco;
    private Sprite spriteGalaxiaVerde;
    private Sprite spriteGalaxiaAzul;
    private Sprite spriteGalaxiaAmarillo;
    private Sprite spriteGalaxiaRojo;
    private Sprite spriteVida1;
    private Sprite spriteVida2;
    private Sprite spriteVida3;
    private int yolo=0;
    private int colEscudo=0;
    //VIDA
    private int ANCHO_VIDA;
    private int vida;
    private int contAzul=0;
    private int contVerde=0;
    private int contAmarillo=0;
    private int contRojo=0;
    private Rectangle rectVida;
    private Rectangle rectVidaActual;

    private int VIDA=3;
    //Puntos
    private Text txtPuntos;
    private int puntos;
    private IFont fontMonster;

    //	Enemigos
    private ArrayList<Enemigos> listaEnemigos;
    private ArrayList<ITextureRegion> listaP;
    private ArrayList<ITextureRegion> listaP1;
    private ArrayList<ITextureRegion> listaP2;
    private ArrayList<ITextureRegion> listaU;
            ;
    private ITextureRegion regionVerde;
    private ITextureRegion regionRojo;
    private ITextureRegion regionAmarillo;
    private ITextureRegion regionAzul;

    //Naves Triangulares
    private ITextureRegion regionTVerde;
    private ITextureRegion regionTRojo;
    private ITextureRegion regionTAmarillo;
    private ITextureRegion regionTAzul;

    //Naves Platillos
    private ITextureRegion regionPVerde;
    private ITextureRegion regionPRojo;
    private ITextureRegion regionPAmarillo;
    private ITextureRegion regionPAzul;

    //Bosses
    private ITextureRegion regionBossAzul;
    private ITextureRegion regionBossVerde;
    private ITextureRegion regionBossAmarillo;
    private ITextureRegion regionBossRojo;
    private Enemigos enemigoA;
    private Enemigos enemigoAm;
    private Enemigos enemigoV;
    private Enemigos enemigoR;



    // Fin del juego
    private ITextureRegion regionFin;
    //	Tiempo	para	generar	enemigos
    private float tiempoEnemigos =	0;				//	Contador
    private float	LIMITE_TIEMPO	=	1.8f;		//	Cada	2.5s	se	crea
    //	Sprite	animado

   // private AnimatedSprite spriteFondo;		//	Sprite
    //private TiledTextureRegion regionFondo;		//	Región

    // Escena de PAUSA
    private CameraScene escenaPausa;    // La escena que se muestra al hacer pausa
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;
    private ITextureRegion regionBtnPlay;
    private ITextureRegion regionBtnMainMenu;
    //Escudo
    private ITextureRegion regionEscudo;
    private ITextureRegion regionEscudo1;
    private ITextureRegion regionEscudo2;
    private ITextureRegion regionEscudo3;
    private ITextureRegion regionEscudo4;
    private ITextureRegion regionEscudo5;
    private ITextureRegion regionEscudo6;
    private ITextureRegion regionEscudoActivado;
    private Sprite spriteEscudoActivado;
    private Sprite spriteEscudo;
    private Sprite escudo;

    //Efectos de sonido
    private Sound sonidoTierra;
    private Sound sonidoGameOver;
    private Sound sonidoEscudo;




    public interface IEntity extends IDrawHandler, IUpdateHandler, IDisposable {
        public boolean isIgnoreUpdate();
        public void setIgnoreUpdate(final boolean pIgnoreUpdate);
    }



    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Fondo_Consola.jpg");
        //regionFondo	=	cargarImagenMosaico("Fondo_Anim.png", 5306, 800,	1,	4);
        GalaxiaVerde = cargarImagen("Circulo_Amarillo.png");
        GalaxiaRojo = cargarImagen("Circulo_Amarillo.png");
        GalaxiaAmarillo = cargarImagen("Circulo_Amarillo.png");
        GalaxiaAzul = cargarImagen("Circulo_Amarillo.png");
        Galaxias = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Galaxias_Juntas.png");
        Tierra = cargarImagen("Tierra_NUEVA.png");
        Marco = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/MARCO-FINAL-REDONDO.png");
        regionVerde = cargarImagen("Naves/Circular_Verde.png");
        regionAzul = cargarImagen("Naves/Circular_Azul.png");
        regionAmarillo = cargarImagen("Naves/Circular_Amarilo.png");
        regionRojo = cargarImagen("Naves/Circular_Rojo.png");
        regionFin = cargarImagen("EARTHKEEPER-MASTER/Pantalla Gameover/Pantalla_GAMEOVER.jpg");
        Vida_uno = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/VIDA_1.png");
        Vida_dos = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/VIDA_2.png");
        Vida_tres = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/VIDA_3.png");
        regionTVerde = cargarImagen("Naves/Triangular_Verde.png");
        regionTAzul = cargarImagen("Naves/Triangular_Azul.png");
        regionTAmarillo = cargarImagen("Naves/Triangular_Amarillo.png");
        regionTRojo = cargarImagen("Naves/Triangular_Rojo.png");
        regionPVerde = cargarImagen("Naves/Redondo_Verde.png");
        regionPAzul = cargarImagen("Naves/Redondo_Azul.png");
        regionPAmarillo = cargarImagen("Naves/Redondo_Amarillo.png");
        regionPRojo = cargarImagen("Naves/Redondo_Rojo.png");
        //Bosses
        regionBossAzul = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Bosses/Sb-neet.png");
        regionBossVerde = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Bosses/Slumaka.png");
        regionBossAmarillo = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Bosses/Sthee.png");
        regionBossRojo = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Bosses/Sklaam.png");


        fontMonster = cargarFont("OCR.ttf", 32, 0xFFFFFFFF, "Puntos: 0123456789");
        // Pausa
        regionBtnPausa = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/Boton-Pausa.png");
        regionPausa = cargarImagen("EARTHKEEPER-MASTER/Pantalla Pausa/Pantalla_Pausa.jpg");
        regionBtnPlay = cargarImagen("EARTHKEEPER-MASTER/Pantalla Principal/PLAY_Boton.png");
        regionBtnMainMenu = cargarImagen ("EARTHKEEPER-MASTER/Pantalla Pausa/Main-Menu_Boton.png");
        //Escudo
        regionEscudo = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/escudo nuevo/barra escudo 0.png");
        regionEscudo1 = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/escudo nuevo/barra escudo 1.png");
        regionEscudo2 = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/escudo nuevo/barra escudo 2.png");
        regionEscudo3 = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/escudo nuevo/barra escudo 3.png");
        regionEscudo4 = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/escudo nuevo/barra escudo 4.png");
        regionEscudo5 = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/escudo nuevo/barra escudo 5.png");
        regionEscudo6 = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/escudo nuevo/barra escudo 6.png");
        regionEscudoActivado = cargarImagen("EARTHKEEPER-MASTER/Pantalla Juego/EscudoTierra.png");
        //Sonidos
        sonidoTierra = cargarEfecto("EARTHKEEPER-MASTER/Sonidos/Grito.wav");
        sonidoGameOver = cargarEfecto("EARTHKEEPER-MASTER/Sonidos/GameOver.wav");
        sonidoEscudo = cargarEfecto("EARTHKEEPER-MASTER/Sonidos/Escudo.wav");


    }



   /* @Override

    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
        listaP.add(regionAmarillo);
        listaP.add(regionVerde);
        listaP.add(regionAzul);
        listaP.add(regionRojo);
        for (int i  = 0; i<=3; i++){

            Sprite spriteEnemigo = cargarSprite(ControlJuego.ANCHO_CAMARA +  listaP.get(i).getWidth(),
                    (float) (Math.random() * ControlJuego.ALTO_CAMARA -  listaP.get(i).getHeight()) +
                            listaP.get(i).getHeight(),  listaP.get(i));
            Enemigos nuevoEnemigo = new Enemigos(spriteEnemigo);
            listaEnemigos.add(nuevoEnemigo);
           this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
            registerTouchArea(nuevoEnemigo.getSpriteEnemigo());
            setTouchAreaBindingOnActionDownEnabled(true);

            attachChild(nuevoEnemigo.getSpriteEnemigo());
        }


        return true;
    }/*

  /*  public boolean onTouchEvent(MotionEvent event) {
        int myEventAction = event.getAction();

        float X = event.getX();
        float Y = event.getY();

        switch (myEventAction) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE: {
                spriteTierra.setPosition(X, Y);
                break;}
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }*/

    private void agregarVida() {
        // Vida
        ANCHO_VIDA = ControlJuego.ANCHO_CAMARA/2;
        //vida = 100; // %
        // Fondo
        rectVida = new Rectangle(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA-50,
                ANCHO_VIDA+10,ANCHO_VIDA/8,actividadJuego.getVertexBufferObjectManager());
        rectVida.setColor(0, 0, 0, 0.4f);
        attachChild(rectVida);
        // Nivel
        rectVidaActual = new Rectangle(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA-50,
                ANCHO_VIDA,ANCHO_VIDA/8,actividadJuego.getVertexBufferObjectManager());
        rectVidaActual.setColor(0, 1, 0);

        attachChild(rectVidaActual);
    }

    private void agregarTextoPuntos() {
        txtPuntos = new Text(ControlJuego.ANCHO_CAMARA-440,ControlJuego.ALTO_CAMARA-70,
                fontMonster," 0          ",actividadJuego.getVertexBufferObjectManager());
        attachChild(txtPuntos);
    }

    private void actualizarPuntos() {
        txtPuntos.setText(" " + puntos);
    }



private void crearEnemigos() {
        listaP.add(regionAmarillo);
        listaP.add(regionVerde);
        listaP.add(regionAzul);
        listaP.add(regionRojo);
        //System.out.println(listaP.size());
        int z=0;
        //int random = (int )(Math.random() * 4 + 1);
        //for (int x = 700; x <= 1200; x += 100) {
            //int random = (int )(Math.random() * 4 + 1);
            Random ran = new Random();
             z = ran.nextInt(3 - 0 + 1) + 0;
            //for (int y = 100; y <= 700; y += 100) {
                Sprite nave = cargarSprite(-100, 100, listaP.get(z));
                attachChild(nave);
                Enemigos enemigo = new Enemigos(nave);
                listaEnemigos.add(enemigo);
          //  }
       // }
       /* for (int x = 100; x <= -100; x += -100) {
            for (int y = -100; y <= -100; y += -100) {
                Sprite nave = cargarSprite(x, y, regionEnemigo);
                attachChild(nave);
                Enemigos enemigo = new Enemigos(nave);
                listaEnemigos.add(enemigo);
            }
        }*/
    }



    @Override
    public void crearEscena() {
        listaEnemigos = new ArrayList<>();
        //listaU = new ArrayList<>();
        int z=0;

       // actualizarPuntos();
        listaP = new ArrayList<>();
        listaP1 = new ArrayList<>();
        listaP2 = new ArrayList<>();


            listaP.add(regionAmarillo);
            listaP.add(regionVerde);
            listaP.add(regionAzul);
            listaP.add(regionRojo);

            listaP1.add(regionAmarillo);
            listaP1.add(regionVerde);
            listaP1.add(regionAzul);
            listaP1.add(regionRojo);
            listaP1.add(regionTAmarillo);
            listaP1.add(regionTVerde);
            listaP1.add(regionTAzul);
            listaP1.add(regionTRojo);



            listaP2.add(regionAmarillo);
            listaP2.add(regionVerde);
            listaP2.add(regionAzul);
            listaP2.add(regionRojo);
            listaP2.add(regionTAmarillo);
            listaP2.add(regionTVerde);
            listaP2.add(regionTAzul);
            listaP2.add(regionTRojo);
            listaP2.add(regionPAmarillo);
            listaP2.add(regionPVerde);
            listaP2.add(regionPAzul);
            listaP2.add(regionPRojo);


        spriteGalaxias = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, Galaxias);
        spriteGalaxiaVerde = cargarSprite(0,0,GalaxiaVerde);
        spriteGalaxiaAzul = cargarSprite(ControlJuego.ANCHO_CAMARA,0,GalaxiaAzul);
        spriteGalaxiaRojo = cargarSprite(0,ControlJuego.ALTO_CAMARA, GalaxiaRojo);
        spriteGalaxiaAmarillo = cargarSprite(ControlJuego.ANCHO_CAMARA,ControlJuego.ALTO_CAMARA,GalaxiaAmarillo);

        /*
        spriteFondo	=	new	AnimatedSprite(ControlJuego.ANCHO_CAMARA/2,
                ControlJuego.ALTO_CAMARA/2,	regionFondo,
                actividadJuego.getVertexBufferObjectManager());
        spriteFondo.animate(200);
*/

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        //Fondo animado
        /*AutoParallaxBackground fondoAnimado	=	new	AutoParallaxBackground(1,1,1,5);
        fondoAnimado.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(-3, spriteFondo));
        setBackground(fondoAnimado);*/
        //

        spriteTierra = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Tierra);
        spriteMarco = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Marco);
        spriteVida1 = cargarSprite(ControlJuego.ANCHO_CAMARA-838,ControlJuego.ALTO_CAMARA-71,Vida_uno);
        spriteVida2 = cargarSprite(ControlJuego.ANCHO_CAMARA-793,ControlJuego.ALTO_CAMARA-75,Vida_dos);
        spriteVida3 = cargarSprite(ControlJuego.ANCHO_CAMARA-750,ControlJuego.ALTO_CAMARA-79,Vida_tres);
        //spriteEscudo =cargarSprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,regionEscudo);

        attachChild(spriteFondo);
        attachChild(spriteGalaxiaVerde);
        attachChild(spriteGalaxiaRojo);
        attachChild(spriteGalaxiaAmarillo);
        attachChild(spriteGalaxiaAzul);
        attachChild(spriteGalaxias);
        attachChild(spriteTierra);
        attachChild(spriteMarco);
        //attachChild(spriteEscudo);
        attachChild(spriteVida1);
        attachChild(spriteVida2);
        attachChild(spriteVida3);

       // crearEnemigos();
        // Crear elementos de pausa
        agregarPausa();
        //Escudo
        agregarEscudo();
        //registerTouchArea(spriteTierra);
        setTouchAreaBindingOnActionDownEnabled(true);
        agregarTextoPuntos();
        actividadJuego.reproducirMusica("EARTHKEEPER-MASTER/Sonidos/Fondo.wav",true);
    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        actividadJuego.detenerMusica();
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaJuego();
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

    private void agregarEscudo(){

        escudo = new Sprite(spriteTierra.getX()-500, spriteTierra.getY()-100,
                regionEscudoActivado, actividadJuego.getVertexBufferObjectManager());
        //spriteEscudo =cargarSprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,regionEscudo);
       // final Sprite Escudo = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
              //  regionEscudoActivado, actividadJuego.getVertexBufferObjectManager());
        Sprite btnEscudo = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionEscudo, actividadJuego.getVertexBufferObjectManager());
        Sprite btnEscudo1 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionEscudo1, actividadJuego.getVertexBufferObjectManager());
        Sprite btnEscudo2 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionEscudo2, actividadJuego.getVertexBufferObjectManager());
        Sprite btnEscudo3 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionEscudo3, actividadJuego.getVertexBufferObjectManager());
        Sprite btnEscudo4 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionEscudo4, actividadJuego.getVertexBufferObjectManager());
        Sprite btnEscudo5 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionEscudo5, actividadJuego.getVertexBufferObjectManager());

        attachChild(btnEscudo);
        if (puntos==20 || puntos==220 || puntos==420){
            //Sprite btnEscudo1 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                   // regionEscudo1, actividadJuego.getVertexBufferObjectManager());
            detachChild(btnEscudo);
            attachChild(btnEscudo1);
        } else if (puntos==40 || puntos==240 || puntos==440){

            //Sprite btnEscudo2 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                    //regionEscudo2, actividadJuego.getVertexBufferObjectManager());
            detachChild(btnEscudo1);
            attachChild(btnEscudo2);
        } else if (puntos==60 || puntos==260 || puntos==460){
            //Sprite btnEscudo3 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                //    regionEscudo3, actividadJuego.getVertexBufferObjectManager());
            detachChild(btnEscudo2);
            attachChild(btnEscudo3);
        } else if (puntos==80 || puntos==280 || puntos==480){
            //Sprite btnEscudo4 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
              //      regionEscudo4, actividadJuego.getVertexBufferObjectManager());
            detachChild(btnEscudo3);
            attachChild(btnEscudo4);
        } else if (puntos==100 || puntos==300 || puntos==500 ){
            //Sprite btnEscudo5 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
              //      regionEscudo5, actividadJuego.getVertexBufferObjectManager());
            detachChild(btnEscudo4);
            attachChild(btnEscudo5);
        } else if (puntos>=120  ){
            colEscudo=3;
            final Sprite btnEscudo6 = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                    regionEscudo6, actividadJuego.getVertexBufferObjectManager()){
                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                    if (pSceneTouchEvent.isActionDown()) {
                        //escudo = new Sprite(spriteTierra.getX()-500, spriteTierra.getY()-100,
                              //  regionEscudoActivado, actividadJuego.getVertexBufferObjectManager());
                        sonidoEscudo.play();
                        //attachChild(escudo);
                        yolo++;

                        return true;
                    }

                    return true;  //return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                }

            };

            attachChild(btnEscudo6);
            if(yolo==1){
                detachChild(btnEscudo6);
            }
            registerTouchArea(btnEscudo6);

        }

        if(yolo==1){
           detachChild(btnEscudo);
            attachChild(btnEscudo);
        }

        /*
        Sprite btnEscudo = new Sprite(regionBtnPausa.getWidth()/2+600, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionEscudo, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown()) {
                    Sprite Escudo = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
                            regionEscudoActivado, actividadJuego.getVertexBufferObjectManager());
                    attachChild(Escudo);
                    Escudo.setAlpha(0.4f);
                    return true;
                }
                return true;  //return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }

        };
        attachChild(btnEscudo);
        registerTouchArea(btnEscudo);*/
    }

    private void agregarPausa() {
        // Crea el botón de PAUSA y lo agrega a la escena
        Sprite btnPausa = new Sprite(regionBtnPausa.getWidth()/2+800, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()/2-640,
                regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        //btnPausa.setAlpha(0.4f);
        attachChild(btnPausa);
        registerTouchArea(btnPausa);

        // Crear la escena de PAUSA, pero NO lo agrega a la escena
        escenaPausa = new CameraScene(actividadJuego.camara);
        //ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
        Sprite fondoPausa = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
                regionPausa);
        escenaPausa.attachChild(fondoPausa);

        // Crea el botón de PAUSA y lo agrega a la escena

        Sprite btnMain = new Sprite((ControlJuego.ANCHO_CAMARA /2)+45,(ControlJuego.ALTO_CAMARA/2)+320,
                regionBtnMainMenu, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                // setIgnoreUpdate(false);

                if (pSceneTouchEvent.isActionDown()) {
                    //juegoCorriendo=true;
                    //admEscenas.crearEscenaMenu();
                    onBackKeyPressed();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(btnMain);
        escenaPausa.registerTouchArea(btnMain);
        escenaPausa.setBackgroundEnabled(false);


        Sprite btnContinuar = new Sprite(ControlJuego.ANCHO_CAMARA /2,(ControlJuego.ALTO_CAMARA/2)-320,
                regionBtnPlay, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
               // setIgnoreUpdate(false);

                if (pSceneTouchEvent.isActionDown()) {
                    //juegoCorriendo=true;
                    pausarJuego();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        //btnContinuar.setAlpha(0.4f);
        escenaPausa.attachChild(btnContinuar);
        escenaPausa.registerTouchArea(btnContinuar);

        escenaPausa.setBackgroundEnabled(false);
    }


    private void pausarJuego() {
        if (juegoCorriendo) {
            setIgnoreUpdate(true);
            setChildScene(escenaPausa, false, true, true);
            juegoCorriendo = false;
            //getChildScene().setIgnoreUpdate(true);
        } else {
            clearChildScene();
            setIgnoreUpdate(false);
            juegoCorriendo = true;

        }
    }

    @Override
    public void liberarRecursos() {
        int cont=0;
        regionFondo.getTexture().unload();
        regionFin.getTexture().unload();
        Galaxias.getTexture().unload();
        Tierra.getTexture().unload();
        Marco.getTexture().unload();
        regionVerde.getTexture().unload();
        regionRojo.getTexture().unload();
        regionAmarillo.getTexture().unload();
        regionAzul.getTexture().unload();
        Vida_uno.getTexture().unload();
        Vida_dos.getTexture().unload();
        Vida_tres.getTexture().unload();
        regionBtnPausa.getTexture().unload();
        regionEscudo.getTexture().unload();
        regionEscudo1.getTexture().unload();
        regionEscudo2.getTexture().unload();
        regionEscudo3.getTexture().unload();
        regionEscudo4.getTexture().unload();
        regionEscudo5.getTexture().unload();
        regionEscudo6.getTexture().unload();
        regionEscudo=null;
        regionEscudo1=null;
        regionEscudo2=null;
        regionEscudo3=null;
        regionEscudo4=null;
        regionEscudo5=null;
        regionEscudo6=null;
        regionBtnPausa = null;
        Vida_uno = null;
        Vida_dos = null;
        Vida_tres = null;
        regionFondo = null;
        Galaxias = null;
        Tierra = null;
        Marco = null;
        regionVerde = null;
        regionRojo = null;
        regionAmarillo = null;
        regionAzul = null;
        regionFin=null;
    }

    private void sonidoGrito(){
        sonidoTierra.play();
    }



    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {

        int vida = 3;

        final int contA = 0;

        Random ran = new Random();

        agregarEscudo();


        //float posX = Tierra.getTextureX();
        //float posY = Tierra.getTextureY();
        super.onManagedUpdate(pSecondsElapsed);
        actualizarPuntos();
        int cont = 0;
        int z;
        int contEscudo=0;


        /*if( puntos<100) {

            listaP.add(regionAmarillo);
            listaP.add(regionVerde);
            listaP.add(regionAzul);
            listaP.add(regionRojo);
        } else if (puntos>=100)
        {
            listaP.add(regionTAmarillo);
            listaP.add(regionTVerde);
            listaP.add(regionTAzul);
            listaP.add(regionTRojo);
        } else if (puntos>=200){
            listaP.add(regionPAmarillo);
            listaP.add(regionPVerde);
            listaP.add(regionPAzul);
            listaP.add(regionPRojo);
        }
*/


        if (puntos == 100 ||  puntos == 105 || puntos == 110 || puntos==115 || puntos == 500 || puntos == 505 || puntos == 510 || puntos == 515 ||
                puntos==900 || puntos == 905 || puntos == 910) {
            if (enemigoA==null) {
                Sprite spriteBossAzul = new Sprite(0,
                        100, regionBossAzul, actividadJuego.getVertexBufferObjectManager()) {

                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                        this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                        if (pSceneTouchEvent.isActionDown()) {
                            contAzul++;
                            //return true;
                        }


                        return true; //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                    }
                };

                registerTouchArea(spriteBossAzul);
                setTouchAreaBindingOnActionDownEnabled(true);
                //attachChild(spriteBossAzul);
                enemigoA = new Enemigos(spriteBossAzul);
                attachChild(enemigoA.getSpriteEnemigo());
            }
            if ( enemigoA!=null) {
                //Log.i("onManagedUpdate","Moviendo...");
                enemigoA.mover(spriteTierra.getX(), spriteTierra.getY());
                if (enemigoA.getSpriteEnemigo().collidesWith(spriteTierra)){
                    Log.i("xx","Colision Boss-Tierra");
                    vida-=2;
                    detachChild(enemigoA.getSpriteEnemigo());
                }
                if (contAzul == 3) {
                    contAzul=0;
                    puntos += 20;
                    detachChild(enemigoA.getSpriteEnemigo());
                    enemigoA = null;
                }
            }
        } else if (puntos==200 || puntos==205 || puntos==210 || puntos==215 || puntos==600 || puntos==605 || puntos==610 || puntos==615 ||
                puntos==1000 || puntos==1005 || puntos==1010 ){
            if (enemigoV==null) {
                Sprite spriteBossVerde = new Sprite(0,
                        100, regionBossVerde, actividadJuego.getVertexBufferObjectManager()) {

                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                        this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                        if (pSceneTouchEvent.isActionDown()) {
                            contVerde++;
                            //return true;
                        }


                        return true; //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                    }
                };

                registerTouchArea(spriteBossVerde);
                setTouchAreaBindingOnActionDownEnabled(true);
                //attachChild(spriteBossAzul);
                enemigoV = new Enemigos(spriteBossVerde);
                attachChild(enemigoV.getSpriteEnemigo());
            }
            if ( enemigoV!=null) {
                //Log.i("onManagedUpdate","Moviendo...");
                enemigoV.mover(spriteTierra.getX(), spriteTierra.getY());
                if(enemigoV.getSpriteEnemigo().collidesWith(spriteTierra)){
                    vida-=2;
                    detachChild(enemigoV.getSpriteEnemigo());
                }
                if (contVerde == 3) {
                    contVerde=0;
                    puntos += 20;
                    detachChild(enemigoV.getSpriteEnemigo());
                    enemigoV = null;
                }
            }

        } else if (puntos==300 || puntos==305 || puntos==310 || puntos==700 || puntos==705 || puntos==710 || puntos==1100 || puntos==1105 ||
                puntos==110){
            if (enemigoAm==null) {
                Sprite spriteBossAmarillo = new Sprite(0,
                        100, regionBossAmarillo, actividadJuego.getVertexBufferObjectManager()) {

                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                        this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                        if (pSceneTouchEvent.isActionDown()) {
                            contAmarillo++;
                            //return true;
                        }


                        return true; //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                    }
                };

                registerTouchArea(spriteBossAmarillo);
                setTouchAreaBindingOnActionDownEnabled(true);
                //attachChild(spriteBossAzul);
                enemigoAm = new Enemigos(spriteBossAmarillo);
                attachChild(enemigoAm.getSpriteEnemigo());
            }
            if ( enemigoAm!=null) {
                //Log.i("onManagedUpdate","Moviendo...");
                enemigoAm.mover(spriteTierra.getX(), spriteTierra.getY());
                if (enemigoAm.getSpriteEnemigo().collidesWith(spriteTierra)){
                    vida-=2;
                    detachChild(enemigoAm.getSpriteEnemigo());
                }
                if (contAmarillo == 3) {
                    contAmarillo=0;
                    puntos += 20;
                    detachChild(enemigoAm.getSpriteEnemigo());
                    enemigoAm = null;
                }
            }




        } else if(puntos==400 ||puntos==405||puntos==410||puntos==800||puntos==805||puntos==810||puntos==1200||puntos==1205||puntos==1210){

            if (enemigoR==null) {
                Sprite spriteBossRojo = new Sprite(0,
                        100, regionBossRojo, actividadJuego.getVertexBufferObjectManager()) {

                    @Override
                    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                        this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                        if (pSceneTouchEvent.isActionDown()) {
                            contRojo++;
                            //return true;
                        }


                        return true; //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                    }
                };

                registerTouchArea(spriteBossRojo);
                setTouchAreaBindingOnActionDownEnabled(true);
                //attachChild(spriteBossAzul);
                enemigoR= new Enemigos(spriteBossRojo);
                attachChild(enemigoR.getSpriteEnemigo());
            }
            if ( enemigoR!=null) {
                //Log.i("onManagedUpdate","Moviendo...");
                enemigoR.mover(spriteTierra.getX(), spriteTierra.getY());
                if(enemigoR.getSpriteEnemigo().collidesWith(spriteTierra)){
                    vida-=2;
                    detachChild(enemigoR.getSpriteEnemigo());
                }
                if (contRojo == 3) {
                    contRojo=0;
                    puntos += 20;
                    detachChild(enemigoR.getSpriteEnemigo());
                    enemigoR = null;
                }
            }









        }


       // if (juegoCorriendo = true) {

            tiempoEnemigos += pSecondsElapsed;        //	Acumular	tiempo

            if (tiempoEnemigos > LIMITE_TIEMPO) {    //	Se	cumplió	el	tiempo
               if(pSecondsElapsed%5==0){
                    LIMITE_TIEMPO-=0.5f;
                }
               tiempoEnemigos = 0;

                Log.i("xx","puntos = "+puntos);
                if (puntos<100){
                    listaU = listaP;
                    z = ran.nextInt(listaU.size()) + 0;
                    Log.i("xx","usando lista1");
                    Log.i("zzzz= ","z"+ z);
                    Log.i("xx","puntos: "+puntos);
                } else if(puntos>=100 && puntos<200){
                    listaU=listaP1;
                    z = ran.nextInt(listaU.size()) + 0;
                    Log.i("xx","usando lista2");
                    Log.i("zzzz= ","z"+ z);
                } else if (puntos>=200){
                    listaU=listaP2;
                    z = ran.nextInt(listaU.size()) + 0;
                    Log.i("xx","usando lista3");
                    Log.i("zzzz= ","z"+ z);
                }

                z = ran.nextInt(listaU.size()) + 0;


              //  int r1 = ran.nextInt(ControlJuego.ANCHO_CAMARA - 0 + 1) + 0;
                float r1 = (float)Math.random()*440 + 180; //GalaxiaRojo.getWidth() + (regionAzul.getWidth()/2) + ran.nextFloat()*(ControlJuego.ALTO_CAMARA + 1) - ( GalaxiaRojo.getWidth() +  GalaxiaAmarillo.getWidth()+ regionAzul.getWidth()/2 );
                float r2 = (float)Math.random()*440 + 180;//GalaxiaRojo.getWidth() + (regionAzul.getWidth()/2)+ ran.nextFloat()*(ControlJuego.ALTO_CAMARA + 1) - ( GalaxiaRojo.getWidth() +  GalaxiaAmarillo.getWidth()+ regionAzul.getWidth()/2 );
                float r3 = (float)Math.random()*900 + 180;//GalaxiaRojo.getWidth() + (regionAzul.getWidth()/2)+ ran.nextFloat()*(ControlJuego.ANCHO_CAMARA + 1) - ( GalaxiaRojo.getWidth() +  GalaxiaAmarillo.getWidth() + regionAzul.getWidth()/2);
                float r4 =(float)Math.random()*900 + 180;// GalaxiaRojo.getWidth() + (regionAzul.getWidth()/2) + ran.nextFloat()*(ControlJuego.ANCHO_CAMARA + 1) - ( GalaxiaRojo.getWidth() +  GalaxiaAmarillo.getWidth()+ regionAzul.getWidth()/2 );
                //int r2 = ran.nextInt(ControlJuego.ANCHO_CAMARA - 0 + 1) + 0;
                //int r3 = ran.nextInt(ControlJuego.ALTO_CAMARA - 0 + 1) + 0;
                //int r4 = ran.nextInt(ControlJuego.ALTO_CAMARA - 0 + 1) + 0;
                int var = ran.nextInt(4 - 1 + 1) + 1;
            /*final Sprite spriteEnemigo	=	cargarSprite(ControlJuego.ANCHO_CAMARA+listaP.get(z).getWidth(),
                    (float)(Math.random()*ControlJuego.ALTO_CAMARA-listaP.get(z).getHeight())	+
                            listaP.get(z).getHeight(),listaP.get(z)) ;*/

                //  Sprite spriteEnemigo = new Sprite(ControlJuego.ANCHO_CAMARA + listaP.get(z).getWidth(),
                  //      (float) (Math.random() * ControlJuego.ALTO_CAMARA - listaP.get(z).getHeight()) +
                 //               listaP.get(z).getHeight(), listaP.get(z), actividadJuego.getVertexBufferObjectManager())



                        Sprite spriteEnemigo = new Sprite(ControlJuego.ANCHO_CAMARA + listaU.get(z).getWidth(),
                                (float) (Math.random() * ControlJuego.ALTO_CAMARA - listaU.get(z).getHeight()) +
                                        listaU.get(z).getHeight(), listaU.get(z), actividadJuego.getVertexBufferObjectManager()) {

                            @Override
                            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                                this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);

                                return true; //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                            }
                        };


                        Enemigos nuevoEnemigo = new Enemigos(spriteEnemigo);

                        registerTouchArea(nuevoEnemigo.getSpriteEnemigo());
                        setTouchAreaBindingOnActionDownEnabled(true);
                        attachChild(nuevoEnemigo.getSpriteEnemigo());
                        //nuevoEnemigo.mover(0,10);

                        //Colisiones con escudo

                        /*
                        if(nuevoEnemigo.getSpriteEnemigo().collidesWith(escudo)){
                            Log.i("xx","Colision Escudo");
                           contEscudo++;
                            detachChild(nuevoEnemigo.getSpriteEnemigo());
                            if(contEscudo==3){
                                detachChild(escudo);
                            }
                        }*/

                    listaEnemigos.add(nuevoEnemigo);    //	Lo	AGREGA	a	la	escena


               // boolean mGrabbed = false;


                /*if (puntos>100 ) {
                     Sprite spriteBossAzul = new Sprite(ControlJuego.ANCHO_CAMARA + listaP.get(z).getWidth(),
                            (float) (Math.random() * ControlJuego.ALTO_CAMARA - listaP.get(z).getHeight()) +
                                    listaP.get(z).getHeight(), regionBossAzul, actividadJuego.getVertexBufferObjectManager()) {

                        @Override
                        public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                            this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                            if (pSceneTouchEvent.isActionDown()) {
                                contAzul++;
                                return true;
                            }


                            return true; //super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                        }
                    };

                    registerTouchArea(spriteBossAzul);
                    setTouchAreaBindingOnActionDownEnabled(true);
                    attachChild(spriteBossAzul);
                    Enemigos enemigoA = new Enemigos(spriteBossAzul);
                    enemigoA.mover(spriteTierra.getX(), spriteTierra.getY());
                    if (contAzul == 3) {
                        detachChild(spriteBossAzul);
                    }
                }*/
                //	Lo	AGREGA	a	la	lista

                // Agrega al enemigo a posiciones aleatorias generadas

                if (puntos>=0) {
                    if (var == 1) {
                        // nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, -300);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                    } else if (var == 2) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                    } else if (var == 3) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                    } else if (var == 4) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                    }


                } /*else if (puntos >100) {
                    if (var == 1) {
                        // nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, -300);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                    } else if (var == 2) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                    } else if (var == 3) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                    } else if (var == 4) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                    }*/

                /*} else if (puntos>200){
                    if (var == 1) {
                        // nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, -300);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                    } else if (var == 2) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                    } else if (var == 3) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(0, r1);
                    } else if (var == 4) {
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r4, ControlJuego.ALTO_CAMARA);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(r3, 0);
                        nuevoEnemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA, r2);
                    }
                }*/
            }

       // if ( juegoCorriendo==true) {
            for (int i = listaEnemigos.size() - 1; i >= 0; i--) {
                Enemigos enemigo = listaEnemigos.get(i);

                //enemigo.getSpriteEnemigo().setPosition(ControlJuego.ANCHO_CAMARA,100);
                // MoveModifier mod1=new MoveModifier(50,0,ControlJuego.ALTO_CAMARA/2,50,ControlJuego.ANCHO_CAMARA/2);
                //enemigo.getSpriteEnemigo().registerEntityModifier(mod1);
                enemigo.mover(spriteTierra.getX(), spriteTierra.getY());
                if(escudo==null){
                    Log.i("xx","No hay escudo");
                }
               /* if(escudo.collidesWith(enemigo.getSpriteEnemigo())){
                    Log.i("xx","Colision Escudo");
                    contEscudo++;
                    detachChild(enemigo.getSpriteEnemigo());
                    listaEnemigos.remove(enemigo);
                    if(contEscudo==3){
                        detachChild(escudo);
                    }
                }*/
                /*
                if (enemigo.getSpriteEnemigo().getX() < -enemigo.getSpriteEnemigo().getWidth()) {
                    detachChild(enemigo.getSpriteEnemigo());        //	Lo	ELIMINA	de	la	escena
                    listaEnemigos.remove(enemigo);                                                                    //	Lo	ELIMINA	de	la	lista
                }*/
                //	Revisa	el	choque	del	personaje	con	el	enemigo

                if (enemigo.getSpriteEnemigo().getTextureRegion() == regionVerde || enemigo.getSpriteEnemigo().getTextureRegion() == regionTVerde || enemigo.getSpriteEnemigo().getTextureRegion() == regionPVerde  ) {
                    if (spriteGalaxiaVerde.collidesWith(enemigo.getSpriteEnemigo())) {
                        puntos += 5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);



                    } else if (spriteGalaxiaAzul.collidesWith(enemigo.getSpriteEnemigo()) || spriteGalaxiaRojo.collidesWith(enemigo.getSpriteEnemigo())
                    ||spriteGalaxiaAmarillo.collidesWith(enemigo.getSpriteEnemigo())){
                        puntos-=5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);

                    }
                }

                if (enemigo.getSpriteEnemigo().getTextureRegion() == regionAzul || enemigo.getSpriteEnemigo().getTextureRegion() == regionTAzul || enemigo.getSpriteEnemigo().getTextureRegion() == regionPAzul) {
                    if (spriteGalaxiaAzul.collidesWith(enemigo.getSpriteEnemigo())) {
                        puntos += 5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);

                    } else if (enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaVerde) || enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaRojo)
                            ||enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaAmarillo)){
                        puntos-=5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);

                    }
                }

                if (enemigo.getSpriteEnemigo().getTextureRegion() == regionAmarillo || enemigo.getSpriteEnemigo().getTextureRegion() == regionTAmarillo || enemigo.getSpriteEnemigo().getTextureRegion() == regionPAmarillo) {
                    if (spriteGalaxiaAmarillo.collidesWith(enemigo.getSpriteEnemigo())) {
                        puntos += 5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);

                    } else if (enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaAzul) || enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaRojo)
                            ||enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaVerde)){
                        puntos-=5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);

                    }
                }

                if (enemigo.getSpriteEnemigo().getTextureRegion() == regionRojo || enemigo.getSpriteEnemigo().getTextureRegion() == regionTRojo || enemigo.getSpriteEnemigo().getTextureRegion() == regionPRojo) {
                    if (spriteGalaxiaRojo.collidesWith(enemigo.getSpriteEnemigo())) {
                        puntos += 5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    } else if (enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaAzul) || enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaVerde)
                            ||enemigo.getSpriteEnemigo().collidesWith(spriteGalaxiaAmarillo)){
                        puntos-=5;
                        detachChild(enemigo.getSpriteEnemigo());
                        listaEnemigos.remove(enemigo);
                    }
                }

                int col = 0;
                if (spriteTierra.collidesWith(enemigo.getSpriteEnemigo())) {
                    //sonidoGrito();
                    //escudo.getTextureRegion().getTextureX()==spriteTierra.getX()-500
                    if(colEscudo==3){


                        detachChild(enemigo.getSpriteEnemigo());
                        col++;
                        colEscudo--;
                        colEscudo=0;
                        Log.i("xx", " Colision al escudo__@");
                        if(col==3){
                            colEscudo=0;
                            detachChild(escudo);
                        }
                    } else if (col==3 || colEscudo==0) {
                        colEscudo=0;

                        detachChild(enemigo.getSpriteEnemigo());
                        //listaEnemigos.remove(enemigo);
                        vida--;
                        if (vida == 2) {
                            detachChild(spriteVida3);
                        } else if (vida == 1) {
                            detachChild(spriteVida2);
                        } else if (vida == 0) {
                            detachChild(spriteVida1);
                            juegoCorriendo = false;
                            //sonidoGameOver.play();
                            //actividadJuego.reproducirMusica("EARTHKEEPER-MASTER/Sonidos/GameOver.wav", true);
                            Sprite spriteFin = new Sprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2,
                                    regionFin, actividadJuego.getVertexBufferObjectManager()) {
                                @Override
                                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                                    if (pSceneTouchEvent.isActionUp()) {
                                        onBackKeyPressed();
                                    }
                                    return true; // return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
                                }

                            };

                            txtPuntos.setText(" " + puntos);
                            registerTouchArea(spriteFin);
                            attachChild(spriteFin);
                            txtPuntos = new Text(ControlJuego.ANCHO_CAMARA / 2, (ControlJuego.ALTO_CAMARA / 2) - 320,
                                    fontMonster, " " + puntos, actividadJuego.getVertexBufferObjectManager());
                            attachChild(txtPuntos);
                        }

                    }
                }


            }

    }


   // @Override
    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0, 0, mCamera.getWidth(), mCamera.getHeight());

        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(mCamera.getWidth(), mCamera.getHeight()), this.mCamera);
        engineOptions.getTouchOptions().setNeedsMultiTouch(true);



        return engineOptions;
    }










}
