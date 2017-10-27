package org.organizesport.android.utils.retrofit2

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class defines a 'Retrofit' client used to query any REST API.
 *
 * @author pablol.
 * @since 1.0
 */
class Retrofit2Client {
    // Kotlin companion objects resemble Java static methods (Factory pattern)
    companion object {
        fun create(baseUrl: String): JokesAPIService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()

            return retrofit.create(JokesAPIService::class.java)
        }
    }
}
