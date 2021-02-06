package net.sarahah.quotes.ui.bottom_tabs.likedQuotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_liked_quote.view.*
import kotlinx.android.synthetic.main.item_quote.view.*
import net.sarahah.quotes.R
import net.sarahah.quotes.data.dto.QuoteResponse
import net.sarahah.quotes.data.model.Quote

class LikedQuoteAdapter : RecyclerView.Adapter<LikedQuoteAdapter.LikedQuoteViewHolder>() {

    inner class LikedQuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedQuoteViewHolder {
        return LikedQuoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_liked_quote,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Int?) -> Unit)? = null

    override fun onBindViewHolder(holder: LikedQuoteViewHolder, position: Int) {
        val quote = differ.currentList[position]
        holder.itemView.apply {

            liked_quote_authorName.text = quote.author
            tv_liked_quote.text = quote.quote

            delete_quote.apply {
                setOnClickListener {
                    onItemClickListener?.let { it(quote.id) }
                    speed = 1f
                    playAnimation()
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Int?) -> Unit) {
        onItemClickListener = listener
    }

}