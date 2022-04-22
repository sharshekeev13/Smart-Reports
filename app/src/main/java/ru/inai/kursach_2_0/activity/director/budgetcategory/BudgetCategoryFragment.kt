package ru.inai.kursach_2_0.activity.director.budgetcategory

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.databinding.FragmentBudgetCategoryBinding
import ru.inai.kursach_2_0.repository.model.BudgetByCategory

class BudgetCategoryFragment : Fragment() {

    private var _binding : FragmentBudgetCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: BudgetByCategoryAdapter
    private lateinit var viewModel: BudgetCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BudgetByCategoryAdapter(view.context)
        viewModel = BudgetCategoryViewModel()
        viewModel.getListBudget().observe(viewLifecycleOwner){
            adapter.putList(it)
            adapter.notifyDataSetChanged()
        }
        binding.budgetByCategoryRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.budgetByCategoryRecyclerView.adapter = adapter
    }
}