package com.aprianto.mygithub.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprianto.mygithub.*
import com.aprianto.mygithub.data.viewmodel.UserSearchViewModel
import com.aprianto.mygithub.databinding.ActivitySearchUserBinding
import com.aprianto.mygithub.utils.Constanta


class UserSearchActivity : AppCompatActivity() {

    private val context = this
    private val rvAdapter = UserSearchAdapter(this)
    private val viewModel: UserSearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.setTitle(R.string.app_name)
            it.setLogo(R.drawable.ic_launcher_foreground)
            it.setDisplayUseLogoEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        viewModel.apply {
            loading.observe(context, { binding.loading.visibility = it })
            illustration.observe(context, { binding.illustration.visibility = it })
            error.observe(context, { Constanta.toastError(context, it) })
            resultData.observe(context, { data ->
                rvAdapter.apply {
                    // jika data search null -> tidak menampilkan apapun
                    if(data.isNullOrEmpty()) clearData() else initData(data)
                    notifyDataSetChanged()
                }
            })
        }

        binding.rvUser.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
        }

        binding.edSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                if (binding.edSearch.text?.length == 0) {
                    Toast.makeText(
                        this@UserSearchActivity,
                        "Nama Pengguna tidak boleh kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnKeyListener false
                } else {
                    binding.edSearch.apply {
                        this.clearFocus()
                        val imm: InputMethodManager = getSystemService(
                            INPUT_METHOD_SERVICE
                        ) as InputMethodManager
                        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
                    }
                    viewModel.getSearchResult(binding.edSearch.text.toString())
                    return@setOnKeyListener true
                }
            }
            return@setOnKeyListener false
        }
    }
}