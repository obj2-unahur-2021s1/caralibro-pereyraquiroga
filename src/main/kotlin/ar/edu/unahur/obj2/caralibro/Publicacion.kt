package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

abstract class Publicacion() {
  abstract fun espacioQueOcupa(): Int
  abstract fun cuantasVecesFueVotada(): Int


  var usuarioDioMegusta= mutableSetOf<Usuario>()

  fun usuariosQueDieronMeGusta(usuarioDeLike: Usuario){

    usuarioDioMegusta.add(usuarioDeLike)
  }

  abstract fun cambiaPermiso(permiso: Permiso)


}



class Foto(val alto: Int, val ancho: Int, var permiso: Permiso) : Publicacion() {

  val factorDeCompresion = 0.7

  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()

  override fun cuantasVecesFueVotada() = usuarioDioMegusta.size

  override fun cambiaPermiso(permiso: Permiso) {this.permiso=permiso}



}

class Texto(val contenido: String, var permiso: Permiso) : Publicacion() {

  override fun espacioQueOcupa() = contenido.length

  override fun cuantasVecesFueVotada() = usuarioDioMegusta.size

  override fun cambiaPermiso(permiso: Permiso) {this.permiso=permiso}
}

class Video(val tipoDeVideo : String, val tiempo :Int, var permiso: Permiso): Publicacion() {

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

  override fun cambiaPermiso(permiso: Permiso) {this.permiso=permiso}

}