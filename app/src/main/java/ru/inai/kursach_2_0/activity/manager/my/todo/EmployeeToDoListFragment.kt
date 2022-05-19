package ru.inai.kursach_2_0.activity.manager.my.todo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.manager.directions.AllDirectionsModel
import ru.inai.kursach_2_0.databinding.FragmentEmployeeToDoListBinding

class EmployeeToDoListFragment : Fragment() {

    private var _binding : FragmentEmployeeToDoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : EmployeeToDoListViewModel
    private lateinit var adapter : EmployeeToDoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeToDoListBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = EmployeeToDoListViewModel(view.context)
        adapter = EmployeeToDoListAdapter(refreshList(),view.context,viewModel.getUserId()){
            openAlertDialog(it)
        }
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.todoRecyclerView.adapter = adapter
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        refreshList()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshList() {
        viewModel.getToDoList().observe(viewLifecycleOwner){
            adapter.putList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun openAlertDialog(it: ArrayList<AllDirectionsModel>) {

        val builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(activity).inflate(R.layout.fragment_to_do_info,null)
        builder.setView(view)

        val icon : ImageView = view.findViewById(R.id.todoInfoIcon)
        val title : TextView = view.findViewById(R.id.todoInfoTitle)
        val description : TextView = view.findViewById(R.id.todoInfoDescription)
        val date : TextView = view.findViewById(R.id.todoInfoDate)
        val cancelButton : Button = view.findViewById(R.id.todoInfoCloseButton)

        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/2833/2833457.png").into(icon)
        title.text = it[0].title
        description.text = it[0].description
        date.text = it[0].date
        cancelButton.setOnClickListener {
            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }

}