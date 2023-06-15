package mx.com.ediel.mv.punkapp.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.com.ediel.mv.punkapp.R
import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.databinding.BeerItemBinding

class MainScreenAdapter: RecyclerView.Adapter<MainScreenAdapter.BeerItemViewHolder>() {
    var onClickItemListener: ((Beer) -> Unit)? = null
    var onClickFavImgListener: ((Int, Beer) -> Unit)? = null
    private var beers: MutableList<Beer> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BeerItemBinding.inflate(inflater, parent, false)
        return BeerItemViewHolder(binding)
    }

    override fun getItemCount() = beers.size

    override fun onBindViewHolder(holder: MainScreenAdapter.BeerItemViewHolder, position: Int) {
        holder.bind(beers[position], position)
    }

    fun updateData(list: List<Beer>, position: Int? = null){
        beers.clear()
        beers.addAll(list)
        if(position != null){
            notifyItemChanged(position)
        }else{
            notifyDataSetChanged()
        }
    }


    inner class BeerItemViewHolder(val binding: BeerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(beer: Beer, position: Int) {
            binding.textNameBeer.text = beer.name
            binding.textTaglineBeer.text = beer.tagline
            Picasso.get().load(beer.imageUrl).into(binding.imgBeer)
            Log.v(MainFragment.TAG, "BeerItemViewHolder -  ${beer.isFavorite}")
            if(beer.isFavorite){
                Picasso.get().load(R.drawable.heart_filled).into(binding.imgFavorite)
            }else{
                Picasso.get().load(R.drawable.heart_outline).into(binding.imgFavorite)
            }
            binding.root.setOnClickListener {
                onClickItemListener?.invoke(beer)
            }
            binding.imgFavorite.setOnClickListener {
                onClickFavImgListener?.invoke(position, beer)
            }
        }
    }
}