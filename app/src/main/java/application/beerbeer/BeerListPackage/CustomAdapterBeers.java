package application.beerbeer.BeerListPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import application.beerbeer.R;
import application.beerbeer.ResponsePack.Response;

/**
 * Created by KOMPUTOR on 2016-05-17.
 */
public class CustomAdapterBeers extends BaseAdapter {
    private List<Response.BeersBean> beers;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapterBeers(Context context, List<Response.BeersBean> beers) {
        this.context = context;
        this.beers = beers;
    }


    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int position) {
        return beers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.rowbeers, parent,false);
            holder.beer = (TextView) convertView.findViewById(R.id.textViewbeers);
            /**
             * setting custom progressbar
             */
            holder.bottle1 = (ImageView) convertView.findViewById(R.id.progimageView1);
            holder.bottle2 = (ImageView) convertView.findViewById(R.id.progimageView2);
            holder.bottle3 = (ImageView) convertView.findViewById(R.id.progimageView3);
            holder.bottle4 = (ImageView) convertView.findViewById(R.id.progimageView4);
            holder.bottle5 = (ImageView) convertView.findViewById(R.id.progimageView5);

            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }




        Response.BeersBean item = (Response.BeersBean) getItem(position);
        setProgressbar(Integer.parseInt(item.getProgress()),holder);
        holder.beer.setText(item.getPiwo());


        return convertView;
    }

    /**
     * setting bottles visibility depending on progress
     * @param progress
     * @param holder
     */
    void setProgressbar(int progress, ViewHolder holder)
    {
                     if(progress>=0 && progress<=20){
            holder.bottle1.setImageResource(R.drawable.progresson);

        }else        if(progress>=21 && progress<=40){
            holder.bottle1.setImageResource(R.drawable.progresson);
            holder.bottle2.setImageResource(R.drawable.progresson);
        }else        if(progress>=41 && progress<=60){
            holder.bottle1.setImageResource(R.drawable.progresson);
            holder.bottle2.setImageResource(R.drawable.progresson);
            holder.bottle3.setImageResource(R.drawable.progresson);
        }else        if(progress>=61 && progress<=80){
            holder.bottle1.setImageResource(R.drawable.progresson);
            holder.bottle2.setImageResource(R.drawable.progresson);
            holder.bottle3.setImageResource(R.drawable.progresson);
            holder.bottle4.setImageResource(R.drawable.progresson);
        }else
        {
            holder.bottle1.setImageResource(R.drawable.progresson);
            holder.bottle2.setImageResource(R.drawable.progresson);
            holder.bottle3.setImageResource(R.drawable.progresson);
            holder.bottle4.setImageResource(R.drawable.progresson);
            holder.bottle5.setImageResource(R.drawable.progresson);
        }

    }

    private static class ViewHolder
    {
        ImageView bottle1,bottle2,bottle3,bottle4,bottle5;
        TextView beer;
    }
}

