package com.jaydenkim.dbexample

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaydenkim.dbexample.adapter.RealmTestItemAdapter
import com.jaydenkim.dbexample.databinding.ActivityDataListViewBinding
import com.jaydenkim.dbexample.realm.RealmTestItem
import com.jaydenkim.dbexample.realm.RealmTestItemDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class RealmActivity : AppCompatActivity() {
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
		binding.recyclerViewTestItemList.adapter = RealmTestItemAdapter(listOf())
		binding.recyclerViewTestItemList.addItemDecoration(
			DividerItemDecoration(
				this,
				LinearLayoutManager.VERTICAL
			)
		)

		binding.buttonCreateSample.setOnClickListener {
			GlobalScope.launch(Dispatchers.IO) {
				val realm = RealmTestItemDAO()
				for (i in 0..9) {
					val uuid = UUID.randomUUID().toString()
					val item = RealmTestItem(uuid, "T", "TEST_$i")
					realm.insert(item)
				}
			}
		}

		binding.searchViewQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextChange(newText: String?): Boolean {
				return true
			}

			override fun onQueryTextSubmit(query: String?): Boolean {
				GlobalScope.launch(Dispatchers.IO) {
					val realm = RealmTestItemDAO()
					val list = realm.getAll()
					withContext(Dispatchers.Main) {
						binding.recyclerViewTestItemList.adapter = RealmTestItemAdapter(list)
					}
				}

				// true 이면 키보드 유지, false 이면 키보드 숨기기
				return false
			}
		})
	}
}