package ar.edu.unahur.obj2.caralibro

abstract class Permiso {

    abstract fun puedeVerLaPublicacion(usuario: Usuario) : Boolean

}
class Publico() :Permiso(){

    override fun puedeVerLaPublicacion(usuario: Usuario): Boolean=true
}

class SoloAmigos(val listaDeAmigos: List<Usuario>):Permiso(){
    override fun puedeVerLaPublicacion(usuario: Usuario): Boolean {
        return listaDeAmigos.contains(usuario)
    }

}
