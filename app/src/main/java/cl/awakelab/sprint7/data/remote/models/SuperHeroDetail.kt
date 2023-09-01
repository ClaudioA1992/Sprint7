package cl.awakelab.sprint7.data.remote.models

import cl.awakelab.sprint7.data.local.entities.SuperHeroDetailEntity

class SuperHeroDetail(val id: Int, val nombre: String, val origen: String,
                      val imagenLink: String, val poder: String, val año_creacion: Int,
                      val color: String, val traduccion: Boolean) {

    fun transformToDetailEntity(it: SuperHeroDetail): SuperHeroDetailEntity {
        return SuperHeroDetailEntity(id, nombre, origen, imagenLink, poder, año_creacion, color,
            traduccion)
    }
}

