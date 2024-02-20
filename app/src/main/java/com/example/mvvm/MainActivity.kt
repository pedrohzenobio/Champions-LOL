package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.buttonLogin.setOnClickListener(this)

        setText()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login){
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.doLogin(email, password)
        }
    }

    private fun setText(){
        viewModel.welcome().observe(this, Observer {
            binding.textOla.text = it
        })
        viewModel.login().observe(this, Observer {
            if (it){
                Toast.makeText(applicationContext,"SUCESSO!",Toast.LENGTH_SHORT).show()
            } else Toast.makeText(applicationContext,"FALHA!",Toast.LENGTH_SHORT).show()
        })
    }


}