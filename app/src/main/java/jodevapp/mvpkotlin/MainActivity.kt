package jodevapp.mvpkotlin.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import jodevapp.mvpkotlin.R
import jodevapp.mvpkotlin.main.base.BaseMPVActivity
import jodevapp.mvpkotlin.main.model.Post
import jodevapp.mvpkotlin.main.presenter.PostPresenter
import jodevapp.mvpkotlin.main.ui.PostView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.general_error.*

/**
 * Created by Jodevapp on 7/30/2018.
 */

class MainActivity : BaseMPVActivity<PostView.View, PostView.Presenter>(), PostView.View {

    override var mPresenter: PostView.Presenter = PostPresenter()
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adapterMain: AdapterPost? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.onRequestPost()

        btnError.setOnClickListener{
            mPresenter.onRequestPost()
        }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        lytError.visibility = View.GONE
    }

    override fun onViewSuccess(post: List<Post>?) {
        rvPost.visibility = View.VISIBLE
        linearLayoutManager = LinearLayoutManager(this)
        rvPost.layoutManager = linearLayoutManager
        adapterMain = post?.let { AdapterPost(this, it) }
        rvPost?.adapter = adapterMain
        adapterMain?.notifyDataSetChanged()
    }

    override fun onViewError(message: String?) {
        lytError.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}
