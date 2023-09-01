package cl.awakelab.sprint7.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cl.awakelab.sprint7.data.Repository
import cl.awakelab.sprint7.data.local.SuperHeroesDatabase
import cl.awakelab.sprint7.data.remote.ApiRetrofit
import kotlinx.coroutines.launch

class SuperHeroesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository

    fun superHeroesLiveData() = repository.getSuperHeroesEntity()

    fun detailLiveData(id: Int) = repository.getSuperHeroDetailEntity(id)

    init {
        val api = ApiRetrofit.getRetrofitInfo()
        val superHeroesDatabase = SuperHeroesDatabase.getDatabase(application).getSuperHeroesDao()
        repository = Repository(api, superHeroesDatabase)
    }

    fun getAllSuperheroes(context: Context) = viewModelScope.launch {
        repository.getSuperHeroes(context)
    }

    fun getSuperheroDetail(id: Int, context: Context) = viewModelScope.launch {
        repository.getSuperHeroDetail(id, context)
    }

}