package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  var listaDeAmigos = mutableListOf<Usuario>()
  var listaDeExcluidos= mutableListOf<Usuario>()
  var listaPermitidos= mutableListOf<Usuario>()


  fun agregarPermitidos(usuario:Usuario){
    listaPermitidos.add(usuario)
  }
  fun agregarAmigos(usuario: Usuario) {
    listaDeAmigos.add(usuario)
  }

  fun agregarExcluidos(usuario:Usuario){
    listaDeExcluidos.add(usuario)
  }

  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)

  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun cantidadDeAmigos() = listaDeAmigos.size


  fun esMasAmistosoQue(usuario: Usuario) = this.cantidadDeAmigos() > usuario.cantidadDeAmigos()


  fun puedeVerLaPublicacion(publicacion: Publicacion,usuario:Usuario)=
    ((publicaciones.contains(publicacion) ) && ( (this == usuario) || (this.listaDeAmigos.contains(usuario)) || (this.listaPermitidos.contains(usuario)) ))

  fun cambiarPermiso(publicacion: Publicacion,permiso:Permiso)=publicacion.cambiaPermiso(permiso)



  fun mejoresAmigos()= (listaDeAmigos + listaPermitidos).toList().toSet()




}







