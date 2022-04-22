package ru.inai.kursach_2_0.activity.marketing.area

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.repo.models.DistrictsModel
import ru.inai.kursach_2_0.utils.SharedPreference

class AreaRecyclerView(val context : Context) : RecyclerView.Adapter<AreaRecyclerView.MyViewHolder>() {

    private val sp : SharedPreference = SharedPreference(context)
    private var list : ArrayList<DistrictsModel> = arrayListOf()

    fun setData(data : ArrayList<DistrictsModel>){
        this.list = data
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        val socialMediaIcon : ImageView = itemView.findViewById(R.id.social_media_icon)
        val nameArea : TextView = itemView.findViewById(R.id.area_name_textview)
        val membersIcon : ImageView = itemView.findViewById(R.id.budget_icon)
        val membersTextView : TextView = itemView.findViewById(R.id.budget_textview)
        val gone : TextView = itemView.findViewById(R.id.members_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.social_media_card,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/3301/3301591.png").into(holder.socialMediaIcon)
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/1935/1935159.png").into(holder.membersIcon)
        holder.gone.visibility = View.GONE
        holder.nameArea.visibility = View.VISIBLE
        holder.membersTextView.text = list[position].people.toString()
        when(list[position].district){
            "Первомайский" ->  holder.nameArea.text = context.getString(R.string.pervomay)
            "Ленинский" ->  holder.nameArea.text = context.getString(R.string.lenin)
            "Октябрьский" ->  holder.nameArea.text = context.getString(R.string.oktyab)
            "Свердловский" ->  holder.nameArea.text = context.getString(R.string.sverd)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}