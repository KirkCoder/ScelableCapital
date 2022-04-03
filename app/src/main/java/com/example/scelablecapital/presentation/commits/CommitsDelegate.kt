package com.example.scelablecapital.presentation.commits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scelablecapital.R
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.commit_item.view.*

class CommitsDelegate : AdapterDelegate<List<CommitsByMonthPresentation>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.commit_item, parent, false)
        )
    }

    override fun isForViewType(items: List<CommitsByMonthPresentation>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: List<CommitsByMonthPresentation>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position]
        val payload =
            payloads.firstOrNull { it is CommitsByMonthPresentation } as? CommitsByMonthPresentation
        with(holder.itemView) {
            if (payload == null) {
                monthName.text = item.month
                chart.setPercent(item.percent)
            } else {
                chart.changePercent(item.percent)
            }
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer
}