package com.jaydenkim.dbexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaydenkim.dbexample.databinding.TestItemLayoutBinding
import com.jaydenkim.dbexample.room.RoomTestItem

class RoomTestItemAdapter(private var dataSet: List<RoomTestItem>) :
	RecyclerView.Adapter<RoomTestItemAdapter.TextItemViewHolder>() {
	class TextItemViewHolder(private val binding: TestItemLayoutBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(item: RoomTestItem) {
			binding.dbIndex.text = item._id.toString()
			binding.itemId.text = item.itemId
			binding.itemCd.text = item.itemCd
			binding.itemValue.text = item.itemValue
			binding.regDate.text = item.regDate
			binding.updDate.text = item.updDate
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
		val binding =
			TestItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return TextItemViewHolder(binding)
	}

	override fun getItemCount(): Int {
		return dataSet.size
	}

	override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
		holder.bind(dataSet[position])
	}
}