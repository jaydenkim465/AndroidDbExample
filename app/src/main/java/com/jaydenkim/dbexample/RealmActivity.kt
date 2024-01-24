package com.jaydenkim.dbexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jaydenkim.dbexample.databinding.ActivityRealmBinding

class RealmActivity : AppCompatActivity() {
	val binding get() = _binding!!
	var _binding: ActivityRealmBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		_binding = ActivityRealmBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}