package ru.inai.kursach_2_0.activity.manager.list.employee

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.repo.models.ListOfEmployeeModel
import ru.inai.kursach_2_0.repository.model.Post
import java.util.*
import kotlin.collections.ArrayList

class ListOfEmployeeAdapter : RecyclerView.Adapter<ListOfEmployeeAdapter.MyViewHolder>(), Filterable {

    private var list : ArrayList<ListOfEmployeeModel> = arrayListOf()
    private var listFilter : ArrayList<ListOfEmployeeModel> = arrayListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataList : ArrayList<ListOfEmployeeModel>){
        this.list = dataList
        this.listFilter = dataList
        notifyDataSetChanged()
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val employeeIcon : ImageView = itemView.findViewById(R.id.item_employee_icon)
        val fullName : TextView = itemView.findViewById(R.id.item_employee_name_textView)
        val empPosition : TextView = itemView.findViewById(R.id.item_employee_position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/1738/1738691.png").into(holder.employeeIcon)
        holder.fullName.text = list[position].name +" "+ list[position].surname
        holder.empPosition.text = list[position].userRole
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getFilter(): Filter {
        val filter = object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val filterResult =FilterResults()
                if(p0 == null || p0.isEmpty()){
                    filterResult.values = listFilter
                    filterResult.count = listFilter.size
                }else{
                    val searchChar = p0.toString().lowercase(Locale.getDefault())
                    val filteredResults = ArrayList<ListOfEmployeeModel>()
                    for(Post in listFilter){
                        if("${Post.name} ${Post.surname}".lowercase(Locale.getDefault()).contains(searchChar)){
                            filteredResults.add(Post)
                        }
                    }
                    filterResult.values = filteredResults
                    filterResult.count = filteredResults.size
                }
                return filterResult
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                list = p1!!.values as ArrayList<ListOfEmployeeModel>
                notifyDataSetChanged()
            }

        }
        return filter
    }
}