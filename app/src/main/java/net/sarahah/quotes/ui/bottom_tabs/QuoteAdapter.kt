package net.sarahah.quotes.ui.bottom_tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_quote.view.*
import net.sarahah.quotes.R
import net.sarahah.quotes.data.model.Quote

class QuoteAdapter : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    inner class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_quote,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Quote) -> Unit)? = null

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = differ.currentList[position]
        holder.itemView.apply {
            var isLiked = false

            authorName.text = quote.author
            tv_quote.text = quote.quote

            likeQuote.apply {
                setOnClickListener {
                    onItemClickListener?.let { it(quote) }
                    if (isLiked){
                        speed = -1f
                        playAnimation()
                        isLiked = false
                    } else {
                        speed = 1f
                        playAnimation()
                        isLiked = true
                    }
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Quote) -> Unit) {
        onItemClickListener = listener
    }

}