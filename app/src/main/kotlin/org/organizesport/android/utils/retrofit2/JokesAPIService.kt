package org.organizesport.android.utils.retrofit2

import io.reactivex.Single
import org.organizesport.android.entity.JokeModel
import retrofit2.http.GET

/**
 * This interface defines a Retrofit 2 service.
 *
 * @author pablol.
 * @since 1.0
 */
interface JokesAPIService {
    @GET("jokes/")
    fun loadSports(): Single<JokeModel.Result>
}
