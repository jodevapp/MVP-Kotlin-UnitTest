package jodevapp.mvpkotlin.main.base

open class BaseMVPPresenterImpl<V : BaseMVPView> : BaseMVPPresenter<V> {

    protected var mView: V? = null

    override fun onAttach(view: V) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }
}