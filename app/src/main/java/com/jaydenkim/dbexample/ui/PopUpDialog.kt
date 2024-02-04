package com.jaydenkim.dbexample.ui

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.jaydenkim.dbexample.databinding.DialogPopUpBinding
import java.util.UUID

open class PopUpDialog(val type: Int) : DialogFragment() {
	companion object {
		val INSERT = 0
		val UPDATE = 1

		val TAG = "CUSTOM_POP_UP_DIALOG"
	}

	val binding get() = _binding!!
	var _binding: DialogPopUpBinding? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		super.onCreateView(inflater, container, savedInstanceState)
		_binding = DialogPopUpBinding.inflate(layoutInflater)
		initializeUI()
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	override fun onResume() {
		super.onResume()
		var width = 0
		var height = 0
		val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			val windowMetrics = wm.currentWindowMetrics
			val insets = windowMetrics.windowInsets
				.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
			width = windowMetrics.bounds.width() - insets.left - insets.right
			height = windowMetrics.bounds.height() - insets.bottom - insets.top
		} else {
			val displayMetrics = DisplayMetrics()
			wm.defaultDisplay.getMetrics(displayMetrics)
			width = displayMetrics.widthPixels
			height = displayMetrics.heightPixels
		}
		val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
		val display = windowManager.defaultDisplay
		val size = Point()
		display.getSize(size)
		val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
		params?.width = (width * 0.9).toInt()
		params?.height = (height * 0.8).toInt()
		dialog?.window?.attributes = params as WindowManager.LayoutParams
	}

	private fun initializeUI() {
		if (type == INSERT) {
			binding.editTextItemId.setText(UUID.randomUUID().toString())
			binding.editTextItemId.isEnabled = true
			binding.editTextItemId.isFocusable = true
		} else {
			binding.editTextItemId.isEnabled = false
			binding.editTextItemId.isFocusable = false
		}

		binding.buttonCancel.setOnClickListener {
			dismiss()
		}
	}
}