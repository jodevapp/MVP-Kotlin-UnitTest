package jodevapp.mvpkotlin.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jodevapp.mvpkotlin.main.base.BaseMVPView

abstract class BaseMPVActivity<V : BaseMVPView, T : BaseMVPPresenter<V>>
    : AppCompatActivity(), BaseMVPView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.onAttach(this as V)
    }

    override fun getContext(): Context = this

    protected abstract var mPresenter: T

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }
}