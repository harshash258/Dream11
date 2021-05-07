package com.mycompany.dream11

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.willy.ratingbar.ScaleRatingBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var wkRecyclerView: RecyclerView? = null
    private var batsmanRecyclerView: RecyclerView? = null
    private var bowlerRecyclerView: RecyclerView? = null
    private var allRounderRecyclerView: RecyclerView? = null
    private var wkAdapter: WKAdapter? = null
    private var batAdapter: WKAdapter? = null
    private var bowlAdapter: WKAdapter? = null
    private var allAdapter: WKAdapter? = null
    private var wkList: MutableList<String> = mutableListOf()
    private var batList: MutableList<String> = mutableListOf()
    private var bowlList: MutableList<String> = mutableListOf()
    private var allList: MutableList<String> = mutableListOf()
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var wk: TextView
    private lateinit var bats: TextView
    private lateinit var bowl: TextView
    private lateinit var all: TextView
    private lateinit var ratingBar: ScaleRatingBar

    var max = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        fillList()
        initVariables()

        /*wk.setOnTouchListener(OnSwipeTouchListener(this){
            performClick
            fun onSwipeTop() {
                Toast.makeText(this@MyActivity, "top", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeRight() {
                Toast.makeText(this@MyActivity, "right", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeLeft() {
                Toast.makeText(this@MyActivity, "left", Toast.LENGTH_SHORT).show()
            }

            fun onSwipeBottom() {
                Toast.makeText(this@MyActivity, "bottom", Toast.LENGTH_SHORT).show()
            }
        })*/
        wk.setOnClickListener {
            viewFlipper.displayedChild = 0
            changeColor(wk)
            changeBackToNormal(bats, bowl, all)
        }
        bats.setOnClickListener {
            viewFlipper.displayedChild = 1
            changeColor(bats)
            changeBackToNormal(wk, bowl, all)
        }
        bowl.setOnClickListener {
            viewFlipper.displayedChild = 3
            changeColor(bowl)
            changeBackToNormal(bats, wk, all)
        }
        all.setOnClickListener {
            viewFlipper.displayedChild = 2
            changeColor(all)
            changeBackToNormal(bats, bowl, wk)
        }
    }

    private fun fillList() {
        GlobalScope.launch {
            wkList.add("Ms Dhoni")
            wkList.add("Quinton De Cock")
            wkList.add("Sanju Samson")
            wkList.add("Jos Butler")
            wkList.add("Jhonny Bairstow")
        }
        wkAdapter = WKAdapter(wkList, object : WKAdapter.OnClick {
            override fun click(position: Int, itemView: View, selectPlayer: ImageView, b: Boolean) {
                if (b) {
                    itemView.setBackgroundResource(R.drawable.selected_player)
                    selectPlayer.setImageResource(R.drawable.ic_close)
                    max++
                    ratingBar.rating++
                    if (max >= 2){

                    }
                    Log.d("MAX", max.toString())
                } else {
                    itemView.setBackgroundResource(0)
                    selectPlayer.setImageResource(R.drawable.ic_plus)
                    max--
                    ratingBar.rating--
                    Log.d("MAX", max.toString())
                }
            }

        })

        GlobalScope.launch {
            batList.add("Rohit Sharma")
            batList.add("Virat Kholi")
            batList.add("Rahul Dravid")
            batList.add("Suresh Raina")
            batList.add("David Malan")
            batList.add("Surya Kumar Yadav")
            batList.add("Rishab Pant")
            batList.add("Shaktiman")
        }

        batAdapter = WKAdapter(batList, object : WKAdapter.OnClick {
            override fun click(
                position: Int,
                itemView: View,
                selectPlayer: ImageView,
                b: Boolean,
            ) {

            }

        })

        GlobalScope.launch {
            allList.add("Kieron Pollard")
            allList.add("Hardik Pandhya")
            allList.add("Krunal Pandhya")
            allList.add("Chris Morris")
            allList.add("Loda Lassson")
        }

        allAdapter = WKAdapter(allList, object : WKAdapter.OnClick {
            override fun click(
                position: Int,
                itemView: View,
                selectPlayer: ImageView,
                b: Boolean,
            ) {

            }

        })
        GlobalScope.launch {
            bowlList.add("Rohit Sharma")
            bowlList.add("Virat Kholi")
            bowlList.add("Rahul Dravid")
            bowlList.add("Suresh Raina")
            bowlList.add("David Malan")
            bowlList.add("Surya Kumar Yadav")
            bowlList.add("Rishab Pant")
            bowlList.add("Shaktiman")
        }
        bowlAdapter = WKAdapter(bowlList, object : WKAdapter.OnClick {
            override fun click(
                position: Int,
                itemView: View,
                selectPlayer: ImageView,
                b: Boolean,
            ) {
            }

        })
    }

    private fun initVariables() {
        viewFlipper = findViewById(R.id.viewFlipper)
        wk = findViewById(R.id.wk)
        bats = findViewById(R.id.batsman)
        bowl = findViewById(R.id.bowlers)
        all = findViewById(R.id.all_rounders)
        ratingBar = findViewById(R.id.ratingBar)

        changeColor(wk)

        wkRecyclerView = findViewById(R.id.wk_recyclerView)
        wkRecyclerView?.setHasFixedSize(false)
        wkRecyclerView?.layoutManager = LinearLayoutManager(this)
        wkRecyclerView?.adapter = wkAdapter

        batsmanRecyclerView = findViewById(R.id.batsman_recyclerView)
        batsmanRecyclerView?.setHasFixedSize(false)
        batsmanRecyclerView?.layoutManager = LinearLayoutManager(this)
        batsmanRecyclerView?.adapter = batAdapter

        bowlerRecyclerView = findViewById(R.id.bowler_recyclerView)
        bowlerRecyclerView?.setHasFixedSize(false)
        bowlerRecyclerView?.layoutManager = LinearLayoutManager(this)
        bowlerRecyclerView?.adapter = bowlAdapter

        allRounderRecyclerView = findViewById(R.id.all_recyclerView)
        allRounderRecyclerView?.setHasFixedSize(false)
        allRounderRecyclerView?.layoutManager = LinearLayoutManager(this)
        allRounderRecyclerView?.adapter = allAdapter

        wkAdapter?.notifyDataSetChanged()
        batAdapter?.notifyDataSetChanged()
        bowlAdapter?.notifyDataSetChanged()
        allAdapter?.notifyDataSetChanged()
    }

    private fun changeColor(textView: TextView) {
        textView.setTextColor(Color.parseColor("#FFFF0000"))
        textView.setBackgroundResource(R.drawable.underline)
    }

    private fun changeBackToNormal(textView: TextView, textView2: TextView, textView3: TextView) {
        textView.setTextColor(Color.parseColor("#000000"))
        textView2.setTextColor(Color.parseColor("#000000"))
        textView3.setTextColor(Color.parseColor("#000000"))
        textView.setBackgroundResource(0)
        textView2.setBackgroundResource(0)
        textView3.setBackgroundResource(0)
    }

}