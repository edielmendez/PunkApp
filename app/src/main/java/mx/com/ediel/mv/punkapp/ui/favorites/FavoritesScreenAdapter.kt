package mx.com.ediel.mv.punkapp.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.ediel.mv.punkapp.data.models.Favorite
import mx.com.ediel.mv.punkapp.databinding.BeerItemBinding
import mx.com.ediel.mv.punkapp.databinding.FavoriteItemBinding

class FavoritesScreenAdapter: RecyclerView.Adapter<FavoritesScreenAdapter.FavoriteItemViewHolder>() {
    var onClickItemListener: ((Favorite) -> Unit)? = null
    private val favorites: MutableList<Favorite> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavoriteItemBinding.inflate(inflater, parent, false)
        return FavoriteItemViewHolder(binding)
    }

    override fun getItemCount() = favorites.size

    override fun onBindViewHolder(holder: FavoritesScreenAdapter.FavoriteItemViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    fun updateData(list: List<Favorite>){
        favorites.clear()
        favorites.addAll(list)
        notifyDataSetChanged()
    }


    inner class FavoriteItemViewHolder(val binding: FavoriteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(favorite: Favorite) {
            binding.textName.text = favorite.name
            binding.textTagline.text = favorite.tagline
            binding.root.setOnClickListener {
                onClickItemListener?.invoke(favorite)
            }
        }
    }
}