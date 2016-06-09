package application.beerbeer.Menu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import application.beerbeer.R;
import application.beerbeer.ResponsePack.Response;

/**
 * Created by methyll.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<String> head;
    HashMap<String,ArrayList<String>> child;
    Response objResponse;

    public ExpandableListAdapter(Context context, ArrayList<String> head, HashMap<String,ArrayList<String>> child, Response objResponse)
    {
        this.context = context;
        this.head=head;
        this.child = child;
        this.objResponse = objResponse;
    }

    @Override
    public int getGroupCount() {
        return head.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(head.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return head.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(head.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String grouptitle = (String) getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.head_list,parent ,false);

        }
        TextView group = (TextView) convertView.findViewById(R.id.headname);
        group.setText(grouptitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childtitle = (String) getChild(groupPosition, childPosition);
         if(convertView == null)
         {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_list,parent,false);
         }
            TextView child = (TextView) convertView.findViewById(R.id.childname);
        ImageView imagechild = (ImageView) convertView.findViewById(R.id.childpubImage);



         String imagelink ="";
      for(int i=0;i<objResponse.getPubs().size();i++)
        {
            if(childtitle.equals(objResponse.getPubs().get(i).getPub()))
            {
                 imagelink = objResponse.getPubs().get(i).getLink();
            }

        }
        try{
            Picasso.with(context).load(imagelink).into(imagechild);
        }catch (IllegalArgumentException e)
        {
            Log.d("blad", "bladExpandablelist");
        }

        child.setText(childtitle);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
