package com.aprianto.mygithub.ui.detail.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aprianto.mygithub.data.viewmodel.UserSocialViewModel
import com.aprianto.mygithub.databinding.FragmentFollowBinding
import com.aprianto.mygithub.utils.Constanta
import com.aprianto.mygithub.utils.Helper

class FragmentFollow : Fragment() {

    private var username: String = "github"
    private var followMode: String = "followers"
    private val rvAdapter = UserSocialAdapter()
    private val viewModel: UserSocialViewModel by viewModels()
    private lateinit var binding: FragmentFollowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(Constanta.EXTRA_USERNAME)!!
            followMode = it.getString(Constanta.EXTRA_FOLLOW_MODE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvUser.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = rvAdapter
        }

        viewModel.apply {
            loading.observe(viewLifecycleOwner, { binding.loading.visibility = it })
            error.observe(
                viewLifecycleOwner,
                { context?.let { context -> Helper.toast(context, it) } })
            socialData.observe(viewLifecycleOwner, {
                rvAdapter.apply {
                    initData(it)
                    notifyDataSetChanged()
                }
            })
            this.setSocialData(followMode, username)
        }
    }

    override fun onResume() {
        super.onResume()
        // samakan height fragment ketika switch followers / following berbeda height -> misal dari 20 (scroll) vs 1 (non scroll)
        binding.root.requestLayout()
    }

    companion object {
        @JvmStatic
        fun newInstance(paramsUsername: String, paramsFollowType: String) =
            FragmentFollow().apply {
                arguments = Bundle().apply {
                    putString(Constanta.EXTRA_USERNAME, paramsUsername)
                    putString(Constanta.EXTRA_FOLLOW_MODE, paramsFollowType)
                }
            }
    }
}