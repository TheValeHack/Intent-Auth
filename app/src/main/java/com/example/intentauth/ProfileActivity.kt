package com.example.intentauth

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentauth.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding  = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dataNama = intent.getStringExtra("EXTRA_NAMA") ?: ""
        val dataEmail = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val dataNohp = intent.getStringExtra("EXTRA_NOHP") ?: ""
        val dataJenisKelamin = intent.getStringExtra("EXTRA_JENISKELAMIN") ?: ""

        val jenisKelaminData = resources.getStringArray(R.array.jenis_kelamin)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            jenisKelaminData
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding){
            inputKelamin.adapter = adapter

            inputEmail.setText(dataEmail)
            inputName.setText(dataNama)
            inputNohp.setText(dataNohp)

            val position = jenisKelaminData.indexOf(dataJenisKelamin)
            if (position >= 0) {
                inputKelamin.setSelection(position)
            }
        }
    }
}