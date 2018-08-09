package jodevapp.mvpkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jodevapp.mvpkotlin.model.Post
import kotlinx.android.synthetic.main.adapter_post.view.*

/**
 * Created by Jodevapp on 7/30/2018.
 */

class AdapterPost(private val context: Context?, private val postList: List<Post>)
    : RecyclerView.Adapter<AdapterPost.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_post, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        context?.let {
            holder.bindItem(it, postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: AppCompatTextView = itemView.tvTitle
        private val tvBody: AppCompatTextView = itemView.tvBody

        @SuppressLint("SimpleDateFormat")
        fun bindItem(context: Context?, post: Post) {
            tvTitle.text = post.title
            tvBody.text = post.body
        }
    }
}