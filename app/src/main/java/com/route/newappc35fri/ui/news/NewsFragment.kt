package com.route.newappc35fri.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.route.newappc35fri.Constants
import com.route.newappc35fri.R
import com.route.newappc35fri.api.ApiManager
import com.route.newappc35fri.databinding.FragmentNewsBinding
import com.route.newappc35fri.model.NewsResponse
import com.route.newappc35fri.model.SourcesItem
import com.route.newappc35fri.model.SourcesResponse
import com.route.newappc35fri.ui.categories.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category;
            return fragment;
        }
    }

    lateinit var category: Category
    lateinit var viewDataBinding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate<FragmentNewsBinding>(
            layoutInflater,
            R.layout.fragment_news, container, false
        );
        return viewDataBinding.root
        // return inflater.inflate(R.layout.fragment_news, container, false);
    }

    lateinit var viewModel: NewsViewModel
    val adapter = NewsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView();
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        subscribeToLivedata()
        viewModel.getNewsSources(category)
    }

    private fun subscribeToLivedata() {
        viewModel.progressBarVisible.observe(viewLifecycleOwner, Observer { isVisible ->
//            if(isVisible)
//                progressBar.visibility = View.VISIBLE
//            else progressBar.visibility = View.GONE
//
            viewDataBinding.progressBar.isVisible = isVisible
        })
        viewModel.sourcesLiveData.observe(viewLifecycleOwner, Observer { data ->
            showTabs(data)
        })
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            adapter.changeData(it)
        })
    }

    private fun initView() {
        viewDataBinding.recyclerView.adapter = adapter
    }


    private fun showTabs(sources: List<SourcesItem?>?) {
        sources?.forEach { item ->
            val tab = viewDataBinding.tablayout.newTab()
            tab.setTag(item)
            tab.setText(item?.name)
            viewDataBinding.tablayout.addTab(tab)
        }
        viewDataBinding.tablayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // val source =  sources?.get(tab?.position?:0)
                    val source = tab?.tag as SourcesItem
                    viewModel.loadNews(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    viewModel.loadNews(source)
                }
            }
        )
        viewDataBinding.tablayout.getTabAt(0)?.select()

    }


}