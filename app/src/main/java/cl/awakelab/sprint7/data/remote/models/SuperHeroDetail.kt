package cl.awakelab.sprint7.data.remote.models

import cl.awakelab.sprint7.data.local.entities.SuperHeroDetailEntity

class SuperHeroDetail(val id: Int, val name: String, val origin: String,
                      val imageUrl: String, val power: String, val creationYear: Int,
                      val color: String, val translation: Boolean) {

    fun transformToDetailEntity(it: SuperHeroDetail): SuperHeroDetailEntity {
        return SuperHeroDetailEntity(id, name, origin, imageUrl, power, creationYear, color,
            translation)
    }
}