package ru.inai.kursach_2_0.activity.director

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.inai.kursach_2_0.activity.director.budgetcategory.BudgetCategoryFragment
import ru.inai.kursach_2_0.activity.director.employee.director.ListOfEmployeeDirectorFragment
import ru.inai.kursach_2_0.activity.director.socialmedia.director.SocialMediaDirectorFragment
import ru.inai.kursach_2_0.activity.manager.my.todo.EmployeeToDoListFragment
import ru.inai.kursach_2_0.activity.marketing.area.AreaFragment
import ru.inai.kursach_2_0.activity.marketing.socialmedia.SocialMediaFragment

class DirectorViewPager (fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return AreaFragment()
            1 -> return BudgetCategoryFragment()
            2 -> return SocialMediaDirectorFragment()
            3 -> return ListOfEmployeeDirectorFragment()
        }
        return EmployeeToDoListFragment()
    }
}