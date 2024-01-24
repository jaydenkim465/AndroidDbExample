package com.jaydenkim.dbexample

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaydenkim.dbexample.adapter.SQLiteTestItemAdapter
import com.jaydenkim.dbexample.databinding.ActivityDataListViewBinding
import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItem
import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItemDAO
import java.util.UUID

class OnlySQLiteActivity : AppCompatActivity() {
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

		binding.spinnerSearchFilter.onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
			}
		}

		binding.recyclerViewTestItemList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		binding.recyclerViewTestItemList.adapter = SQLiteTestItemAdapter(arrayOf())
		binding.recyclerViewTestItemList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

		binding.buttonCreateSample.setOnClickListener {
			val dao = SQLiteTestItemDAO()
			for (i in 0 .. 9) {
				val uuid = UUID.randomUUID().toString()
				val item = SQLiteTestItem(uuid, "T", "TEST_$i")
				dao.insertItem(this, item)
			}
		}

		binding.searchViewQuery.setOnQueryTextListener(object : OnQueryTextListener {
			override fun onQueryTextChange(newText: String?): Boolean {
				return true
			}

			override fun onQueryTextSubmit(query: String?): Boolean {
				val dao = SQLiteTestItemDAO()
				val list = dao.selectAll(this@OnlySQLiteActivity)
				binding.recyclerViewTestItemList.adapter = SQLiteTestItemAdapter(list)

				// true 이면 키보드 유지, false 이면 키보드 숨기기
				return false
			}
		})
	}
}