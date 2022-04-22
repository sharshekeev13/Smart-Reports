package ru.inai.kursach_2_0.activity.marketing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.inai.kursach_2_0.activity.marketing.area.AreaFragment
import ru.inai.kursach_2_0.activity.marketing.socialmedia.SocialMediaFragment

class ViewPagerAdapterMarketing (fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        if(position==1){
            return SocialMediaFragment()
        }else{
            return AreaFragment()
        }
    }
}