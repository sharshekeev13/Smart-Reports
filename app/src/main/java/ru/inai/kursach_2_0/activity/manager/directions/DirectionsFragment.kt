package ru.inai.kursach_2_0.activity.manager.directions

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.manager.directions.add.AddToDoFragment
import ru.inai.kursach_2_0.databinding.FragmentDirectionsBinding


class DirectionsFragment : Fragment() {

    private var _binding : FragmentDirectionsBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel : DirectionViewModel
    private lateinit var adapter : DirectionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDirectionsBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DirectionsAdapter()
        viewModel = DirectionViewModel()
        viewModel.getDirections().observe(viewLifecycleOwner){
            adapter.putList(it)
            adapter.notifyDataSetChanged()
        }
        binding.directionsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.directionsRecyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        viewModel.getAllToDoEmployee()
        viewModel.getDirections().observe(viewLifecycleOwner){
            adapter.putList(it)
            adapter.notifyDataSetChanged()
        }
        binding.directionsRefresh.setOnRefreshListener {
            viewModel.getAllToDoEmployee()
            viewModel.getDirections().observe(viewLifecycleOwner){
                adapter.putList(it)
                adapter.notifyDataSetChanged()
            }
            binding.directionsRefresh.isRefreshing = false
        }
        binding.itemAddDirection.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentPlaceHolder, AddToDoFragment())
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }
}