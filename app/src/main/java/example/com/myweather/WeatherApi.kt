package example.com.myweather

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object WeatherApi {

    const val BASE_URL = "https://www.metaweather.com"
    const val IMAGE_URL = "$BASE_URL/static/img/weather/png/"
    lateinit var retrofit: Retrofit

    fun getApi(): WeatherApiInterface {
        // 로깅을 위한 interceptor 생성
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // OkhttpClient 초기화
        val client: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
        // Gson 초기화
        val gson = GsonBuilder().setLenient().create()

        // Retrofit2 초기화
        retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return retrofit.create(WeatherApiInterface::class.java)
    }

    // 날씨 정보와 관련된 API 인터페이스
    interface WeatherApiInterface {

        @GET("/api/location/search/")
        fun getLocationSearch(@Query("query") query: String = "se"): Call<List<LocationSearch>>

        @GET("/api/location/{woeid}/")
        fun getLocation(@Path("woeid") woeid: String): Call<Location>

    }

    // 커스텀 가능한 추상 Callback 클래스
    abstract class Callback<T> : retrofit2.Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponseImpl(call, response.body())
        }

        // 매번 response.body()를 호출하지 않게 추상 메서드를 추가
        protected abstract fun onResponseImpl(call: Call<T>, body: T?)

        override fun onFailure(call: Call<T>, t: Throwable) {
            t.printStackTrace()
        }
    }


}