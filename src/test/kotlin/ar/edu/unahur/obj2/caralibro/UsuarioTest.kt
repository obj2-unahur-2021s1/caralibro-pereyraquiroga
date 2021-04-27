package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe



class UsuarioTest : DescribeSpec({
  val juana = Usuario()
  val roberto= Usuario()
  val silvana= Usuario()
  val sofia= Usuario()
  val estefania=Usuario()
  val claudia = Usuario()
  val leticia = Usuario()
  val Publico =Publico()
  val SoloAmigos=SoloAmigos()
  val Permitidos= Permitidos()
  val Excluidos= Excluidos()

  describe("Caralibro") {
    //CREO PUBLICACIONES
    val fotoEnCuzco = Foto(768, 1024,Publico)
    val videoSD = Video( "SD", 2,Publico)
    val videoHD720p = Video( "HD720p", 2,Publico)
    val videoHD1080P = Video( "HD1080p", 2,Publico)
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz",Publico)
    describe("Una publicacion") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
        }
      }

      describe("de tipo texto") {
        it("ocupa tantos bytes como su longitud") {
          saludoCumpleanios.espacioQueOcupa().shouldBe(45)
        }
      }

      describe("tipo video") {
        it("segun la calidad es el tamaño que ocupa videoSD") {
          videoSD.espacioQueOcupa().shouldBe(2)
        }
        it("segun la calidad es el tamaño que ocupa HD 720p") {
          videoHD720p.espacioQueOcupa().shouldBe(6)
        }
        it("segun la calidad es el tamaño que ocupa HD 1080p") {
          videoHD1080P.espacioQueOcupa().shouldBe(12)
        }

      }
    }

    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
       //juana agrega una publicacion
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.agregarPublicacion(videoSD) // agregue videoSD
        juana.agregarPublicacion(videoHD1080P)
        juana.agregarPublicacion(videoHD720p)
        //PREGUNTA QUE ESPACIO OCUPA LAs PUBLICACIONES DE JUANA
        juana.espacioDePublicaciones().shouldBe(550568) // calcular agregando video
      }

      describe("Poder darle me gusta a una publicacion y ver cuantas veces fue votada") {
       //AGREGA PUBLICACIONES
        roberto.agregarPublicacion(saludoCumpleanios)
        claudia.agregarPublicacion(fotoEnCuzco)
        silvana.agregarPublicacion(videoSD)
        //votaciones
        saludoCumpleanios.usuariosQueDieronMeGusta(silvana)
        fotoEnCuzco.usuariosQueDieronMeGusta(roberto)
        fotoEnCuzco.usuariosQueDieronMeGusta(roberto)
        fotoEnCuzco.usuariosQueDieronMeGusta(silvana)
        //Cuantas veces la votaron a cada una
        saludoCumpleanios.cuantasVecesFueVotada().shouldBe(1)
        videoSD.cuantasVecesFueVotada().shouldBe(0)
        fotoEnCuzco.cuantasVecesFueVotada().shouldBe(2)
      }

      describe("Un usuario es mas amistoso que otro") {
        //Agrega amigos
        roberto.agregarAmigos(sofia)
        silvana.agregarAmigos(claudia)
        silvana.agregarAmigos(estefania)
        //Quien es mas amistoso que quien
        roberto.esMasAmistosoQue(silvana).shouldBe(false)
      }

      describe("Un usuario puede ver una cierta publicacion.") {
        claudia.agregarAmigos(roberto) // Agrega amigo

        claudia.agregarPublicacion(videoSD) //Agrega pubicacion

        claudia.puedeVerLaPublicacion(videoSD, roberto).shouldBe(true) // PREGUNTA SI PUEDE VER LA PUBLICACION EL USUARIO
      }

      describe("El usuario Silvana puede ver la publicacion si es para solo amigos de Roberto") {

        val fotoEnCuzcoCumple=Foto(2,3,SoloAmigos)
        //Agrega amigo
        roberto.agregarAmigos(silvana)
        //Agrega publicacion
        roberto.agregarPublicacion(fotoEnCuzcoCumple)
        //Pregunta si puede ver la publicaicon
        roberto.puedeVerLaPublicacion(fotoEnCuzcoCumple,silvana).shouldBe(true)
      }

      describe("Silvana puede ver la publicacion de Estefania si es publica con lista de excluidos") {
        val videoHD1080P=Video("HD1080P",2,Excluidos)
        //Agrega a los excluidos de sus publicaciones
        estefania.agregarExcluidos(silvana)
        //Crea publicacion
        estefania.agregarPublicacion(videoHD1080P)
        //Pregunta si el usuario puede ver la publicacion
        estefania.puedeVerLaPublicacion(videoHD1080P,silvana).shouldBe(false)
      }

      describe("Leticia puede ver a publicacion de Silvana si es publica con lista de excluidos"){
        //Agrega excluidos
        claudia.agregarExcluidos(roberto)
        claudia.agregarExcluidos(estefania)
        //Agrega amigos
        claudia.agregarAmigos(leticia)
        //Agrega la publicacion
        claudia.agregarPublicacion(videoHD1080P)
        //Pregunta si puede ver la publicacion el usuario
        claudia.puedeVerLaPublicacion(videoHD1080P,leticia).shouldBe(true)
      }

      describe("Puede ver publicacion si esta en la lista de Permitidos"){

        val foto=Foto(2,2,Permitidos)
        //Agrega los permitidos que pueden ver su publicacion
        estefania.agregarPermitidos(claudia)
        estefania.agregarPermitidos(sofia)
        //Agrega la publicacion
        estefania.agregarPublicacion(foto)
        //Pregunta si el usuario puede ver la publicacion
        estefania.puedeVerLaPublicacion(foto,sofia).shouldBe(true)
      }

      describe("No puede ver publicacion si no esta en la lista de Permitidos"){
        val foto=Foto(2,2,Permitidos)
        //Agrega permitidos
        silvana.agregarPermitidos(leticia)
        silvana.agregarPermitidos(roberto)
        //Crea la publicacion
        silvana.agregarPublicacion(foto)
        //Pregunta si el usuario puede ver la publicacion
        silvana.puedeVerLaPublicacion(foto,sofia).shouldBe(false)
      }

      describe("Puede cambiar permiso de una publicacion"){
        val foto=Foto(2,2,Permitidos)
        //Cambiar permiso
        silvana.cambiarPermiso(foto,SoloAmigos)


      }

      describe("mejores amigos de un usuario"){
        //Agrega amigos 
        silvana.agregarAmigos(roberto)
        silvana.agregarPermitidos(claudia)
        silvana.agregarPermitidos(claudia) // repetido
        silvana.agregarPermitidos(roberto)//repetido
        silvana.mejoresAmigos().shouldContainExactlyInAnyOrder(roberto,claudia)

      }

    }
  }

})




