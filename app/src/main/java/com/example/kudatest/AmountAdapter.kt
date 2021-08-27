package com.example.kudatest

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kudatest.common.inflate
import com.example.kudatest.databinding.AmountItemBinding

class AmountAdapter: RecyclerView.Adapter<AmountAdapter.AmountViewHolder>() {

    private var items = ArrayList<AmountEntity>()
    //private var binding  AmountItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AmountAdapter.AmountViewHolder = AmountViewHolder(parent)

    override fun onBindViewHolder(holder: AmountAdapter.AmountViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class AmountViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(
            R.layout.amount_item
        )
    ) {

        fun bind(items: AmountEntity) {

            //itemView.la = items.name


        }
    }
}

data class AmountEntity(
    var amount: String,
)