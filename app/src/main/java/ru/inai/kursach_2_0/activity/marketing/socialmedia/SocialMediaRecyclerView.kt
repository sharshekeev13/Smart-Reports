package ru.inai.kursach_2_0.activity.marketing.socialmedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.repo.models.SocialMediaModel
import ru.inai.kursach_2_0.repository.model.Budget

class SocialMediaRecyclerView(private val list : ArrayList<SocialMediaModel>,
                              val onClickListener : (ArrayList<SocialMediaModel>)->Unit)
                                : RecyclerView.Adapter<SocialMediaRecyclerView.MyViewHolder>() {
    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        val socialMediaIcon : ImageView = itemView.findViewById(R.id.social_media_icon)
        val membersIcon : ImageView = itemView.findViewById(R.id.members_icon)
        val membersTextView : TextView = itemView.findViewById(R.id.members_textview)
        val budgetIcon : ImageView = itemView.findViewById(R.id.budget_icon)
        val budgetTextView : TextView = itemView.findViewById(R.id.budget_textview)
        val tapToSpendTextView : TextView = itemView.findViewById(R.id.tap_to_spend_textview)
        val somString : String = itemView.context.getString(R.string.som)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.social_media_card,parent,false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(list[position].image.toString()).into(holder.socialMediaIcon)
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/1935/1935159.png").into(holder.membersIcon)
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/1761/1761422.png").into(holder.budgetIcon)
        holder.membersTextView.text = list[position].people.toString()
        val somText = list[position].money.toString() + holder.somString
        holder.budgetTextView.text = somText
        holder.tapToSpendTextView.visibility = View.VISIBLE
        holder.itemView.setOnClickListener {
            onClickListener(arrayListOf(list[position]))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}