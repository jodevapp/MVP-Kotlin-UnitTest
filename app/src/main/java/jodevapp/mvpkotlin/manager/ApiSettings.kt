package jodevapp.mvpkotlin.manager

import jodevapp.mvpkotlin.BuildConfig.DEBUG

/**
 * Created by Jodevapp on 7/30/2018.
 */

object ApiSettings {

    // example if you have dev and live environment
    var devHost = "https://jsonplaceholder.typicode.com/"
    var liveHost = "https://jsonplaceholder.typicode.com/"

    private fun getHost(): String {
        return if (DEBUG) {
            devHost
        } else {
            liveHost
        }
    }

    fun endPointPost(): String {
        return getHost() + "posts"
    }
}