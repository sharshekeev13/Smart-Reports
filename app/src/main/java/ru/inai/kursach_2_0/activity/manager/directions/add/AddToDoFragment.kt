package ru.inai.kursach_2_0.activity.manager.directions.add

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shashank.sony.fancytoastlib.FancyToast
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.manager.ActivityManager
import ru.inai.kursach_2_0.activity.manager.directions.DirectionViewModel
import ru.inai.kursach_2_0.activity.manager.directions.ListOfEmployeeSpinnerAdapter
import ru.inai.kursach_2_0.activity.manager.list.employee.ListOfEmployeeViewModel
import ru.inai.kursach_2_0.databinding.FragmentAddToDoBinding
import ru.inai.kursach_2_0.utils.SharedPreference

class AddToDoFragment : Fragment() {

    private var _binding : FragmentAddToDoBinding? = null
    private val binding get() = _binding!!
    private var idEmployee = 0
    private lateinit var viewModel : DirectionViewModel
    private lateinit var sharedPreference: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddToDoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = DirectionViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = SharedPreference(requireView().context)
    }

    override fun onResume() {
        super.onResume()
        binding.closeButtonEmployeeAlert.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .remove(this).commit()
        }
        binding.selectEmployeeButtonAlert.setOnClickListener {
            spinnerEmployee()
        }
        binding.addButtonEmployeeAlert.setOnClickListener {
            Log.d("TIMALOH",idEmployee.toString())
            if(idEmployee != 0){
                viewModel.addToDoForEmployee(binding.descriptionEditTextAlert.text.toString(),
                    idEmployee,
                    sharedPreference.getId(),
                    binding.titleEditTextAlert.text.toString())
                FancyToast.makeText(
                    requireView().context,
                    requireView().context.getString(R.string.successAdd),
                    FancyToast.LENGTH_SHORT,
                    FancyToast.SUCCESS,
                    false).show()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .remove(this).commit()
            }else{
                FancyToast.makeText(
                    requireView().context,
                    requireView().context.getString(R.string.please_select_employee),
                    FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR,
                    false).show()
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun spinnerEmployee(){
        val dialog = Dialog(requireView().context)
        dialog.setContentView(R.layout.spinner_employees)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val employeeRecyclerViewSpinner : RecyclerView = dialog.findViewById(R.id.employee_recyclerView_spinner)
        employeeRecyclerViewSpinner.layoutManager = LinearLayoutManager(requireView().context)
        val adapterDialog = ListOfEmployeeSpinnerAdapter(){
            val name = it.name + " " + it.surname
            binding.selectEmployeeButtonAlert.text = name
            idEmployee = it.id!!
            dialog.dismiss()
        }
        employeeRecyclerViewSpinner.adapter = adapterDialog
        val viewModelListOfEmployee = ListOfEmployeeViewModel(requireView().context)
        viewModelListOfEmployee.getListOfEmployee().observe(viewLifecycleOwner){
            adapterDialog.setData(it)
            adapterDialog.notifyDataSetChanged()
        }
        val searchEmployee : EditText = dialog.findViewById(R.id.search_employee)
        searchEmployee.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapterDialog.filter.filter(p0)
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    companion object {
        fun newInstance() = AddToDoFragment()
    }
}