package raq.lop.io.marvelkotlinmvvm.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import raq.lop.io.marvelkotlinmvvm.R
import raq.lop.io.marvelkotlinmvvm.data.model.character.CharacterModel
import raq.lop.io.marvelkotlinmvvm.databinding.ItemCharacterBinding
import raq.lop.io.marvelkotlinmvvm.util.limitText

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<CharacterModel>(){
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id ==newItem.id && oldItem.name == newItem.name && oldItem.description == newItem.description &&
                    oldItem.thumbnail.path == newItem.thumbnail.path && oldItem.thumbnail.extension == newItem.thumbnail.extension
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)

    var characters : List<CharacterModel>
    get() = differ.currentList
    set(value) = differ.submitList(value)




        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

            return CharacterViewHolder(
                ItemCharacterBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        val character = characters[position]
        holder.binding.apply {
            tvNameCharacter.text=character.name
            if(character.description.isNullOrEmpty()) tvDescriptionCharacter.text=holder.itemView.context.getString(R.string.textDescriptionEmpty)
            else tvDescriptionCharacter.text = character.description.limitText(120)
            Glide.with(holder.itemView.context)
                .load(character.thumbnail.path + "." + character.thumbnail.extension)
                .into(imgCharacter)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(character)
            }
        }

    }

    private var onItemClickListener: ((CharacterModel) -> Unit)? = null

    fun setOnClickListener(listener: (CharacterModel) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = characters.size
}