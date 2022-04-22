package ru.inai.kursach_2_0.activity.manager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.inai.kursach_2_0.activity.manager.directions.DirectionsFragment
import ru.inai.kursach_2_0.activity.manager.list.employee.ListOfEmployeeFragment
import ru.inai.kursach_2_0.activity.manager.my.todo.EmployeeToDoListFragment
import ru.inai.kursach_2_0.activity.marketing.area.AreaFragment

class ManagerViewPager (fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
            when(position) {
                0 -> return ListOfEmployeeFragment()
                1 -> return EmployeeToDoListFragment()
                2 -> return DirectionsFragment()
                3 -> return AreaFragment()
            }
        return ListOfEmployeeFragment()
    }
}