package ru.inai.kursach_2_0.activity.marketing.socialmedia

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shashank.sony.fancytoastlib.FancyToast
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.databinding.FragmentSocialMediaBinding
import ru.inai.kursach_2_0.repo.models.SocialMediaModel
import ru.inai.kursach_2_0.repo.models.SocialMediaUpdateModel
import ru.inai.kursach_2_0.repository.model.Budget

class SocialMediaFragment : Fragment() {

    private var _binding : FragmentSocialMediaBinding? = null
    private val binding get() = _binding!!
    private val viewModel = SocialMediaViewModel()
    private var socialMediaList = arrayListOf<SocialMediaModel>()
    private lateinit var adapter: SocialMediaRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SocialMediaRecyclerView(socialMediaList){
            spendAlertDialog(it)
        }
        viewModel.getSocialMediaList().observe(viewLifecycleOwner){
            socialMediaList.clear()
            socialMediaList.addAll(it)
            adapter.notifyDataSetChanged()
        }
        binding.recyclerViewSocialMedia.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)
        binding.recyclerViewSocialMedia.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        binding.swipeFragmentSocialMedia.setOnRefreshListener {
            viewModel.socialMediaRepo()
            viewModel.getSocialMediaList().observe(viewLifecycleOwner){
                socialMediaList.clear()
                socialMediaList.addAll(it)
                adapter.notifyDataSetChanged()
                binding.swipeFragmentSocialMedia.isRefreshing = false
            }
        }
    }

    private fun spendAlertDialog(dataSM: ArrayList<SocialMediaModel>) {
            val builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
                .create()
            val view = LayoutInflater.from(activity).inflate(R.layout.alert_dialog_spend_money,null)
            builder.setView(view)
            val socialMediaName : TextView = view.findViewById(R.id.social_media_name)
            socialMediaName.text = dataSM[0].social
            val socialMediaBudget : TextView = view.findViewById(R.id.social_media_budget)
            socialMediaBudget.text = dataSM[0].money.toString()
            val editTextSpend : EditText = view.findViewById(R.id.number_of_money)
            val yesButton : Button = view.findViewById(R.id.spend_money_spend_button)
            yesButton.setOnClickListener {
                val numberEditText = editTextSpend.text.toString().toInt()
                if (numberEditText>dataSM[0].money){
                    FancyToast.makeText(
                        view.context,
                        view.context.resources.getString(R.string.error),
                        FancyToast.LENGTH_LONG,FancyToast.ERROR,
                        false).show()
                }else{
                    val money = dataSM[0].money - numberEditText
                    val body = SocialMediaUpdateModel(money,dataSM[0].people,dataSM[0].social)
                    updateSocialMediaBudget(dataSM[0].id.toString(),body)
                    builder.dismiss()
                    FancyToast.makeText(
                        view.context,
                        view.context.resources.getString(R.string.good),
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        false).show()
                }

            }
            val cancelButton : Button = view.findViewById(R.id.spend_money_cancel_button)
            cancelButton.setOnClickListener {
                builder.dismiss()
            }
            builder.setCanceledOnTouchOutside(true)
            builder.show()
    }

    private fun updateSocialMediaBudget(id : String,body : SocialMediaUpdateModel){
        viewModel.updateSocialMedia(id,body)
    }
}