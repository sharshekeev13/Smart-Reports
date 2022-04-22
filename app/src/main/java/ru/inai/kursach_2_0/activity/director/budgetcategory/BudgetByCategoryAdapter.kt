package ru.inai.kursach_2_0.activity.director.budgetcategory

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.repo.models.AllBudget
import ru.inai.kursach_2_0.repo.models.BudgetDTO
import ru.inai.kursach_2_0.repository.model.BudgetByCategory

class BudgetByCategoryAdapter(_context : Context) : RecyclerView.Adapter<BudgetByCategoryAdapter.MyViewHolder>() {

    private val list = arrayListOf<BudgetDTO>()
    private val context = _context

    fun putList(_list : AllBudget){
        val marketing = arrayListOf<BudgetDTO>(
           BudgetDTO(
               _list.marketingDto?.name,
               _list.marketingDto?.sum
           )
        )
        val salary = arrayListOf<BudgetDTO>(
            BudgetDTO(
                _list.salariesDto?.name,
                _list.salariesDto?.sum
            )
        )
        list.addAll(marketing)
        list.addAll(salary)
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val category : TextView = itemView.findViewById(R.id.area_name_textview)
        val icon : ImageView = itemView.findViewById(R.id.social_media_icon)
        val budgetForCategory : TextView = itemView.findViewById(R.id.budget_textview)
        val budgetIcon : ImageView = itemView.findViewById(R.id.budget_icon)
        val gone : TextView = itemView.findViewById(R.id.members_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.social_media_card,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.gone.visibility = View.GONE
        holder.category.visibility = View.VISIBLE
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/1761/1761422.png").into(holder.budgetIcon)
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/3467/3467191.png").into(holder.icon)
        when(list[position].name){
            "Зарплата" -> {
                holder.category.text = context.resources.getString(R.string.salary)
                holder.budgetForCategory.text = list[position].sum.toString()
            }
            "Маркетинг" -> {
                holder.category.text = context.resources.getString(R.string.marketing)
                holder.budgetForCategory.text = list[position].sum.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}