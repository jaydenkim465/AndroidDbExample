package com.jaydenkim.dbexample.ui

import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItem
import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItemDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class SQLitePopUpDialog(type: Int, private var data: SQLiteTestItem? = null) : PopUpDialog(type) {
	override fun onResume() {
		super.onResume()
		setUI()
	}

	private fun setUI() {
		if(type == UPDATE) {
			binding.editTextDbId.setText(data?._id)
			binding.editTextItemId.setText(data?.itemId)
			binding.editTextItemCd.setText(data?.itemCd)
			binding.editTextItemValue.setText(data?.itemValue)
			binding.editTextRegDate.setText(data?.regDate)
			binding.editTextUpdDate.setText(data?.updDate)
		}

		binding.buttonConfirm.setOnClickListener {
			processDB()
		}
	}

	private fun processDB() {
		GlobalScope.launch(Dispatchers.IO) {
			try {
				val dao = SQLiteTestItemDAO()
				if (type == INSERT) {
					data = SQLiteTestItem(
						binding.editTextItemId.text.toString(),
						binding.editTextItemCd.text.toString(),
						binding.editTextItemValue.text.toString()
					)
					dao.insertItem(requireContext(), data!!)
				} else {
					if (data != null) {
						data!!.itemCd = binding.editTextItemCd.text.toString()
						data!!.itemValue = binding.editTextItemValue.text.toString()
						dao.updateItem(requireContext(), data!!)
					}
				}
				dismiss()
			} catch (ex: Exception) {
				ex.printStackTrace()
			}
		}
	}
}