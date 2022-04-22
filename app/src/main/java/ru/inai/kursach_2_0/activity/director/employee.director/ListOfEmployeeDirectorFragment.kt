package ru.inai.kursach_2_0.activity.director.employee.director

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shashank.sony.fancytoastlib.FancyToast
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.databinding.FragmentListOfEmployeeDirectorBinding
import ru.inai.kursach_2_0.repo.models.ListOfEmployeeModel
import ru.inai.kursach_2_0.repo.models.UpdateUserInfo


class ListOfEmployeeDirectorFragment : Fragment() {

    private var _binding : FragmentListOfEmployeeDirectorBinding? = null
    private val binding get() = _binding!!
    private lateinit var directorAdapter : ListOfEmployeeDirectorAdapter
    private lateinit var viewModel : ListOfEmployeeViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfEmployeeDirectorBinding.inflate(inflater,container,false)
        return binding.root

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        directorAdapter = ListOfEmployeeDirectorAdapter(){
            alertDialogEmployee(it)
        }
        viewModel = ListOfEmployeeViewModel(requireView().context)
        binding.listOfEmployeeRecyclerView.layoutManager = LinearLayoutManager(requireView().context)
        binding.listOfEmployeeRecyclerView.adapter = directorAdapter
        binding.searchViewEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                directorAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        directorAdapter.clearLists()
        viewModel.getListEmployeeApi()
        viewModel.getListOfEmployee().observe(viewLifecycleOwner){
            directorAdapter.setData(it)
            directorAdapter.notifyDataSetChanged()
        }
        binding.swipeListOfEmployee.setOnRefreshListener {
            directorAdapter.clearLists()
            viewModel.getListEmployeeApi()
            viewModel.getListOfEmployee().observe(viewLifecycleOwner){
                directorAdapter.setData(it)
                directorAdapter.notifyDataSetChanged()
            }
            binding.swipeListOfEmployee.isRefreshing = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun alertDialogEmployee(updateNumber: ListOfEmployeeModel) {
        val builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(activity).inflate(R.layout.alert_dialog_employee,null)
        builder.setView(view)
        val cancelButton : Button = view.findViewById(R.id.close_button_employee)
        cancelButton.setOnClickListener {
            builder.dismiss()
        }
        val name : TextView = view.findViewById(R.id.name_employee_alert)
        name.text = updateNumber.name + " " + updateNumber.surname
        val role : TextView = view.findViewById(R.id.position_employee_alert)
        role.text = updateNumber.userRole
        val salary : TextView = view.findViewById(R.id.salary_num_to_edit)
        salary.text = updateNumber.salary.toString()
        val lowerButton : AppCompatButton = view.findViewById(R.id.lowerButtonEmployee)
        val raiseButton : AppCompatButton = view.findViewById(R.id.raise_button_employee)
        val number : EditText = view.findViewById(R.id.number_of_money_employee)
        lowerButton.setOnClickListener {
            if(number.text.toString().toInt()>updateNumber.salary){
                FancyToast.makeText(
                    view.context,
                    view.context.resources.getString(R.string.error),
                    FancyToast.LENGTH_LONG,FancyToast.ERROR,
                    false).show()
            }else{
                val sum = updateNumber.salary - number.text.toString().toInt()
                val body = UpdateUserInfo(updateNumber.name,updateNumber.password,sum,updateNumber.surname)
                viewModel.updateUser(updateNumber.id.toString(),body)
                FancyToast.makeText(
                    view.context,
                    view.context.resources.getString(R.string.successSalary),
                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,
                    false).show()
            }
        }
        raiseButton.setOnClickListener {
            if(number.text.toString().toInt()<0){
                FancyToast.makeText(
                    view.context,
                    view.context.resources.getString(R.string.error),
                    FancyToast.LENGTH_LONG,FancyToast.ERROR,
                    false).show()
            }else{
                val sum = updateNumber.salary + number.text.toString().toInt()
                val body = UpdateUserInfo(updateNumber.name,updateNumber.password,sum,updateNumber.surname)
                viewModel.updateUser(updateNumber.id.toString(),body)
                FancyToast.makeText(
                    view.context,
                    view.context.resources.getString(R.string.successSalary),
                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,
                    false).show()
                hideKeyboard()
            }
        }
        builder.setCanceledOnTouchOutside(true)
        builder.show()
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