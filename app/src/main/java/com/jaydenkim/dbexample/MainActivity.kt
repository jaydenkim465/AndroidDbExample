package com.jaydenkim.dbexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jaydenkim.dbexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	val binding get() = _binding!!
	var _binding: ActivityMainBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		_binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}