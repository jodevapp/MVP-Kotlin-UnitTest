@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package jodevapp.mvpkotlin.manager

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jodevapp.mvpkotlin.BuildConfig
import jodevapp.mvpkotlin.manager.ApiSettings.devHost
import jodevapp.mvpkotlin.manager.ApiSettings.liveHost
import jodevapp.mvpkotlin.model.Post
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Jodevapp on 7/30/2018.
 */

object ApiManager {
    private lateinit var apiService: ApiService
    private var client: OkHttpClient.Builder? = null
    private var interceptor: HttpLoggingInterceptor? = null

    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val gson = GsonBuilder()
                .setLenient()
                .create()

        if (BuildConfig.DEBUG) {

            client = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .apply {
                        networkInterceptors().add(Interceptor { chain ->
                            val original = chain.request()
                            val request = original.newBuilder()
                                    .method(original.method(), original.body())
                                    .build()
                            chain.proceed(request)
                        })
                        addInterceptor(interceptor)
                    }
            client?.interceptors()?.add(interceptor)
            return Retrofit.Builder()
                    .baseUrl(devHost)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client?.build())
                    .build()
        } else {
            client = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
            return Retrofit.Builder().baseUrl(liveHost)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client?.build())
                    .build()
        }


    }

    private fun initServices(retrofit: Retrofit) {
        apiService = retrofit.create(ApiService::class.java)
    }

    fun requestPost(): Observable<List<Post>> =
            apiService.getPostList(ApiSettings.endPointPost())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}
