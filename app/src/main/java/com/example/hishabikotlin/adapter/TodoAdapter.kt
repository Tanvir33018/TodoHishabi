package com.example.hishabikotlin.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hishabikotlin.R
import com.example.hishabikotlin.database.TodoModel

class TodoAdapter(
    private val mContext: Context,
    private val function: (TodoModel) -> Unit,
    private val function2:(TodoModel)-> Unit
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    var mList = emptyList<TodoModel>()
    var flag:Boolean = false

    fun setData(taskList: List<TodoModel>) {
        this.mList = taskList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public val mTitle: TextView = itemView.findViewById(R.id.title)
        public val mButton: CheckBox = itemView.findViewById(R.id.checkBox)
        public val mLayout: RelativeLayout = itemView.findViewById(R.id.layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTitle.setText(mList.get(position).mTitle)
        holder.mButton.setOnClickListener(View.OnClickListener {
            if (holder.mButton.isChecked) {
                holder.mButton.setBackgroundTintList(
                    mContext.getResources().getColorStateList(R.color.teal_200)
                )
                holder.mTitle.paintFlags = holder.mTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.mTitle.setTextColor(
                    mContext.getResources().getColorStateList(R.color.custom3)
                )
                /*mList.get(position).mFlag = true
                function2(mList.get(position))*/
            } else {
                holder.mButton.setBackgroundTintList(
                    mContext.getResources().getColorStateList(R.color.custom)
                )
                holder.mTitle.paintFlags =
                    holder.mTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                holder.mTitle.setTextColor(mContext.getResources().getColorStateList(R.color.black))
                /*mList.get(position).mFlag = false
                function2(mList.get(position))*/
            }

        })

        holder.mLayout.setOnClickListener(View.OnClickListener {
            function(mList.get(position))
        })
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}