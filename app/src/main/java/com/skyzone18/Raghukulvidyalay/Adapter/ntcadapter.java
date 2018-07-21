package com.skyzone18.Raghukulvidyalay.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyzone18.Raghukulvidyalay.JsonActivity.NoticeActivity;
import com.skyzone18.Raghukulvidyalay.Model.TouchImageView;
import com.skyzone18.Raghukulvidyalay.R;
import com.skyzone18.Raghukulvidyalay.Rest.Datum;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 1/30/2018.
 */

public class ntcadapter extends RecyclerView.Adapter<ntcadapter.ViewHolder> {

    List<Datum> ntclist;
    Context contet;
    String monthname;
    String day;

    public ntcadapter(NoticeActivity notic, ArrayList<Datum> ntclist) {
        this.ntclist = (List<Datum>) ntclist;
        this.contet = (Context) notic;
    }

    @Override
    public ntcadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_ntc_data, null);


        return new ViewHolder(itemview);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ntcadapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(ntclist.get(position).getNoticeName());
        ///  date setformat
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//        Date newDate = null;
//        try {
//            newDate = format.parse(String.valueOf(ntclist.get(position).getUpdatedOn()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        format = new SimpleDateFormat("dd-MM-yyyy");
//        String date = format.format(newDate);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        String newDateStr = null;
//        try {
//            newDateStr = simpleDateFormat.format(simpleDateFormat.parse(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String str[] = newDateStr.split("-");
//        day = str[0];
//        Integer month = Integer.valueOf(str[1]);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MONTH, month);
//        SimpleDateFormat sortDateFormat = new SimpleDateFormat("MMM");
//        sortDateFormat.setCalendar(calendar);
//        monthname = sortDateFormat.format(calendar.getTime());

        Glide.with(contet).load(ntclist.get(position).getImagePath() + ntclist.get(position).getNoticeImage())
                .into(holder.imageView);
        holder.name.setText(Html.fromHtml(ntclist.get(position).getNoticeDescription()));
//        holder.tvday.setText(day);
//        holder.tvmonth.setText(monthname);
        //   holder.tv_Date.setText(maindate);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_fullscreen_image);
                TouchImageView viewPager = (TouchImageView) dialog.findViewById(R.id.IMAGEID);
                Glide.with(contet).load(ntclist.get(position).getImagePath() + ntclist.get(position).getNoticeImage())
                        .into(viewPager);
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return ntclist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, tv_title;
        TextView tvday, tvmonth;
ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            name = (TextView) itemView.findViewById(R.id.news_dis);
            tvmonth = (TextView) itemView.findViewById(R.id.tv_month);
        }
    }
}