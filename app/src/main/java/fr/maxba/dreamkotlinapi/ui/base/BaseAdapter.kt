package fr.maxba.dreamkotlinapi.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import fr.maxba.dreamkotlinapi.data.model.BaseObject
import fr.maxba.dreamkotlinapi.utils.DiffCallback
import fr.maxba.dreamkotlinapi.utils.OnItemClickListener

abstract class BaseAdapter<T: BaseObject>(private val lifecycleOwner: LifecycleOwner, updateWhen: ((T, T) -> Boolean)? = null):
    ListAdapter<T, BaseViewHolder<T, *>>(DiffCallback<T>(updateWhen)) {

    var onClick: ((item: T) -> Unit)? = null
    var onLongClick: ((item: T) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder<T, *>, position: Int) {
        holder.bind(lifecycleOwner, getItem(position), object: OnItemClickListener<T> {
            override fun onItemClick(item: T) {
                onClick?.invoke(item)
            }

            override fun onItemLongClick(item: T): Boolean {
                onLongClick?.invoke(item)
                return true
            }
        })
    }

    override fun getItemViewType(position: Int): Int = layoutFor(position)

    protected abstract fun layoutFor(position: Int): Int
}