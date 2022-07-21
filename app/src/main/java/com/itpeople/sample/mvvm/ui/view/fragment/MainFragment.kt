package com.itpeople.sample.mvvm.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.itpeople.sample.mvvm.R
import com.itpeople.sample.mvvm.data.model.ResultsItem
import com.itpeople.sample.mvvm.ui.adapter.MainAdapter
import com.itpeople.sample.mvvm.ui.viewmodel.MainViewModel
import com.itpeople.sample.mvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*


@AndroidEntryPoint
class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MainAdapter(arrayListOf())
        adapter.onItemClick = { result ->
            val bundle = bundleOf("resultItem" to Gson().toJson(result))
            Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerView.adapter = adapter
    }

    private fun setupObserver() {

        mainViewModel.users.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { users ->
                        mainViewModel.insertUsers(users).observe(viewLifecycleOwner, { status ->
                            if (status == Status.SUCCESS) {
                                mainViewModel.fetchUsers().observe(requireActivity(), { userList ->
                                    renderList(userList)
                                })
                            }else{
                                progressBar.visibility = View.GONE
                            }
                        })
                    }
                    recyclerView.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(results: List<ResultsItem>) {

        adapter.addData(results)
        progressBar.visibility = View.GONE


    }

}