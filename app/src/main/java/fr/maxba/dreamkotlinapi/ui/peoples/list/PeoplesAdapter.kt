package fr.maxba.dreamkotlinapi.ui.peoples.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import fr.maxba.dreamkotlinapi.R
import fr.maxba.dreamkotlinapi.data.model.People
import fr.maxba.dreamkotlinapi.databinding.ItemPeopleBinding
import fr.maxba.dreamkotlinapi.ui.base.BaseAdapter
import fr.maxba.dreamkotlinapi.ui.base.BaseViewHolder
import fr.maxba.dreamkotlinapi.utils.OnItemClickListener

class PeoplesAdapter(lifecycleOwner: LifecycleOwner): BaseAdapter<People>(lifecycleOwner) {

    override fun layoutFor(position: Int): Int = R.layout.item_people

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<People, *> {
        val binding: ItemPeopleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return PeopleViewHolder(binding)
    }

    class PeopleViewHolder(private val binding: ItemPeopleBinding): BaseViewHolder<People, ItemPeopleBinding>(binding) {

        override fun bind(lifecycleOwner: LifecycleOwner, item: People, listener: OnItemClickListener<People>) {
            super.bind(lifecycleOwner, item, listener)
            binding.name.text = item.name
        }
    }
}