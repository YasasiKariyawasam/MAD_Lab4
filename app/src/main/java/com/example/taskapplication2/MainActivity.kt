package com.example.taskapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapplication2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private  lateinit var binding: ActivityMainBinding
   private lateinit var db:TaskDatabaseHelper
   private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= TaskDatabaseHelper(this)
        taskAdapter= TaskAdapter(db.getAllTasks(),this)

        binding.tasksRecyclecyleView.layoutManager=LinearLayoutManager(this)
        binding.tasksRecyclecyleView.adapter=taskAdapter



        binding.addButton.setOnClickListener{
            val intent= Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume(){
        super.onResume()
        taskAdapter.refreshData(db.getAllTasks())
    }
}