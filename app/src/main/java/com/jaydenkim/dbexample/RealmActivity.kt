package com.jaydenkim.dbexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jaydenkim.dbexample.databinding.ActivityDataListViewBinding

class RealmActivity : AppCompatActivity() {
	val binding get() = _binding!!
	var _binding: ActivityDataListViewBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		_binding = ActivityDataListViewBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}