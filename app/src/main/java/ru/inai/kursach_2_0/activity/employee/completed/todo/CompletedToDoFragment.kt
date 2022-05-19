package ru.inai.kursach_2_0.activity.employee.completed.todo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.databinding.FragmentCompletedToDoBinding
import ru.inai.kursach_2_0.repository.model.CompletedToDo

class CompletedToDoFragment : Fragment() {

    private var _binding : FragmentCompletedToDoBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: CompletedToDoViewModel
    private lateinit var adapter: CompletedToDoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompletedToDoBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = CompletedToDoViewModel(view.context)
        adapter = CompletedToDoAdapter(viewModel.getUserId())
        viewModel.getListCompletedToDo().observe(viewLifecycleOwner){
            adapter.putList(it)
            adapter.notifyDataSetChanged()
        }
        binding.completedToDoRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.completedToDoRecyclerView.adapter = adapter
    }

}