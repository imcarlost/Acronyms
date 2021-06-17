package dev.carlos.shortform.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.carlos.core.extensions.autoNotify
import dev.carlos.core.extensions.capitalize
import dev.carlos.shortform.R
import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.shortform.databinding.ItemLongformCardBinding
import kotlin.properties.Delegates

class LongformAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items by Delegates.observable(emptyList<ShortformModel.LongformModel>()) { _, oldList, newList ->
        autoNotify(oldList, newList) { old, new -> old.value == new.value }
        notifyDataSetChanged()
    }

    var onItemClick: (ShortformModel.LongformModel) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LongformViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_longform_card, parent, false),
            onItemClick
        )

    fun getItem(position: Int) = items[position]

    fun addAll(list: List<ShortformModel.LongformModel>) {
        items = list
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) =
        when (viewholder) {
            is LongformViewHolder -> viewholder.bind(items[position])
            else -> throw NoWhenBranchMatchedException("Undefined viewholder")
        }
}

class LongformViewHolder(
    private val view: View,
    private val onItemClick: (ShortformModel.LongformModel) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemLongformCardBinding.bind(view)

    fun bind(longform: ShortformModel.LongformModel) = with(view) {
        binding.itemLongformName.text = longform.value.capitalize()
        binding.itemLongformSince.text = view.resources.getString(R.string.item_longform_since, longform.since)
        binding.itemLongformContainer.setOnClickListener {
            onItemClick(longform)
        }
    }
}