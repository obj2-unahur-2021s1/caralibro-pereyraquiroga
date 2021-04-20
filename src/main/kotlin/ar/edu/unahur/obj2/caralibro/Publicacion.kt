package ar.edu.unahur.obj2.caralibro

import java.sql.Time
import kotlin.math.ceil

abstract class Publicacion {
  abstract fun espacioQueOcupa(): Int
}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
}

class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}

 class Video(val tipoDeVideo : String, val tiempo :Int): Publicacion() {
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


}







