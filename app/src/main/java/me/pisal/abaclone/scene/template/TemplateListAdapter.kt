package me.pisal.abaclone.scene.template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.pisal.abaclone.databinding.ItemTemplateBinding
import me.pisal.abaclone.model.entity.TrxTemplate
import me.pisal.abaclone.model.entity.TrxTemplateDiff

class TemplateListAdapter : ListAdapter<TrxTemplate, TemplateListAdapter.ViewHolder>(TrxTemplateDiff){

    inner class ViewHolder(private val binding: ItemTemplateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: TrxTemplate) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTemplateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}