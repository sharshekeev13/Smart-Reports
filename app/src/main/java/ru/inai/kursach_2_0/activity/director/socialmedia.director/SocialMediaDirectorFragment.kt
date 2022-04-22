package ru.inai.kursach_2_0.activity.director.socialmedia.director


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.inai.kursach_2_0.activity.director.socialmedia.director.SocialMediaDirectorRecyclerView
import ru.inai.kursach_2_0.databinding.FragmentSocialMediaBinding

class SocialMediaDirectorFragment : Fragment() {

    private var _binding : FragmentSocialMediaBinding? = null
    private val binding get() = _binding!!
    private val viewModel = SocialMediaDirectorViewModel()
    private lateinit var adapter: SocialMediaDirectorRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SocialMediaDirectorRecyclerView()
        viewModel.getSocialMediaList().observe(viewLifecycleOwner){
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
        binding.recyclerViewSocialMedia.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)
        binding.recyclerViewSocialMedia.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        binding.swipeFragmentSocialMedia.setOnRefreshListener {
            viewModel.socialMediaRepo()
            viewModel.getSocialMediaList().observe(viewLifecycleOwner){
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            }
            binding.swipeFragmentSocialMedia.isRefreshing = false
        }
    }
}