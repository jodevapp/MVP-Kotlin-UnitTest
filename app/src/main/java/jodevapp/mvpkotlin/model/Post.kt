package jodevapp.mvpkotlin.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Jodevapp on 7/30/2018.
 */
class Post {

    @SerializedName("userId")
    var userId: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("body")
    var body: String? = null

    override fun toString(): String {
        return "Post(userId=$userId, id=$id, title=$title, body=$body)"
    }

}