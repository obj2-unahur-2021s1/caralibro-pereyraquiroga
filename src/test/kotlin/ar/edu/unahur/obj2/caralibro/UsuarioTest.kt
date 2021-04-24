package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoSD = Video(tipoDeVideo = "SD",2)
    val videoHD720p = Video(tipoDeVideo = "HD720p",2)
    val videoHD1080P = Video(tipoDeVideo = "HD1080p",2)
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

      describe("tipo video"){
        it ("segun la calidad es el tamaño que ocupa videoSD"){
            videoSD.espacioQueOcupa().shouldBe(2)
        }
        it ("segun la calidad es el tamaño que ocupa HD 720p"){
          videoHD720p.espacioQueOcupa().shouldBe(6)
        }
        it ("segun la calidad es el tamaño que ocupa HD 1080p"){
          videoHD1080P.espacioQueOcupa().shouldBe(12)
        }

      }
    }

    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        val permiso =Texto("publico")
        juana.agregarPublicacion(fotoEnCuzco, permiso)
        juana.agregarPublicacion(saludoCumpleanios, permiso)
        juana.agregarPublicacion(videoSD, permiso) // agregue videoSD
        juana.agregarPublicacion((videoHD1080P), permiso)
        juana.agregarPublicacion((videoHD720p), permiso)
        juana.espacioDePublicaciones().shouldBe(550568) // calcular agregando video
      }

      describe ("Poder darle me gusta a una publicacion y ver cuantas veces fue votada"){
        val Roberto= Usuario()
        val Silvana= Usuario()
        val Claudia= Usuario()
        val permiso=Texto("publico")
        Roberto.agregarPublicacion(saludoCumpleanios, permiso)
        Claudia.agregarPublicacion(fotoEnCuzco, permiso)
        Silvana.agregarPublicacion(videoSD, permiso)
        saludoCumpleanios.usuariosQueDieronMeGusta(Silvana)
        fotoEnCuzco.usuariosQueDieronMeGusta(Roberto)
        fotoEnCuzco.usuariosQueDieronMeGusta(Roberto)
        fotoEnCuzco.usuariosQueDieronMeGusta(Silvana)
        saludoCumpleanios.cuantasVecesFueVotada().shouldBe(1)
        videoSD.cuantasVecesFueVotada().shouldBe(0)
        fotoEnCuzco.cuantasVecesFueVotada().shouldBe(2)
      }

      describe("Un usuario es mas amistoso que otro"){
        val Roberto= Usuario()
        val Silvana= Usuario()
        val Claudia= Usuario()
        val Estefania= Usuario()
        val Sofia= Usuario()
        Roberto.listaDeAmigos= mutableListOf<Usuario>()
        Roberto.agregarAmigos(Sofia)
        Silvana.listaDeAmigos= mutableListOf<Usuario>()
        Silvana.agregarAmigos(Claudia)
        Silvana.agregarAmigos(Estefania)
        Roberto.esMasAmistosoQue(Silvana).shouldBe(false)
      }

      describe("Saber si un usuario puede ver una publicacion"){
        val Leticia =Usuario()
        val Roberto= Usuario()
        val cumpleaños = Texto("Felicidades Alejandro")
        val permiso=Texto("publico")
        Leticia.agregarPublicacion(cumpleaños,permiso)
        Roberto.puedeVerLaPublicacion(cumpleaños,permiso).shouldBe(true)
      }
    }
  }
})
