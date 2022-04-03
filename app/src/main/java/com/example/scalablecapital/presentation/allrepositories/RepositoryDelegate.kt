package com.example.scalablecapital.presentation.allrepositories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scalablecapital.R
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryDelegate(
    private val onSelectRepository: (GitHubRepositoryPresentation) -> Unit
) : AdapterDelegate<List<GitHubRepositoryPresentation>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        )
    }

    override fun isForViewType(items: List<GitHubRepositoryPresentation>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: List<GitHubRepositoryPresentation>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position]
        with(holder.itemView) {
            idTextView.text = item.id
            nameTextView.text = item.presentationName
            setOnClickListener {
                onSelectRepository(item)
            }
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer
}