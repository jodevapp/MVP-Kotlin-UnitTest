package jodevapp.mvpkotlin.main.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Jodevapp on 7/30/2018.
 */
class Post {

    @SerializedName("userId")
    @Expose
    var userId: Int? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("body")
    @Expose
    var body: String? = null

    override fun toString(): String {
        return "Post(userId=$userId, id=$id, title=$title, body=$body)"
    }

}