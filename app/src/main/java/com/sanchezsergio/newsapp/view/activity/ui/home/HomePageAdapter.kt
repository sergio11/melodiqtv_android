package com.sanchezsergio.newsapp.view.activity.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sanchezsergio.newsapp.R
import com.sanchezsergio.newsapp.domain.models.Article
import com.sanchezsergio.newsapp.persistence.db.entity.ArticleEntity
import kotlinx.coroutines.Dispatchers

class HomePageAdapter(diffCallback: DiffUtil.ItemCallback<Article>):
	PagingDataAdapter<ArticleEntity, HomeItemViewHolder>(
		diffCallback = diffCallback,
		workerDispatcher = Dispatchers.IO) {
	
	override fun onBindViewHolder(holderHome: HomeItemViewHolder, position: Int) {
		getItem(position)?.let { holderHome.bind(it) }
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
		val itemView = LayoutInflater.from(parent.context)
			.inflate(R.layout.item,parent,false)
		return HomeItemViewHolder(itemView)
	}
}

object ItemComparator: DiffUtil.ItemCallback<Article>() {
	override fun areItemsTheSame(oldArticleEntity: Article, newArticleEntity: Article)
			= oldArticleEntity.id == newArticleEntity.id
	
	override fun areContentsTheSame(oldArticleEntity: Article, newArticleEntity: Article)
			= oldArticleEntity == newArticleEntity
}

class HomeItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
	fun bind(articleEntity: ArticleEntity){
		/*view.txt_item_name.text = articleEntity.name
		view.txt_item_status.text = articleEntity.status
		view.imv_item_status
				.setImageDrawable(view.context
						.getDrawable(getItemStatusDrawable(
								articleEntity.status)))
		view.txt_item_species.text = articleEntity.species
		view.txt_item_dash.text = getItemDashString(
				status = articleEntity.status,
				species = articleEntity.species)
		Glide.with(view)
				.load(articleEntity.image)
				.into(view.imv_item)*/
	}
}