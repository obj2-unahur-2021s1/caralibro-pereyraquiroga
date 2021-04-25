package ar.edu.unahur.obj2.caralibro

abstract class Permiso {

    open abstract fun puedeVerLaPublicacion(): Boolean

}
class Publico():Permiso(){
    override fun puedeVerLaPublicacion()= true
}

class soloAmigos(val listaDeAmigos: List<Usuario>):Permiso(){
    override fun puedeVerLaPublicacion(usuario: Usuario): Boolean {
        return listaDeAmigos.contains(usuario)
    }
}