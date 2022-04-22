package ru.inai.kursach_2_0.activity.manager.directions

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

class ListOfEmployeeSpinnerAdapter(val onClickListener : (ListOfEmployeeModel)->Unit) : RecyclerView.Adapter<ListOfEmployeeSpinnerAdapter.MyViewHolder>(), Filterable {

    private var list : ArrayList<ListOfEmployeeModel> = arrayListOf()
    private var listFilter : ArrayList<ListOfEmployeeModel> = arrayListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataList : ArrayList<ListOfEmployeeModel>){
        val anotherList = arrayListOf<ListOfEmployeeModel>()
        dataList.forEach {
            if(it.userRole.equals("WORKER")){
                anotherList.add(it)
            }
        }
        this.list = anotherList
        this.listFilter = anotherList
        notifyDataSetChanged()
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name_employee_for_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_for_search,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
           holder.name.text = list[position].name + " " + list[position].surname
           holder.itemView.setOnClickListener {
               onClickListener(list[position])
           }
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