package fr.maxba.dreamkotlinapi.utils

import fr.maxba.dreamkotlinapi.data.model.BaseObject

interface OnItemClickListener<T: BaseObject> {

    fun onItemClick(item: T)

    fun onItemLongClick(item: T): Boolean

}