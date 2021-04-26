package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import ar.edu.unahur.obj2.caralibro.Excluidos as Permiso

class UsuarioTest : DescribeSpec({
  val Roberto= Usuario()
  val Silvana= Usuario()
  val Sofia= Usuario()
  val Estefania=Usuario()
  val Claudia = Usuario()
  val Leticia = Usuario()
  val Publico =Publico()

  describe("Caralibro") {

    val fotoEnCuzco = Foto(768, 1024)
    val videoSD = Video(tipoDeVideo = "SD", 2)
    val videoHD720p = Video(tipoDeVideo = "HD720p", 2)
    val videoHD1080P = Video(tipoDeVideo = "HD1080p", 2)
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
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
        val juana = Usuario()

        juana.agregarPublicacion(fotoEnCuzco, Publico)
        juana.agregarPublicacion(saludoCumpleanios, Publico)
        juana.agregarPublicacion(videoSD, Publico) // agregue videoSD
        juana.agregarPublicacion((videoHD1080P), Publico)
        juana.agregarPublicacion((videoHD720p), Publico)
        juana.espacioDePublicaciones().shouldBe(550568) // calcular agregando video
      }

      describe("Poder darle me gusta a una publicacion y ver cuantas veces fue votada") {
        val Claudia = Usuario()
        Roberto.agregarPublicacion(saludoCumpleanios, Publico)
        Claudia.agregarPublicacion(fotoEnCuzco, Publico)
        Silvana.agregarPublicacion(videoSD, Publico)
        saludoCumpleanios.usuariosQueDieronMeGusta(Silvana)
        fotoEnCuzco.usuariosQueDieronMeGusta(Roberto)
        fotoEnCuzco.usuariosQueDieronMeGusta(Roberto)
        fotoEnCuzco.usuariosQueDieronMeGusta(Silvana)
        saludoCumpleanios.cuantasVecesFueVotada().shouldBe(1)
        videoSD.cuantasVecesFueVotada().shouldBe(0)
        fotoEnCuzco.cuantasVecesFueVotada().shouldBe(2)
      }

      describe("Un usuario es mas amistoso que otro") {

        Roberto.agregarAmigos(Sofia)
        Silvana.agregarAmigos(Claudia)
        Silvana.agregarAmigos(Estefania)
        Roberto.esMasAmistosoQue(Silvana).shouldBe(false)
      }

      describe("Un usuario puede ver una cierta publicacion.") {
        val Claudia = Usuario()

        Claudia.agregarAmigos(Roberto)

        Claudia.agregarPublicacion(videoSD, Publico)

        Claudia.puedeVerLaPublicacion(videoSD, Roberto).shouldBe(true)
      }

      describe("El usuario Silvana puede ver la publicacion si es para solo amigos de Roberto") {
        val soloAmigos = SoloAmigos()
        Roberto.agregarAmigos(Silvana)
        Roberto.agregarPublicacion(fotoEnCuzco, soloAmigos)
        Roberto.puedeVerLaPublicacion(fotoEnCuzco, Silvana).shouldBe(true)
      }

      describe("Silvana puede ver la publicacion de Estefania si es publica con lista de excluidos") {
        val listaExcluidos= Permiso()
        val Estefania=Usuario()
        Estefania.agregarExcluidos(Silvana)
        Estefania.agregarPublicacion(videoHD1080P,listaExcluidos)
        Estefania.puedeVerLaPublicacion(videoHD1080P,Silvana).shouldBe(false)
      }
      describe("Leticia puede ver a publicacion de Silvana si es publica con lista de excluidos"){
        val listaExcluidos = Permiso()
        val Claudia=Usuario()
        Claudia.agregarExcluidos(Roberto)

        Claudia.agregarExcluidos(Estefania)
        Claudia.agregarPublicacion(videoHD1080P,listaExcluidos)
        Claudia.puedeVerLaPublicacion(videoHD1080P,Leticia).shouldBe(true) /// ARREGLAR PARA LA POSIBILIDAD DE TRUE
      }


    }
  }

  })
