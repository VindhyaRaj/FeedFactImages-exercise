package com.display.feedimages.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.display.feedimages.R
import com.display.feedimages.Utils
import com.display.feedimages.models.FactsModel

import java.util.ArrayList

/**
 *
 * This is an adapter for the [RecyclerView] of Facts it will inflate
 * the row for every fact.
 */

class FeedAdapter(private val facts: ArrayList<FactsModel>?, private val context: Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = facts!![position]
        holder.tvTitle.text = fact.title
        holder.tvTitle.visibility = if (Utils.notEmptyOrNull(fact.title)) View.VISIBLE else View.GONE
        holder.tvDescription.text = fact.description
        holder.tvDescription.visibility = if (Utils.notEmptyOrNull(fact.description)) View.VISIBLE else View.GONE

        holder.ivFactPic.visibility = View.VISIBLE

        val picasso = Picasso.with(context)
                .load(fact.imageUrl).networkPolicy(NetworkPolicy.OFFLINE)
        picasso.into(holder.ivFactPic, object : Callback {
            override fun onSuccess() {
                //Working properly.
            }

            override fun onError() {
                Picasso.with(context).load(fact.imageUrl).into(holder.ivFactPic)
            }
        })

    }

    override fun getItemCount(): Int {
        return facts?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvTitle: TextView
        internal var tvDescription: TextView
        internal var ivFactPic: ImageView

        init {

            tvTitle = itemView.findViewById(R.id.tv_fact_title)
            tvDescription = itemView.findViewById(R.id.tv_fact_description)
            ivFactPic = itemView.findViewById(R.id.iv_fact_image)
        }
    }
}
