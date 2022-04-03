package fr.isen.corre.livraisou

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.corre.livraisou.databinding.CardCellBinding

class CardAdapter(
    private val shops: List<Shop>,
    private val clickListener: RecyclerView
) : RecyclerView.Adapter<CardViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindHook(shops[position])
    }



    override fun getItemCount(): Int = shops.size

}


