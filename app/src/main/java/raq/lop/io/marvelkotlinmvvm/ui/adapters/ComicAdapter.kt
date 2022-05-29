package raq.lop.io.marvelkotlinmvvm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import raq.lop.io.marvelkotlinmvvm.R
import raq.lop.io.marvelkotlinmvvm.data.model.comic.ComicModel
import raq.lop.io.marvelkotlinmvvm.databinding.ItemComicBinding
import raq.lop.io.marvelkotlinmvvm.util.limitText

class ComicAdapter : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    inner class ComicViewHolder(val binding: ItemComicBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ComicModel>(){
        override fun areItemsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.id ==newItem.id && oldItem.title == newItem.title && oldItem.description == newItem.description &&
                    oldItem.thumbnail.path == newItem.thumbnail.path && oldItem.thumbnail.extension == newItem.thumbnail.extension
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)

    var comics : List<ComicModel>
    get() = differ.currentList
    set(value) = differ.submitList(value)




        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {

            return ComicViewHolder(
                ItemComicBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        holder.binding.apply {
            tvNameComic.text=comic.title
            tvDescriptionComic.text=comic.description
            Glide.with(holder.itemView.context)
                .load(comic.thumbnail.path + "." + comic.thumbnail.extension)
                .into(imgComic)
        }

    }

    override fun getItemCount(): Int = comics.size
}