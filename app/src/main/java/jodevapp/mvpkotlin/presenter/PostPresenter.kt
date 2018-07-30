package jodevapp.mvpkotlin.main.presenter

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import jodevapp.mvpkotlin.main.base.BaseMVPPresenterImpl
import jodevapp.mvpkotlin.main.manager.ApiManager
import jodevapp.mvpkotlin.main.model.Post
import jodevapp.mvpkotlin.main.ui.PostView

/**
 * Created by Jodevapp on 7/30/2018.
 */
class PostPresenter : BaseMVPPresenterImpl<PostView.View>(), PostView.Presenter {
    override fun onRequestPost() {
        mView?.getContext().let {
            mView?.showProgress()
            ApiManager.requestPost()
                    .doOnError {
                        mView?.onViewError(it.toString())
                    }
                    .subscribe(object : Observer<List<Post>> {
                        override fun onError(e: Throwable) {
                            mView?.onViewError(e.toString())
                        }

                        override fun onNext(post: List<Post>) {
                            mView?.onViewSuccess(post)
                        }

                        override fun onComplete() {
                            // on complete
                            mView?.hideProgress()
                        }

                        override fun onSubscribe(d: Disposable) {
                            // Disposable
                        }
                    })
        }

    }

}