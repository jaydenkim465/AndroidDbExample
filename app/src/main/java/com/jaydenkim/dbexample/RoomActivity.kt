package com.jaydenkim.dbexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jaydenkim.dbexample.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity() {
	val binding get() = _binding!!
	var _binding: ActivityRoomBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		_binding = ActivityRoomBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}