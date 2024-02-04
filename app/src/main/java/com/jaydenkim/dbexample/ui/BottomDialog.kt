package com.jaydenkim.dbexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jaydenkim.dbexample.adapter.BottomDialogAdapter
import com.jaydenkim.dbexample.databinding.DialogBottomBinding

class BottomDialog(private val data: Array<String>, private val result: (Int) -> Unit) : BottomSheetDialogFragment() {
	companion object {
		val TAG = "CUSTOM_BOTTOM_DIALOG"
	}

	val binding get() = _binding!!
	var _binding: DialogBottomBinding? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		super.onCreateView(inflater, container, savedInstanceState)
		_binding = DialogBottomBinding.inflate(layoutInflater)
		initializeUI()
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	private fun initializeUI() {
		// Recycler View (List)
		binding.recyclerViewBottomDialog.layoutManager =
			LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
		binding.recyclerViewBottomDialog.adapter = BottomDialogAdapter(data) { position ->
			result(position)
			dismiss()
		}
		binding.recyclerViewBottomDialog.addItemDecoration(
			DividerItemDecoration(
				requireContext(),
				LinearLayoutManager.VERTICAL
			)
		)

		binding.buttonCancel.setOnClickListener {
			dismiss()
		}
	}
}