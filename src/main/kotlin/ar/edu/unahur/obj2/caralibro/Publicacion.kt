package ar.edu.unahur.obj2.caralibro

import java.sql.Time
import kotlin.math.ceil

abstract class Publicacion {
  abstract fun espacioQueOcupa(): Int
  abstract fun cuantasVecesFueVotada(): Int
  abstract fun aumentarContador(): Int


}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val contador =0
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
  override fun aumentarContador() = contador+1
  override fun cuantasVecesFueVotada() = contador

}

class Texto(val contenido: String) : Publicacion() {
  val contador=0
  override fun espacioQueOcupa() = contenido.length
  override fun aumentarContador() = contador+1
  override fun cuantasVecesFueVotada() = contador
}

 class Video(val tipoDeVideo : String, val tiempo :Int): Publicacion() {
   val contador=0
   override fun espacioQueOcupa() =
     if (tipoDeVideo == "SD") {
       tiempo
     }
    else if (tipoDeVideo == "HD720p"){
      tiempo*3
   }
     else {
       (tiempo*3)*2
   }
   override fun aumentarContador() = contador+1
   override fun cuantasVecesFueVotada() = contador

}







