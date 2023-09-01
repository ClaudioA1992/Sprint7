package cl.awakelab.sprint7.data.remote

import cl.awakelab.sprint7.data.remote.models.SuperHero
import cl.awakelab.sprint7.data.remote.models.SuperHeroDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroesAPI {

    @GET("")
    suspend fun getAllSuperHeroes(): Response<List<SuperHero>>

    @GET("{id}")
    suspend fun getDetail(@Path("id") id: Int): Response<SuperHeroDetail>

}