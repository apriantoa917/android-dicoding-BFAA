package com.aprianto.mygithub.ui.favorite

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aprianto.mygithub.R
import com.aprianto.mygithub.data.viewmodel.UserFavoriteViewModel
import com.aprianto.mygithub.data.viewmodel.ViewModelUserFactory
import com.aprianto.mygithub.databinding.ActivityFavorteUserBinding
import com.aprianto.mygithub.utils.Helper
import com.aprianto.mygithub.utils.callback.RecyclerViewSwipeCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class UserFavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavorteUserBinding
    private lateinit var viewModel: UserFavoriteViewModel
    private lateinit var favoriteAdapter: UserFavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavorteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // jika inflate menu options, tombol back harus hard code manual
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        favoriteAdapter = UserFavoriteAdapter()
        binding.rvFavorite.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = favoriteAdapter
        }

        viewModel = obtainViewModel(this@UserFavoriteActivity)
        viewModel.getAllFavorites().observe(this, { favoriteList ->
            if (favoriteList != null) {
                favoriteAdapter.setListFavorites(favoriteList)
            }
        })

        val swipeCallback = object : RecyclerViewSwipeCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val username = favoriteAdapter.getSwipedUsername(position)
                binding.rvFavorite.adapter?.let {
                    it.notifyItemRemoved(position)
                    viewModel.unsetFavorite(username)
                    Helper.toast(this@UserFavoriteActivity, "$username dihapus dari favorite")
                }
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.rvFavorite)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_favorite_user_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_remove -> {
                if (favoriteAdapter.itemCount > 0) {
                    MaterialAlertDialogBuilder(this@UserFavoriteActivity)
                        .setTitle("Hapus Semua Favorite ?")
                        .setMessage("Anda akan menghapus semua data favorite?")
                        .setCancelable(true)
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Hapus") { _: DialogInterface, _: Int ->
                            viewModel.removeAllFavorites()
                            Helper.toast(this@UserFavoriteActivity, "Data favorite dihapus")
                        }
                        .setNegativeButton("Batalkan") { _: DialogInterface, _: Int -> }
                        .show()
                } else {
                    Helper.toast(this@UserFavoriteActivity, "Kamu tidak memiliki data favorite")
                }
            }
        }
        return true
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserFavoriteViewModel {
        val factory = ViewModelUserFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[UserFavoriteViewModel::class.java]
    }

}