package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  val listaDeAmigos= mutableListOf<Usuario>()

  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)
  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun darleMegusta(publicacion:Publicacion){
    publicacion.aumentarContador()
  }

  fun esMasAmistosoQue(usuario: Usuario) {

  }


}
