package jodevapp.mvpkotlin.base

import jodevapp.mvpkotlin.main.base.BaseMVPView

abstract class BaseMVPPresenter<V : BaseMVPView> {
    protected var mView: V? = null

    fun onAttach(view: V) {
        this.mView = view
    }

    fun onDetach() {
        mView = null
    }
}