package ru.inai.kursach_2_0.activity.manager.list.employee

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inai.kursach_2_0.activity.director.employee.director.ListOfEmployeeViewModel
import ru.inai.kursach_2_0.databinding.FragmentListOfEmployeeBinding

class ListOfEmployeeFragment : Fragment() {

    private var _binding : FragmentListOfEmployeeBinding? = null
    private val binding get() = _binding!!
    private lateinit var directorAdapter : ListOfEmployeeAdapter
    private lateinit var viewModel : ListOfEmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfEmployeeBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        directorAdapter = ListOfEmployeeAdapter()
        viewModel = ListOfEmployeeViewModel(view.context)
        viewModel.getListOfEmployee().observe(viewLifecycleOwner){
            directorAdapter.setData(it)
            directorAdapter.notifyDataSetChanged()
        }
        binding.listOfEmployeeRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.listOfEmployeeRecyclerView.adapter = directorAdapter
        binding.searchViewEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                directorAdapter.filter.filter(p0)
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    override fun onPause() {
        hideKeyboard()
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("CHECK","Destroy")
        super.onDestroy()
        _binding = null
    }

    private fun hideKeyboard(){
        val activity = activity
        val view = activity?.currentFocus
        if (view != null){
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken,0)
        }
    }

}