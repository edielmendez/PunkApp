package mx.com.ediel.mv.punkapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.ediel.mv.punkapp.data.models.Ingredient
import mx.com.ediel.mv.punkapp.databinding.IngredientItemBinding

class IngredientItemAdapter(
    private val items: List<String>
): RecyclerView.Adapter<IngredientItemAdapter.IngredientItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IngredientItemBinding.inflate(inflater, parent, false)
        return IngredientItemViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: IngredientItemAdapter.IngredientItemViewHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class IngredientItemViewHolder(
        private val binding: IngredientItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String) {
            binding.textName.text = item
        }
    }
}