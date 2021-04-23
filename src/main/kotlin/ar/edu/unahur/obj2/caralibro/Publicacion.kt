package ar.edu.unahur.obj2.caralibro

import java.sql.Time
import kotlin.math.ceil

abstract class Publicacion() {
  abstract fun espacioQueOcupa(): Int
  abstract fun cuantasVecesFueVotada(): Int
  //abstract fun aumentarContador()
  fun permisos(tipoDePermiso:String){
    if (tipoDePermiso=="publico"){

    }
  }

  var usuarioDioMegusta= mutableListOf<Usuario>()

  fun darMegusta(usuarioDeLike: Usuario){
    if(usuarioDioMegusta.contains(usuarioDeLike)){
      throw error("Este usuario ya le dio Like")
    }
    usuarioDioMegusta.add(usuarioDeLike)
  }

}


class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  var contador =0
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()

  override fun cuantasVecesFueVotada() = usuarioDioMegusta.size


}

class Texto(val contenido: String) : Publicacion() {
  var contador=0
  override fun espacioQueOcupa() = contenido.length

  override fun cuantasVecesFueVotada() = usuarioDioMegusta.size
}

 class Video(val tipoDeVideo : String, val tiempo :Int): Publicacion() {
   var contador=0
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

   override fun cuantasVecesFueVotada() = usuarioDioMegusta.size

}







