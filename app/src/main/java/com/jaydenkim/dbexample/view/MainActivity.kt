package com.jaydenkim.dbexample.view

import android.content.Intent
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

		initializeUI()
	}

	private fun initializeUI() {
		binding.buttonSqlite.setOnClickListener {
			val intent = Intent(this, OnlySQLiteActivity::class.java)
			startActivity(intent)
		}

		binding.buttonRoom.setOnClickListener {
			val intent = Intent(this, RoomActivity::class.java)
			startActivity(intent)
		}

		binding.buttonRealm.setOnClickListener {
			val intent = Intent(this, RealmActivity::class.java)
			startActivity(intent)
		}
	}
}