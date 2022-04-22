package ru.inai.kursach_2_0.activity.employee.completed.todo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.repository.model.CompletedToDo

class CompletedToDoAdapter(val list : ArrayList<CompletedToDo>) : RecyclerView.Adapter<CompletedToDoAdapter.MyViewHolder>() {
    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val icon : ImageView = itemView.findViewById(R.id.item_employee_icon)
        val title : TextView = itemView.findViewById(R.id.item_employee_name_textView)
        val description : TextView = itemView.findViewById(R.id.item_employee_position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/1632/1632670.png").into(holder.icon)
        holder.title.text = list[position].title
        TextViewCompat.setTextAppearance(holder.description,R.style.fontDescription)
        val descriptionText = list[position].description!!.substring(0,15)
        holder.description.text = descriptionText + "..." +"\n" + list[position].date
    }

    override fun getItemCount(): Int {
        return list.size
    }
}