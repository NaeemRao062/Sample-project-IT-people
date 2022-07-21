package com.itpeople.sample.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itpeople.sample.mvvm.R


import com.itpeople.sample.mvvm.data.model.ResultsItem
import com.bumptech.glide.Glide
import com.itpeople.sample.mvvm.utilities.DateUtility
import com.itpeople.sample.mvvm.utilities.DateUtility.DATE_FORMAT_ZONE
import com.itpeople.sample.mvvm.utilities.DateUtility.DATE_ONLY_FORMAT
import kotlinx.android.synthetic.main.item_layout.view.*


class MainAdapter(
    private val users: ArrayList<ResultsItem>,
    var onItemClick: ((ResultsItem) -> Unit)? = null

) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(users[adapterPosition])
            }
        }

        fun bind(user: ResultsItem) {
            itemView.textViewUserName.text = "${user.name?.first} ${user.name?.last} "
            itemView.textViewUserEmail.text = user.email
            itemView.textViewDate.text = DateUtility.changeFormat(""+DATE_FORMAT_ZONE,""+DATE_ONLY_FORMAT,user.registered?.date)
            itemView.textViewUserCountry.text = "Country | "+user.location?.country
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.picture?.thumbnail)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<ResultsItem>) {
        users.addAll(list)
        notifyDataSetChanged()
    }
}