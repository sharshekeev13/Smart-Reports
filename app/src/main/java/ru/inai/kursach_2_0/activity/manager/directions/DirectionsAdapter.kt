package ru.inai.kursach_2_0.activity.manager.directions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.repo.models.ManagerToDoAllModel
import ru.inai.kursach_2_0.repo.models.employee.todo.EmployeeToDoModel
import ru.inai.kursach_2_0.repository.model.Directions

class DirectionsAdapter() : RecyclerView.Adapter<DirectionsAdapter.MyViewHolder>() {

    private val list : ArrayList<EmployeeToDoModel> = arrayListOf()

    fun putList(_list : ArrayList<EmployeeToDoModel>){
        list.clear()
        list.addAll(_list)
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val firstName : TextView = itemView.findViewById(R.id.item_directions_firstName_textView)
        val secondName : TextView = itemView.findViewById(R.id.item_directions_secondName_textView)
        val title : TextView = itemView.findViewById(R.id.item_directions_title_textView)
        val description : TextView = itemView.findViewById(R.id.item_directions_descriptions_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_directions,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.firstName.text = list[position].employee!!.name
        holder.secondName.text = list[position].employee!!.surname
        holder.title.text = list[position].title
        val descriptionText = list[position].description
        holder.description.text = descriptionText
    }

    override fun getItemCount(): Int {
        return list.size
    }
}