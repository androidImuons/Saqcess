package com.saqcess.notification.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saqcess.notification.R
import com.saqcess.notification.adapter.NotificationAdapter
import com.saqcess.notification.datamodel.NotificationModel
import com.saqcess.notification.datamodel.NotificationsResponseModel
import com.saqcess.notification.listner.NotificationListner
import com.saqcess.notification.viewmodel.TokenUpdateViewModel

class MainActivity : AppCompatActivity(), NotificationListner {
    private lateinit var recyclerView: RecyclerView
    private lateinit var iv_close:ImageView
    private lateinit var notificationAdapter: NotificationAdapter
    var list: ArrayList<NotificationModel> = ArrayList();
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var ll_no_data_layer: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     //   setSupportActionBar(findViewById(R.id.toolbar))
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        initUI();
    }

    private fun initUI() {
        recyclerView = findViewById(R.id.recycle_view);
        notificationAdapter = NotificationAdapter(applicationContext, list);
        iv_close = findViewById<ImageView>(R.id.iv_close)
        ll_no_data_layer = findViewById(R.id.ll_no_data);
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = notificationAdapter;

        iv_close.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        getNotification();
    }

    private fun getNotification() {
        val tokenUpdateViewModel:TokenUpdateViewModel= TokenUpdateViewModel();
        tokenUpdateViewModel.notificationListner=this as NotificationListner;
        tokenUpdateViewModel.getNotification(applicationContext,"0");
    }



    override fun onsuccessResponse(notificationLiveData: MutableLiveData<NotificationsResponseModel>) {
        notificationLiveData.observe(this , Observer { notificationLiveData ->
            Log.d("data","---"+notificationLiveData.isError);
            if (notificationLiveData.isError){
                ll_no_data_layer.isVisible=true;
                recyclerView.isVisible=false;
            }else{
                ll_no_data_layer.isVisible=false;
                recyclerView.isVisible=true;
                updateList(notificationLiveData.data);
            }
        })
    }

    private fun updateList(data: List<NotificationModel>) {
        list= data as ArrayList<NotificationModel>;
        notificationAdapter.updateList(list);
    }


}