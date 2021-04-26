package ar.edu.unahur.obj2.caralibro

abstract class Permiso {
    val listaDeAmigos= mutableListOf<Usuario>()
    var listaDeExcluidos= mutableListOf<Usuario>()

    abstract fun puedeVerLaPublicacion(usuario: Usuario) : Boolean

}
class Publico() :Permiso(){

    override fun puedeVerLaPublicacion(usuario: Usuario): Boolean=true
}

class SoloAmigos():Permiso() {
    override fun puedeVerLaPublicacion(usuario: Usuario): Boolean {
        return listaDeAmigos.contains(usuario)
    }
}
class Excluidos():Permiso(){
    override fun puedeVerLaPublicacion(usuario: Usuario)=
        !listaDeExcluidos.contains(usuario)


}