package mx.itesm.earthkeeper;

/**
 * Created by cooldarp on 10/11/2015.
 */

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

import java.util.ArrayList;


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


    //	Enemigos
    private ArrayList<Enemigos> listaEnemigos;
    private ITextureRegion regionEnemigo;
    //	Tiempo	para	generar	enemigos
    private float tiempoEnemigos =	0;				//	Contador
    private float	LIMITE_TIEMPO	=	2.5f;		//	Cada	2.5s	se	crea






    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoNegro.jpg");
        Galaxias = cargarImagen("Galaxias_Juntas.png");
        Tierra = cargarImagen("Tierra.png");
        Marco = cargarImagen("PantallaMarcoFINAL.png");
        regionEnemigo = cargarImagen("Circular_Verde.png");
    }

    private void crearEnemigos() {
        for (int x = 700; x <= 1200; x += 100) {
            for (int y = 100; y <= 700; y += 100) {
                Sprite nave = cargarSprite(x, y, regionEnemigo);
                attachChild(nave);
                Enemigos enemigo = new Enemigos(nave);
                listaEnemigos.add(enemigo);
            }
        }
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
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        spriteGalaxias = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Galaxias);
        spriteTierra = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Tierra);
        spriteMarco = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, Marco);
        attachChild(spriteFondo);
        attachChild(spriteGalaxias);
        attachChild(spriteTierra);
        attachChild(spriteMarco);
        crearEnemigos();
    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
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

    @Override
    public void liberarRecursos() {
        regionFondo.getTexture().unload();
        Galaxias.getTexture().unload();
        Tierra.getTexture().unload();
        Marco.getTexture().unload();
        regionEnemigo.getTexture().unload();
        regionFondo = null;
        Galaxias = null;
        Tierra = null;
        Marco = null;
        regionEnemigo = null;
    }





    @Override
    protected void onManagedUpdate(float pSecondsElapsed)	{

        super.onManagedUpdate(pSecondsElapsed);
        tiempoEnemigos	+=	pSecondsElapsed;		//	Acumular	tiempo
        if	(tiempoEnemigos>LIMITE_TIEMPO)	{	//	Se	cumplió	el	tiempo
            tiempoEnemigos	=	0;
            Sprite spriteEnemigo	=	cargarSprite(ControlJuego.ANCHO_CAMARA+regionEnemigo.getWidth(),
                    (float)(Math.random()*ControlJuego.ALTO_CAMARA-regionEnemigo.getHeight())	+
                            regionEnemigo.getHeight(),regionEnemigo);

            Enemigos nuevoEnemigo = new Enemigos(spriteEnemigo);
            //nuevoEnemigo.mover(0,10);
            listaEnemigos.add(nuevoEnemigo);	//	Lo	AGREGA	a	la	escena
            attachChild(nuevoEnemigo.getSpriteEnemigo());	//	Lo	AGREGA	a	la	lista
        }






        //	Actualizar	cada	uno	de	los	enemigos	y	ver	si	alguno	ya	salió	de	la	pantalla
        for	(int	i=listaEnemigos.size()-1;	i>=0;	i--)	{
            Enemigos	enemigo	=	listaEnemigos.get(i);
            enemigo.mover(-10, 0);
            if	(enemigo.getSpriteEnemigo().getX()<-enemigo.getSpriteEnemigo().getWidth())	{
                detachChild(enemigo.getSpriteEnemigo());		//	Lo	ELIMINA	de	la	escena
                listaEnemigos.remove(enemigo);																	//	Lo	ELIMINA	de	la	lista
            }
            //	Revisa	el	choque	del	personaje	con	el	enemigo

            if	(spriteTierra.collidesWith(enemigo.getSpriteEnemigo()))	{
                detachChild(enemigo.getSpriteEnemigo());
                //enemigos.remove(enemigo);
                //energia	-=	5; //	Descuenta	puntos,	vidas,	etc.
            }
        }
    }








}
