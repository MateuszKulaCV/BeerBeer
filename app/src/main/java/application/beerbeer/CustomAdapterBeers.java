package application.beerbeer;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KOMPUTOR on 2016-05-17.
 */
public class CustomAdapterBeers extends BaseAdapter {
    private List<Response.BeersBean> beers;
    private Context context;
    private LayoutInflater layoutInflater;
    ImageView bottle1,bottle2,bottle3,bottle4,bottle5;
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
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.rowbeers, parent,false);
        TextView beer = (TextView) rowView.findViewById(R.id.textViewbeers);
        /**
         * setting custom progressbar
         */
        bottle1 = (ImageView) rowView.findViewById(R.id.progimageView1);
        bottle2 = (ImageView) rowView.findViewById(R.id.progimageView2);
        bottle3 = (ImageView) rowView.findViewById(R.id.progimageView3);
        bottle4 = (ImageView) rowView.findViewById(R.id.progimageView4);
        bottle5 = (ImageView) rowView.findViewById(R.id.progimageView5);


       // ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.progbar);
        Response.BeersBean item = (Response.BeersBean) getItem(position);
        setProgressbar(Integer.parseInt(item.getProgress()));
        beer.setText(item.getPiwo());
       // progressBar.setProgress(Integer.parseInt(item.getProgress()));

        return rowView;
    }

    void setProgressbar(int progress)
    {
                     if(progress>=0 && progress<=20){
            bottle1.setImageResource(R.drawable.progresson);

        }else        if(progress>=21 && progress<=40){
            bottle1.setImageResource(R.drawable.progresson);
            bottle2.setImageResource(R.drawable.progresson);
        }else        if(progress>=41 && progress<=60){
            bottle1.setImageResource(R.drawable.progresson);
            bottle2.setImageResource(R.drawable.progresson);
            bottle3.setImageResource(R.drawable.progresson);
        }else        if(progress>=61 && progress<=80){
            bottle1.setImageResource(R.drawable.progresson);
            bottle2.setImageResource(R.drawable.progresson);
            bottle3.setImageResource(R.drawable.progresson);
            bottle4.setImageResource(R.drawable.progresson);
        }else
        {
            bottle1.setImageResource(R.drawable.progresson);
            bottle2.setImageResource(R.drawable.progresson);
            bottle3.setImageResource(R.drawable.progresson);
            bottle4.setImageResource(R.drawable.progresson);
            bottle5.setImageResource(R.drawable.progresson);
        }

    }
}
