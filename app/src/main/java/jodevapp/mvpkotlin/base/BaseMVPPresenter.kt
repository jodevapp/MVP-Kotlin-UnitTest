package jodevapp.mvpkotlin.main.base

interface BaseMVPPresenter<in T : BaseMVPView> {
    fun onAttach(view: T)

    fun onDetach()
}