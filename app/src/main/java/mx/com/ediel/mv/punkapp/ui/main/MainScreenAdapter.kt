package mx.com.ediel.mv.punkapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.databinding.BeerItemBinding

class MainScreenAdapter: RecyclerView.Adapter<MainScreenAdapter.BeerItemViewHolder>() {
    var onClickItemListener: ((Beer) -> Unit)? = null
    private val tours: MutableList<Beer> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BeerItemBinding.inflate(inflater, parent, false)
        return BeerItemViewHolder(binding)
    }

    override fun getItemCount() = tours.size

    override fun onBindViewHolder(holder: MainScreenAdapter.BeerItemViewHolder, position: Int) {
        holder.bind(tours[position])
    }

    fun updateData(list: List<Beer>){
        tours.clear()
        tours.addAll(list)
        notifyDataSetChanged()
    }


    inner class BeerItemViewHolder(val binding: BeerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(beer: Beer) {
            binding.textNameBeer.text = beer.name
            binding.textTaglineBeer.text = beer.tagline
            binding.root.setOnClickListener {
                onClickItemListener?.invoke(beer)
            }
        }
    }
}