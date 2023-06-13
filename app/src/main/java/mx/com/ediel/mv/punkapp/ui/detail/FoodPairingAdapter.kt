package mx.com.ediel.mv.punkapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.ediel.mv.punkapp.databinding.FoodItemBinding

class FoodPairingAdapter: RecyclerView.Adapter<FoodPairingAdapter.FoodViewHolder>() {
    var onClickItemListener: ((String) -> Unit)? = null
    private val favorites: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodItemBinding.inflate(inflater, parent, false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount() = favorites.size

    override fun onBindViewHolder(holder: FoodPairingAdapter.FoodViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    fun updateData(list: List<String>){
        favorites.clear()
        favorites.addAll(list)
        notifyDataSetChanged()
    }


    inner class FoodViewHolder(val binding: FoodItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String) {
            binding.textName.text = "- $item"
        }
    }
}