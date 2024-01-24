package com.jaydenkim.dbexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaydenkim.dbexample.databinding.TestItemLayoutBinding
import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItem

class TestItemAdapter(private var dataSet: Array<SQLiteTestItem>) :
	RecyclerView.Adapter<TestItemAdapter.TextItemViewHolder>() {
	class TextItemViewHolder(private val binding: TestItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item : SQLiteTestItem) {
			binding.dbIndex.text = item._id
			binding.itemId.text = item.itemId
			binding.itemCd.text = item.itemCd
			binding.itemValue.text = item.itemValue
			binding.regDate.text = item.regDate
			binding.updDate.text = item.updDate
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
		val binding = TestItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return TextItemViewHolder(binding)
	}

	override fun getItemCount(): Int {
		return dataSet.size
	}

	override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
		holder.bind(dataSet[position])
	}
}