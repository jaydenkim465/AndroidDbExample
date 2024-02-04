package com.jaydenkim.dbexample.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaydenkim.dbexample.R
import com.jaydenkim.dbexample.adapter.RoomTestItemAdapter
import com.jaydenkim.dbexample.databinding.ActivityDataListViewBinding
import com.jaydenkim.dbexample.room.RoomHelper
import com.jaydenkim.dbexample.room.RoomTestItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class RoomActivity : AppCompatActivity() {
	val binding get() = _binding!!
	var _binding: ActivityDataListViewBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		_binding = ActivityDataListViewBinding.inflate(layoutInflater)
		setContentView(binding.root)

		initializeUI()
	}

	private fun initializeUI() {
		ArrayAdapter.createFromResource(
			this,
			R.array.spinner_search_filter,
			android.R.layout.simple_spinner_item
		).also { adpter ->
			adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
			binding.spinnerSearchFilter.adapter = adpter
		}

		binding.spinnerSearchFilter.onItemSelectedListener = object :
			AdapterView.OnItemSelectedListener {
			override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
			}
		}

		binding.recyclerViewTestItemList.layoutManager =
			LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		binding.recyclerViewTestItemList.adapter = RoomTestItemAdapter(listOf())
		binding.recyclerViewTestItemList.addItemDecoration(
			DividerItemDecoration(
				this,
				LinearLayoutManager.VERTICAL
			)
		)

		binding.buttonCreateSample.setOnClickListener {
			GlobalScope.launch(Dispatchers.IO) {
				val room = RoomHelper.getInstance(this@RoomActivity)
				for (i in 0..9) {
					val uuid = UUID.randomUUID().toString()
					val item = RoomTestItem(uuid, "T", "TEST_$i")
					room.getTestItemDAO().insertItem(item)
				}
			}
		}

		binding.searchViewQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextChange(newText: String?): Boolean {
				return true
			}

			override fun onQueryTextSubmit(query: String?): Boolean {
				GlobalScope.launch(Dispatchers.IO) {
					val room = RoomHelper.getInstance(this@RoomActivity)
					val list = room.getTestItemDAO().getAll()
					withContext(Dispatchers.Main) {
						binding.recyclerViewTestItemList.adapter = RoomTestItemAdapter(list)
					}
				}

				// true 이면 키보드 유지, false 이면 키보드 숨기기
				return false
			}
		})
	}
}