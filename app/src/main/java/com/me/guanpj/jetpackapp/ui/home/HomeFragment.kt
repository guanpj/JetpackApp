package com.me.guanpj.jetpackapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.me.guanpj.jetpackapp.R
import com.me.guanpj.jetpackapp.databinding.FragmentHomeBinding
import com.me.guanpj.jetpackapp.ui.adapter.HomeListAdapter

class HomeFragment : Fragment() {

    private var isLine: Boolean = false
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = HomeListAdapter()
        binding.rvHome.adapter = adapter
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: HomeListAdapter, binding: FragmentHomeBinding) {
        viewModel = obtainViewModel(HomeListViewModel::class.java)
        viewModel.listData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) adapter.submitList(data)
        })
        binding.fab.setOnClickListener {
            binding.rvHome.layoutManager = if (isLine) GridLayoutManager(context,2) else LinearLayoutManager(context)
            binding.fab.setImageResource(if (isLine) R.mipmap.icon_lin else R.mipmap.icon_grid)
            isLine = !isLine
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.button_home).setOnClickListener {
            val action = HomeFragmentDirections
                    .actionHomeFragmentToHomeSecondFragment("From HomeFragment")
            NavHostFragment.findNavController(this@HomeFragment)
                    .navigate(action)
        }
    }
}
