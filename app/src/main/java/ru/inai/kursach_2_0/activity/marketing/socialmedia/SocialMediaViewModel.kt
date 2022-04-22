package ru.inai.kursach_2_0.activity.marketing.socialmedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.SocialMediaModel
import ru.inai.kursach_2_0.repo.models.SocialMediaUpdateModel
import ru.inai.kursach_2_0.repository.api.RepositoryAPI
import ru.inai.kursach_2_0.repository.model.Budget

class SocialMediaViewModel : ViewModel() {
    private var socialMediaLiveData = MutableLiveData<ArrayList<SocialMediaModel>>()
    fun getSocialMediaList() = socialMediaLiveData
    private val repo = Repository()

    init {
        socialMediaRepo()
    }

    fun socialMediaRepo() {
        viewModelScope.launch {
            val response = repo.getAllSocialMedia()
            socialMediaLiveData.postValue(response!!)
        }
    }

    fun updateSocialMedia(id : String,body:SocialMediaUpdateModel){
        viewModelScope.launch {
            val response = repo.updateSocialMedia(id,body)
        }
    }
}