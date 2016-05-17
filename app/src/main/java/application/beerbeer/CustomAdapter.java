package application.beerbeer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KOMPUTOR on 2016-05-16.
 */
public class CustomAdapter extends BaseAdapter{
    private List<Response.PubsBean> pubs;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, List<Response.PubsBean> pubs) {
        this.context = context;
        this.pubs = pubs;
    }



    @Override
    public int getCount() {
        return pubs.size();
    }

    @Override
    public Object getItem(int position) {
        return pubs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = layoutInflater.inflate(R.layout.rowpubs, parent, false);
            TextView pub = (TextView) rowView.findViewById(R.id.textView);
            ImageView pubImage = (ImageView) rowView.findViewById(R.id.pubImage);
            Response.PubsBean item = (Response.PubsBean) getItem(position);
            pub.setText(item.getPub());
            Picasso.with(context).load(item.getLink()).into(pubImage);
            return rowView;

    }
}
