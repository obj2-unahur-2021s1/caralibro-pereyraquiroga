package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  var listaDeAmigos= mutableListOf<Usuario>()


  fun agregarAmigos(usuario:Usuario){
    listaDeAmigos.add(usuario)
  }
  fun agregarPublicacion(publicacion: Publicacion, permiso: Texto) {
    publicaciones.add(publicacion)
  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun cantidadDeAmigos() = listaDeAmigos.size


  fun esMasAmistosoQue(usuario:Usuario) =
    if (this.cantidadDeAmigos() > usuario.cantidadDeAmigos() ) {
       true
    }

    else{
      false
    }

  fun puedeVerLaPublicacion(publicacion: Publicacion,permiso: Texto)=
    if(permiso){
      true
    }
    else{
      false
    }



}






