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
    describe("Una publicación") {
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
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.agregarPublicacion(videoSD) // agregue videoSD
        juana.agregarPublicacion((videoHD1080P))
        juana.agregarPublicacion((videoHD720p))
        juana.espacioDePublicaciones().shouldBe(550568) // calcular agregando video
      }

      describe ("Poder darle me gusta a una publicacion y ver cuantas veces fue votada"){
        val Roberto= Usuario()
        val Silvana= Usuario()
        val Claudia= Usuario()
        val Estefania= Usuario()
        val Sofia= Usuario()
        Roberto.agregarPublicacion(saludoCumpleanios)
        Claudia.agregarPublicacion(fotoEnCuzco)
        Silvana.agregarPublicacion(videoSD)
        Silvana.darleMegusta(fotoEnCuzco)
        Sofia.darleMegusta(saludoCumpleanios)
        Claudia.darleMegusta(videoSD)
        Estefania.darleMegusta(saludoCumpleanios)
        saludoCumpleanios.cuantasVecesFueVotada().shouldBe(2)
        videoSD.cuantasVecesFueVotada().shouldBe(1)
        fotoEnCuzco.cuantasVecesFueVotada().shouldBe(1)
      }

      describe("Un usuario es mas amistoso que otro"){
        val Roberto= Usuario()
        val Silvana= Usuario()
        Roberto.esMasAmistosoQue(Silvana).shouldTrue()
      }
    }
  }
})
