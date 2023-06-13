package mx.com.ediel.mv.punkapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.ediel.mv.punkapp.data.models.Ingredient
import mx.com.ediel.mv.punkapp.databinding.IngredientViewItemBinding

class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {
    private val ingredients: MutableList<Ingredient> = mutableListOf()

    private val rvPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IngredientViewItemBinding.inflate(inflater, parent, false)
        return IngredientViewHolder(binding, rvPool)
    }

    override fun getItemCount() = ingredients.size

    override fun onBindViewHolder(holder: IngredientAdapter.IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    fun updateData(list: List<Ingredient>){
        ingredients.clear()
        ingredients.addAll(list)
        notifyDataSetChanged()
    }


    inner class IngredientViewHolder(
        private val binding: IngredientViewItemBinding,
        private val rvPool: RecyclerView.RecycledViewPool
    ): RecyclerView.ViewHolder(binding.root){
        private val context = binding.root.context
        private val orientation = RecyclerView.VERTICAL
        private val isReverseLayout = false

        fun bind(ingredient: Ingredient) {
            binding.textName.text = ingredient.title
            initRecycler(ingredient.items)
        }
        private fun initRecycler(items: List<String>){
            val ingredientItemAdapter = IngredientItemAdapter(items)
            val layoutManager = LinearLayoutManager(context, orientation, isReverseLayout)
            layoutManager.initialPrefetchItemCount = items.size
            binding.recyclerViewItems.setRecycledViewPool(rvPool)
            binding.recyclerViewItems.layoutManager = layoutManager
            binding.recyclerViewItems.adapter = ingredientItemAdapter
        }
    }
}