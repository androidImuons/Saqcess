package com.saqcess.notification.adapter

import android.content.Context
import android.content.Intent
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.saqcess.notification.R
import com.saqcess.notification.datamodel.NotificationModel
import com.saqcess.notification.view.ImageViewActivity
import com.saqcess.notification.view.MessageDetailsActivity


class NotificationAdapter(
    applicationContext: Context,
    list: ArrayList<NotificationModel>
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    var list: ArrayList<NotificationModel>? = null
    var context: Context? = null

    init {
        this.list = list;
        this.context = applicationContext;
    }

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtmsgTtile: TextView? = null
        var txtmsg: TextView? = null
        var tv_date: TextView? = null
        var sdvImage: SimpleDraweeView? = null
        var cardview: CardView? = null
        var rl_image_view: RelativeLayout? = null;
        var imageview:SimpleDraweeView?=null;

        init {
            txtmsgTtile = itemView.findViewById(R.id.tv_title);
            txtmsg = itemView.findViewById(R.id.tv_message);
            tv_date = itemView.findViewById(R.id.tv_date);
            sdvImage = itemView.findViewById(R.id.svg_image)
            cardview = itemView.findViewById(R.id.cv_data);
            rl_image_view=itemView.findViewById(R.id.rl_image);
            rl_image_view=itemView.findViewById(R.id.rl_image);
            imageview=itemView.findViewById(R.id.imageview);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_notification_view, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setData(list!!.get(position), holder, position);
        holder.cardview!!.setOnClickListener {
            val nextScreenIntent = Intent(context, MessageDetailsActivity::class.java).apply {
                putExtra("image", list!!.get(position).image_large)
                putExtra("date", list!!.get(position).createdDateText)
                putExtra("title", list!!.get(position).title)
                putExtra("msg", list!!.get(position).notification)
                setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context!!.startActivity(nextScreenIntent)
        }
    }

    private fun setData(
        get: NotificationModel,
        holder: ViewHolder,
        position: Int
    ) {
        holder.txtmsgTtile!!.setText(get.title);
        holder.txtmsg!!.setText(get.notification);
        holder.tv_date!!.setText(get.createdDateText)

        val roundingParams = RoundingParams.fromCornersRadius(5f)
        roundingParams.setBorder(context!!.getResources().getColor(R.color.colorBlack), 1.0f)
        roundingParams.roundAsCircle = true
        holder.sdvImage!!.getHierarchy().setRoundingParams(roundingParams)

        holder.sdvImage!!.setBackgroundResource(R.drawable.ic_logo)
        if (get.image_thumb.equals("")){
            holder.rl_image_view!!.setVisibility(View.GONE)
        }else{
            holder.rl_image_view!!.setVisibility(View.VISIBLE)
            holder.imageview!!.setImageURI(get.image_large)
            holder.imageview!!.setOnClickListener { view ->
                val nextScreenIntent = Intent(context, ImageViewActivity::class.java).apply {
                    putExtra("image", list!!.get(position).image_large)
                    putExtra("date", list!!.get(position).createdDateText)
                    putExtra("title", list!!.get(position).title)
                    putExtra("msg", list!!.get(position).notification)
                    setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context!!.startActivity(nextScreenIntent)
            }
        }

        holder.txtmsg!!.setMovementMethod(LinkMovementMethod.getInstance());
        holder.txtmsg!!.setLinkTextColor(this.context!!.resources.getColor(R.color.colorPrimary));
        Linkify.addLinks(holder!!.txtmsg!!, Linkify.WEB_URLS)
    }

    fun updateList(list1: java.util.ArrayList<NotificationModel>) {
        this.list = list1;
        notifyDataSetChanged();
    }


}