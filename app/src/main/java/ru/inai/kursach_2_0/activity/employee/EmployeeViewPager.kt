package ru.inai.kursach_2_0.activity.employee

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.inai.kursach_2_0.activity.employee.completed.todo.CompletedToDoFragment
import ru.inai.kursach_2_0.activity.manager.my.todo.EmployeeToDoListFragment

class EmployeeViewPager (fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
            when(position) {
                0 -> return EmployeeToDoListFragment()
                1 -> return CompletedToDoFragment()
            }
        return EmployeeToDoListFragment()
    }
}