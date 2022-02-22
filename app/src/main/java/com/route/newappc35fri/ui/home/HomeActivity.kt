package com.route.newappc35fri.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.route.newappc35fri.R
import com.route.newappc35fri.ui.categories.CategoriesFragment
import com.route.newappc35fri.ui.categories.Category
import com.route.newappc35fri.ui.news.NewsFragment
import com.route.newappc35fri.ui.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val categoriesFragment = CategoriesFragment();
    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarLayout: AppBarLayout
    lateinit var drawerIcon: ImageView
    lateinit var settings: View
    lateinit var categories: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerIcon = findViewById(R.id.drawer_icon)
        categories = findViewById(R.id.categories)
        settings = findViewById(R.id.settings)
        pushFragment(categoriesFragment)

        categoriesFragment.onCategoryClickListener =
            object : CategoriesFragment.OnCategoryClickListener {
                override fun onCategoryClick(category: Category) {
                    pushFragment(NewsFragment.getInstance(category), true);
                }
            }
        drawerIcon.setOnClickListener {
            drawerLayout.open()
        }
        categories.setOnClickListener {
            pushFragment(categoriesFragment)
        }
        settings.setOnClickListener {
            pushFragment(SettingsFragment())
        }

    }

    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragTransction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (addToBackStack)
            fragTransction.addToBackStack("");
        fragTransction.commit()
        drawerLayout.close()
    }

}