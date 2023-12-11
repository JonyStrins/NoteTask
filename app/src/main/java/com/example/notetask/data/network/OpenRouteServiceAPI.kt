package com.example.notetask.data.network

import com.example.notetask.models.GeoJSONDirection
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.openrouteservice.org"

private const val API_KEY =
    "AIzaSyAk8J9pxj3NwYfhunyCK2Bm-UluPBihigo"
//"6b3ce3597x51110001cf624819e6024a736b47666fe215764a8bdba7" //obtener esrkey registrandose


private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json{
        isLenient = true
        ignoreUnknownKeys = true}
        .asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface IOpenRouteServiceAPI {
    @GET("/v2/directions/{profile}")
    suspend fun directions_get( @Path("profile") profile: String,
                                @Query("api_key") apikey : String = API_KEY,
                                @Query("start") start : String ,
                                @Query("end") end : String ): GeoJSONDirection

    @GET("/v2/directions/driving-car?api_key=xxxxxx&start=8.681495,49.41461&end=8.687872,49.420318")
    suspend  fun directions_get( ): GeoJSONDirection
}

object OpenRouteServiceApi {
    val retrofitService : IOpenRouteServiceAPI by lazy {
        retrofit.create(IOpenRouteServiceAPI::class.java)
    }
}

