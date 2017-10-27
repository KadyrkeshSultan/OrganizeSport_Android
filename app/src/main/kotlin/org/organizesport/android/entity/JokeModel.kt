package org.organizesport.android.entity

import com.google.gson.annotations.SerializedName

/**
 * This class refers to the 'Entity' layer (VIPER architecture) to be used across the app. It
 * specifically relates to the data model fetched from the jokes REST API, through Retrofit2 and
 * the Gson library.
 *
 * @author pablol.
 * @since 1.0
 */
object JokeModel {
    data class Result(@SerializedName("value") val jokes: Joke)
    data class Joke(val id: Int, @SerializedName("joke") val text: String)
}
