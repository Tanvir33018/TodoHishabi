package com.example.hishabikotlin.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hishabikotlin.adapter.TodoAdapter
import com.example.hishabikotlin.databinding.MainFragmentBinding
import com.example.hishabikotlin.ui.main.data_class.TodoModel

class MainFragment : Fragment() {
    private val mList: ArrayList<TodoModel> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: TodoAdapter
    private lateinit var mBinding:MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = MainFragmentBinding.inflate(layoutInflater)
        mList.add(TodoModel("Task 1", "Demo"))
        mList.add(TodoModel("Task 2", "Demo"))
        mList.add(TodoModel("Task 3", "Demo"))
        mList.add(TodoModel("Task 4", "Demo"))
        mList.add(TodoModel("Task 5", "Demo"))
        mList.add(TodoModel("Task 6", "Demo"))
        mList.add(TodoModel("Task 7", "Demo"))
        mList.add(TodoModel("Task 8", "Demo"))
        mList.add(TodoModel("Task 9", "Demo"))
        mList.add(TodoModel("Task 10", "Demo"))
        recyclerView = mBinding.taskRv
        val layout: LinearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        mAdapter = TodoAdapter(mList,requireContext(),{
            taskExecute(it)
        })
        recyclerView.layoutManager = layout
        recyclerView.adapter = mAdapter
        clickListener()
        return mBinding.root
    }

    private fun clickListener() {
        mBinding.fab.setOnClickListener(View.OnClickListener {
            mList.add(TodoModel("Task 10", "Demo"))
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun taskExecute(it: Int) {
        mAdapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}