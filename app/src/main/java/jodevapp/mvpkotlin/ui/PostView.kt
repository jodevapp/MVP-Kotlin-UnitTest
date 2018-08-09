package jodevapp.mvpkotlin.ui

import jodevapp.mvpkotlin.base.BaseMVPPresenter
import jodevapp.mvpkotlin.main.base.BaseMVPView
import jodevapp.mvpkotlin.model.Post

/**
 * Created by Jodevapp on 7/30/2018.
 */
interface PostView {


    interface View : BaseMVPView {

        fun showProgress()

        fun onViewSuccess(post: List<Post>?)

        fun onViewError(message: String?)

        fun hideProgress()
    }

    abstract class Presenter : BaseMVPPresenter<View>() {

        /**
         * Request Weather
         *
         * @param query the String
         */
        abstract fun onRequestPost()
    }
}