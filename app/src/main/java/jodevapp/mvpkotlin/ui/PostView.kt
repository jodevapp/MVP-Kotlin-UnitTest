package jodevapp.mvpkotlin.main.ui

import jodevapp.mvpkotlin.main.base.BaseMVPPresenter
import jodevapp.mvpkotlin.main.base.BaseMVPView
import jodevapp.mvpkotlin.main.model.Post

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

    interface Presenter : BaseMVPPresenter<View> {

        /**
         * Request Weather
         *
         * @param query the String
         */
        fun onRequestPost()
    }
}