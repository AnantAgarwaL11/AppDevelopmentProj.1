package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpRecyclerView()
        binding.search.setOnClickListener(){
            startActivity(Intent(this,SearchActivity::class.java))
        }
    }
        private fun setUpRecyclerView(){
            dataList= ArrayList()
            binding.rvPolular.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            var db= Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java,"db_name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .createFromAsset("recipe.db")
                .build()
            var daoObject=db.getDao()
            var recipes=daoObject.getAll()
            for(i in recipes!!.indices){
                if(recipes[i]!!.category.contains("Popular")){
                    dataList.add(recipes[i]!!)
                }
                rvAdapter=PopularAdapter(dataList,this)
                binding.rvPolular.adapter=rvAdapter
            }
        }

    }
