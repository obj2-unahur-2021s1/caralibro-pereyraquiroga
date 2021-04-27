package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
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

    val fotoEnCuzco = Foto(768, 1024,Publico)
    val videoSD = Video( "SD", 2,Publico)
    val videoHD720p = Video( "HD720p", 2,Publico)
    val videoHD1080P = Video( "HD1080p", 2,Publico)
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz",Publico)
    describe("Una publicaci?n") {
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
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.agregarPublicacion(videoSD) // agregue videoSD
        juana.agregarPublicacion(videoHD1080P)
        juana.agregarPublicacion(videoHD720p)
        juana.espacioDePublicaciones().shouldBe(550568) // calcular agregando video
      }

      describe("Poder darle me gusta a una publicacion y ver cuantas veces fue votada") {
        roberto.agregarPublicacion(saludoCumpleanios)
        claudia.agregarPublicacion(fotoEnCuzco)
        silvana.agregarPublicacion(videoSD)
        saludoCumpleanios.usuariosQueDieronMeGusta(silvana)
        fotoEnCuzco.usuariosQueDieronMeGusta(roberto)
        fotoEnCuzco.usuariosQueDieronMeGusta(roberto)
        fotoEnCuzco.usuariosQueDieronMeGusta(silvana)
        saludoCumpleanios.cuantasVecesFueVotada().shouldBe(1)
        videoSD.cuantasVecesFueVotada().shouldBe(0)
        fotoEnCuzco.cuantasVecesFueVotada().shouldBe(2)
      }

      describe("Un usuario es mas amistoso que otro") {

        roberto.agregarAmigos(sofia)
        silvana.agregarAmigos(claudia)
        silvana.agregarAmigos(estefania)
        roberto.esMasAmistosoQue(silvana).shouldBe(false)
      }

      describe("Un usuario puede ver una cierta publicacion.") {
        claudia.agregarAmigos(roberto)

        claudia.agregarPublicacion(videoSD)

        claudia.puedeVerLaPublicacion(videoSD, roberto).shouldBe(true)
      }

      describe("El usuario Silvana puede ver la publicacion si es para solo amigos de Roberto") {
        val fotoEnCuzcoCumple=Foto(2,3,SoloAmigos)
        roberto.agregarAmigos(silvana)
        roberto.agregarPublicacion(fotoEnCuzcoCumple)
        roberto.puedeVerLaPublicacion(fotoEnCuzcoCumple,silvana).shouldBe(true)
      }

      describe("Silvana puede ver la publicacion de Estefania si es publica con lista de excluidos") {
        val videoHD1080P=Video("DH1080P",2,Excluidos)
        estefania.agregarExcluidos(silvana)
        estefania.agregarPublicacion(videoHD1080P)
        estefania.puedeVerLaPublicacion(videoHD1080P,silvana).shouldBe(false)
      }

      describe("Leticia puede ver a publicacion de Silvana si es publica con lista de excluidos"){
        claudia.agregarExcluidos(roberto)
        claudia.agregarExcluidos(estefania)
        claudia.agregarAmigos(leticia)
        claudia.agregarPublicacion(videoHD1080P)
        claudia.puedeVerLaPublicacion(videoHD1080P,leticia).shouldBe(true)
      }

      describe("Puede ver publicacion si esta en la lista de Permitidos"){
        val foto=Foto(2,2,Permitidos)
        estefania.agregarPermitidos(claudia)
        estefania.agregarPermitidos(sofia)
        estefania.agregarPublicacion(foto)
        estefania.puedeVerLaPublicacion(foto,sofia).shouldBe(true)
      }

      describe("No puede ver publicacion si no esta en la lista de Permitidos"){
        val foto=Foto(2,2,Permitidos)
        silvana.agregarPermitidos(leticia)
        silvana.agregarPermitidos(roberto)
        silvana.agregarPublicacion(foto)
        silvana.puedeVerLaPublicacion(foto,sofia).shouldBe(false)
      }

      describe("Puede cambiar permiso de una publicacion"){
        val foto=Foto(2,2,Permitidos)
        silvana.cambiarPermiso(foto,SoloAmigos)


      }

      //describe("mejores amigos de un usuario"){
        //Silvana.mejoresAmigos()
      //}

    }
  }

})
