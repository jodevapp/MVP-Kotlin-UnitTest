package jodevapp.mvpkotlin.manager

import io.reactivex.Observable
import jodevapp.mvpkotlin.model.Post
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by Jodevapp on 7/30/2018.
 */

interface ApiService {

    @GET
    fun getPostList(@Url url: String): Observable<List<Post>>
}