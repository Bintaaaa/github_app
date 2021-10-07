package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ItemUserBinding
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.util.OnItemClickCallback
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.util.loadImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    private val listUser = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users: ArrayList<User>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }
    inner class UserHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClick(user)
            }

            binding.apply{
                ivUserItem.loadImage(user.avatarUrl)
                tvUsernameItem.text = user.login
                tvUrlItem.text = user.url
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

}