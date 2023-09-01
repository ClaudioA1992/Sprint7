package cl.awakelab.sprint7.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.awakelab.sprint7.data.local.entities.SuperHeroDetailEntity
import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity

@Dao
interface SuperHeroesDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertSuperHero(hero: SuperHeroEntity)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertSuperHeroes(heroes: List<SuperHeroEntity>)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertSuperHeroDetail(superHeroDetail: SuperHeroDetailEntity)

    @Query("select * from superhero order by name asc")
    fun selectSuperHeroes(): LiveData<List<SuperHeroEntity>>

    @Query("select * from superherodetail where id = :id")
    fun selectSuperHeroDetail(id: Int): LiveData<SuperHeroDetailEntity>


}