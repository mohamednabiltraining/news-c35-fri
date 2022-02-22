package com.route.newappc35fri.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newappc35fri.Constants
import com.route.newappc35fri.database.MyDataBase
import com.route.newappc35fri.model.ArticlesItem
import com.route.newappc35fri.model.SourcesItem
import com.route.newappc35fri.repos.news.NewsOnlineDataSource
import com.route.newappc35fri.repos.news.NewsOnlineDataSourceImpl
import com.route.newappc35fri.repos.news.NewsRepository
import com.route.newappc35fri.repos.news.NewsRepositoryImpl
import com.route.newappc35fri.repos.source.*
import com.route.newappc35fri.ui.categories.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsRepository: NewsRepository,
    val sourcesRepository: SourcesRepository
) : ViewModel() {
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val progressBarVisible = MutableLiveData<Boolean>(false)
    val newsList = MutableLiveData<List<ArticlesItem?>?>()

    fun getNewsSources(category: Category) {
        viewModelScope.launch {
            try {
                progressBarVisible.value = true
                val result = sourcesRepository.getSources(category.id)
                progressBarVisible.value = false;
                sourcesLiveData.value = result
            } catch (ex: Exception) {
                progressBarVisible.value = false;

            }
//                    Log.e("response", response.body().toString())

        }
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    progressBarVisible.value = false;
//                }
//
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    progressBarVisible.value = false;
//                    sourcesLiveData.value = response.body()?.sources
//                    Log.e("response", response.body().toString())
//                }
//            })
    }

    fun loadNews(source: SourcesItem) {

        viewModelScope.launch {
            try {
                progressBarVisible.value = true;
                val result = newsRepository.getNews(source.id)
                progressBarVisible.value = false;
                newsList.value = result
            } catch (ex: Exception) {
                progressBarVisible.value = false;
            }

        }
//            .enqueue(object : Callback<NewsResponse> {
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
////                    Toast.makeText(
////                        requireContext(), "Error loading news",
////                        Toast.LENGTH_LONG
////                    ).show()
//                    progressBarVisible.value = false;
//
//                }
//
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    progressBarVisible.value = false;
//                    newsList.value = response.body()?.articles
//                }
//            })
    }

}