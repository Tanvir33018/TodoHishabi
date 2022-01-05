package com.example.hishabikotlin.ui.main

import android.app.Dialog
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hishabikotlin.R
import com.example.hishabikotlin.adapter.TodoAdapter
import com.example.hishabikotlin.databinding.MainFragmentBinding
import com.example.hishabikotlin.database.TodoModel

class MainFragment : Fragment()  {
    private var mList: ArrayList<TodoModel> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: TodoAdapter
    private lateinit var mBinding:MainFragmentBinding
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = MainFragmentBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = mBinding.taskRv
        val layout: LinearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        mAdapter = TodoAdapter(requireContext(),{
            taskExecute(it)
        },{
            update(it)
        })
        recyclerView.layoutManager = layout
        recyclerView.adapter = mAdapter
        clickListener()
        getData()
        return mBinding.root
    }

    fun getData() {
        viewModel.getTask.observe(viewLifecycleOwner, Observer { todoModelList ->
            mAdapter.setData(todoModelList)
        })
    }

    private fun clickListener() {
        mBinding.fab.setOnClickListener(View.OnClickListener {
            showDialog()
        })
    }

    private fun taskExecute(it: TodoModel) {
        showDialog2(it)
        mAdapter.notifyDataSetChanged()
    }

    private fun update(todoModel: TodoModel){

        viewModel.updateTask(todoModel)
        getData()
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_layout)
        dialog.show()

        val title = dialog.findViewById(R.id.title) as EditText
        val body = dialog.findViewById(R.id.body) as EditText
        val button = dialog.findViewById(R.id.button) as Button

        button.setOnClickListener(View.OnClickListener {
            val todoTask = TodoModel(0,title.text.toString(),body.text.toString(),0)
            viewModel.addTask(todoTask)
            dialog.dismiss()
        })
    }


    private fun showDialog2(todoModel: TodoModel) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_layout2)
        val delete = dialog.findViewById(R.id.delete) as Button
        val title = dialog.findViewById(R.id.titel2) as TextView
        val body = dialog.findViewById(R.id.body2) as TextView
        //val complete = dialog.findViewById(R.id.comlete) as Button
        title.setText(todoModel.mTitle.toString())
        body.setText(todoModel.mDescription.toString())
        dialog.show()
        delete.setOnClickListener(View.OnClickListener {
              viewModel.deleteTask(todoModel)
              getData()
            dialog.dismiss()
        })
    }
}