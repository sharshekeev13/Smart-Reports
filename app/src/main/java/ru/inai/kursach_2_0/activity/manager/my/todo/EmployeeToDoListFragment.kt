package ru.inai.kursach_2_0.activity.manager.my.todo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.databinding.FragmentEmployeeToDoListBinding
import ru.inai.kursach_2_0.repository.model.ToDoListModelItem

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
        adapter = EmployeeToDoListAdapter()
        viewModel = EmployeeToDoListViewModel(view.context)
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.todoRecyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        viewModel.getToDoList().observe(viewLifecycleOwner){
            adapter.putList(it)
            adapter.notifyDataSetChanged()
        }
    }
}