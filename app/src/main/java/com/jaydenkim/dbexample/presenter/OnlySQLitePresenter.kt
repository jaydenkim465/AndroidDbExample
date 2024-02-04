package com.jaydenkim.dbexample.presenter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.jaydenkim.dbexample.R
import com.jaydenkim.dbexample.adapter.SQLiteTestItemAdapter
import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItem
import com.jaydenkim.dbexample.sqlitehelper.SQLiteTestItemDAO
import com.jaydenkim.dbexample.ui.BottomDialog
import com.jaydenkim.dbexample.ui.PopUpDialog
import com.jaydenkim.dbexample.ui.SQLitePopUpDialog
import java.util.UUID

class OnlySQLitePresenter {
	val selectSearchType get() = _selectSearchType
	private var _selectSearchType = SQLiteTestItemDAO.ItemColumn.ITEM_VALUE

	fun randomDataGenerate(context: Context) {
		val dao = SQLiteTestItemDAO()
		for (i in 0..9) {
			val uuid = UUID.randomUUID().toString()
			val item = SQLiteTestItem(uuid, "T", "TEST_$i")
			dao.insertItem(context, item)
		}
	}

	fun searchKeyword(activity: AppCompatActivity, input: String?): SQLiteTestItemAdapter {
		val dao = SQLiteTestItemDAO()
		val list = dao.selectKeyword(activity, selectSearchType, input ?: "")
		return SQLiteTestItemAdapter(list) { data ->
			popUpSubMenuDialog(activity, data)
		}
	}

	fun refreshList(activity: AppCompatActivity): SQLiteTestItemAdapter {
		val dao = SQLiteTestItemDAO()
		val list = dao.selectAll(activity)
		return SQLiteTestItemAdapter(list) { data ->
			popUpSubMenuDialog(activity, data)
		}
	}

	fun setSelectSearchType(context: Context, input: Int) {
		_selectSearchType = context.resources.getStringArray(R.array.spinner_search_filter)[input]
	}

	fun popUpSubMenuDialog(activity: AppCompatActivity, data: SQLiteTestItem) {
		val menuList = activity.resources.getStringArray(R.array.sub_menu_list)
		BottomDialog(menuList) { result ->
			when (result) {
				0 -> updatePopUpDialog(activity, data)
				1 -> deleteDate(data)
			}
		}.show(activity.supportFragmentManager, BottomDialog.TAG)
	}

	fun insertPopUpDialog(activity: AppCompatActivity) {
		SQLitePopUpDialog(PopUpDialog.INSERT).show(activity.supportFragmentManager, PopUpDialog.TAG)
	}

	fun updatePopUpDialog(activity: AppCompatActivity, data: SQLiteTestItem) {
		SQLitePopUpDialog(PopUpDialog.UPDATE, data).show(
			activity.supportFragmentManager,
			PopUpDialog.TAG
		)
	}

	fun deleteDate(data: SQLiteTestItem) {

	}
}