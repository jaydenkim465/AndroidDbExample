package com.jaydenkim.dbexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaydenkim.dbexample.databinding.ItemBottomDialogListBinding

class BottomDialogAdapter(private var dataSet: Array<String>, private val listener: (Int) -> Unit) :
	RecyclerView.Adapter<BottomDialogAdapter.SimpleTextViewHolder>() {
	class SimpleTextViewHolder(private val binding: ItemBottomDialogListBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(item: String, position: Int, listener: (Int) -> Unit) {
			binding.textViewItemBottomDialog.text = item
			binding.root.setOnClickListener {
				listener(position)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleTextViewHolder {
		val binding =
			ItemBottomDialogListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return SimpleTextViewHolder(binding)
	}

	override fun getItemCount(): Int {
		return dataSet.size
	}

	override fun onBindViewHolder(holder: SimpleTextViewHolder, position: Int) {
		holder.bind(dataSet[position], position, listener)
	}
}