package jodevapp.mvpkotlin.presenter

import jodevapp.mvpkotlin.manager.ApiManager
import jodevapp.mvpkotlin.ui.PostView

/**
 * Created by Jodevapp on 7/30/2018.
 */
class PostPresenter : PostView.Presenter() {
    override fun onRequestPost() {
        mView?.let { view ->
            view.showProgress()
            ApiManager.requestPost()
                    .doOnError { view.onViewError(it.toString()) }
                    .subscribe({ view.onViewSuccess(it) }, { view.onViewError(it.toString()) }, { view.hideProgress() })
        }

    }

}