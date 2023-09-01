package cl.awakelab.sprint7.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import cl.awakelab.sprint7.R
import cl.awakelab.sprint7.data.local.SuperHeroesDao
import cl.awakelab.sprint7.data.local.entities.SuperHeroDetailEntity
import cl.awakelab.sprint7.data.local.entities.SuperHeroEntity
import cl.awakelab.sprint7.data.remote.SuperHeroesAPI

class Repository(private val superHeroesAPI: SuperHeroesAPI,
                 private val superHeroesDAO: SuperHeroesDao) {

    fun getSuperHeroesEntity(): LiveData<List<SuperHeroEntity>> = superHeroesDAO.selectSuperHeroes()

    fun getSuperHeroDetailEntity(id: Int): LiveData<SuperHeroDetailEntity> =
        superHeroesDAO.selectSuperHeroDetail(id);

    suspend fun getSuperHeroes(context: Context) {
        try {
            val response = superHeroesAPI.getAllSuperHeroes()
            if (response.isSuccessful) {
                val response = response.body()!!
                response.let {
                    val superHeroesEntities = it.map { it.transformToEntity(it) }
                    superHeroesDAO.insertSuperHeroes(superHeroesEntities)
                }
            }
        } catch (exception: Exception) {
            Log.e("Get super heroes error", exception.toString())
            Toast.makeText(
                context, R.string.disconnected_superhero,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    suspend fun getSuperHeroDetail(id: Int, context: Context) {
        try {
            val response = superHeroesAPI.getDetail(id)
            if(response.isSuccessful) {
                val response = response.body()!!
                Log.d("Response for detail in repository", response.toString())
                response.let {
                    val superHeroDetailEntity = it.transformToDetailEntity(it)
                    superHeroesDAO.insertSuperHeroDetail(superHeroDetailEntity)
                }
            }
        } catch(exception: Exception) {
            Log.e("Get detail error in repository", exception.toString())
            Toast.makeText(context, R.string.disconnected_detail, Toast.LENGTH_LONG).show()
        }
    }
}