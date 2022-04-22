package ru.inai.kursach_2_0.activity.marketing.area

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.inai.kursach_2_0.databinding.FragmentAreaBinding

class AreaFragment : Fragment() {

    private var _binding : FragmentAreaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : AreaRecyclerView
    private val viewModel = AreaViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreaBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AreaRecyclerView(view.context)
        binding.recyclerViewArea.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)
        viewModel.getAreaList().observe(viewLifecycleOwner){
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        }
        binding.recyclerViewArea.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        binding.areaRefresh.setOnRefreshListener {
            viewModel.getAreaList().observe(viewLifecycleOwner){
                adapter.setData(it)
                adapter.notifyDataSetChanged()
            }
            binding.areaRefresh.isRefreshing = false
        }
    }

}