package ru.inai.kursach_2_0.activity.manager.my.todo

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.manager.directions.AllDirectionsModel

class EmployeeToDoListAdapter(
    val onDoneClick: Unit,val context : Context, val id: Int,
    val onMoreClickListener: (ArrayList<AllDirectionsModel>)->Unit)
    : RecyclerView.Adapter<EmployeeToDoListAdapter.MyViewHolder>() {

    private val list : ArrayList<AllDirectionsModel> = arrayListOf()

    fun putList(_list : ArrayList<AllDirectionsModel>){
        list.clear()
        _list.forEach {
            if(it.employeeObject!!.id == id){
                list.add(it)
            }
        }
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val icon : ImageView = itemView.findViewById(R.id.item_employee_icon)
        val title : TextView = itemView.findViewById(R.id.item_employee_name_textView)
        val description : TextView = itemView.findViewById(R.id.item_employee_position)
        val menuLayout : LinearLayout = itemView.findViewById(R.id.itemEmployeeMenuLinearLayout)
        val moreButton : ImageView = itemView.findViewById(R.id.itemEmployeeMore)
        val doneButton : ImageView = itemView.findViewById(R.id.itemEmployeeDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.menuLayout.visibility = View.VISIBLE
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/2833/2833457.png").into(holder.icon)
        holder.title.text = list[position].title
        TextViewCompat.setTextAppearance(holder.description,R.style.fontDescription)
        val descriptionText = list[position].description
        if(list[position].description!!.length>15){
            holder.description.text = descriptionText!!.substring(0,15) + "..."
        }else{
            holder.description.text = descriptionText
        }
        holder.moreButton.setOnClickListener {
            onMoreClickListener(arrayListOf(list[position]))
        }
        holder.doneButton.setOnClickListener {
            val editToDo = EmployeeToDoListViewModel(context)
            editToDo.addToDoDoneToDo(arrayListOf(list[position]))
            list.remove(list[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}