package com.jaydenkim.dbexample.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaydenkim.dbexample.R
import com.jaydenkim.dbexample.adapter.SQLiteTestItemAdapter
import com.jaydenkim.dbexample.databinding.ActivityDataListViewBinding
import com.jaydenkim.dbexample.presenter.OnlySQLitePresenter
import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItem

class OnlySQLiteActivity : AppCompatActivity() {
	val binding get() = _binding!!
	var _binding: ActivityDataListViewBinding? = null
	val presenter = OnlySQLitePresenter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		_binding = ActivityDataListViewBinding.inflate(layoutInflater)
		setContentView(binding.root)

		initializeUI()
	}

	override fun onResume() {
		super.onResume()
		binding.recyclerViewTestItemList.adapter = presenter.refreshList(this)
	}

	private fun initializeUI() {
		// Search Filter
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
				presenter.setSelectSearchType(this@OnlySQLiteActivity, pos)
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
			}
		}

		// Recycler View (List)
		binding.recyclerViewTestItemList.layoutManager =
			LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		binding.recyclerViewTestItemList.adapter = SQLiteTestItemAdapter(arrayOf()) {}
		binding.recyclerViewTestItemList.addItemDecoration(
			DividerItemDecoration(
				this,
				LinearLayoutManager.VERTICAL
			)
		)

		// Search View
		binding.searchViewQuery.setOnQueryTextListener(object : OnQueryTextListener {
			override fun onQueryTextChange(newText: String?): Boolean {
				return true
			}

			override fun onQueryTextSubmit(query: String?): Boolean {
				binding.recyclerViewTestItemList.adapter =
					presenter.searchKeyword(this@OnlySQLiteActivity, query)
				// true 이면 키보드 유지, false 이면 키보드 숨기기
				return false
			}
		})

		// Button
		binding.buttonCreateSample.setOnClickListener {
			presenter.randomDataGenerate(this)
		}
		binding.buttonCreateItem.setOnClickListener {
			presenter.insertPopUpDialog(this)
		}
		binding.buttonRefresh.setOnClickListener {
			binding.recyclerViewTestItemList.adapter = presenter.refreshList(this)
		}
	}
}