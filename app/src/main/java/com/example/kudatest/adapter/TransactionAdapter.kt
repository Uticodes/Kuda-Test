package com.example.kudatest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kudatest.common.inflate
import com.example.kudatest.databinding.AccountItemBinding

class TransactionAdapter(private val transactionList: List<TransactionEntity>): RecyclerView.Adapter<TransactionAdapter.AmountViewHolder>() {
    private lateinit var context: Context

    inner class AmountViewHolder(val binding: AccountItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.AmountViewHolder {
        val binding = AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return AmountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionAdapter.AmountViewHolder, position: Int) {
        with(holder){
            with(transactionList[position]){
                binding.moneyIn.text = this.title
                binding.amount.text = this.amount

                if (this.type.contains(context.resources.getString(R.string.money_in))){
                    binding.amount.setTextColor(ContextCompat.getColor(context, R.color.green))
                } else {
                    binding.imgGreen.setImageResource(R.drawable.ic_dash)
                    binding.amount.setTextColor(ContextCompat.getColor(context, R.color.red))
                }
            }
        }
    }

    override fun getItemCount(): Int = transactionList.size

}

data class TransactionEntity(
    var title: String,
    var amount: String,
    var type: String,
)
