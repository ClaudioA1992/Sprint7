package cl.awakelab.sprint7.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {

    companion object {
        private const val URL_BASE = "https://y-mariocanedo.vercel.app/"
        fun getRetrofitInfo(): SuperHeroesAPI {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofit.create(SuperHeroesAPI::class.java)
        }
    }

}