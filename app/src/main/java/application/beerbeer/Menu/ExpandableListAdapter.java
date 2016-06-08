package application.beerbeer.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import application.beerbeer.R;

/**
 * Created by methyll.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<String> head;
    HashMap<String,ArrayList<String>> child;

    public ExpandableListAdapter(Context context, ArrayList<String> head, HashMap<String,ArrayList<String>> child)
    {
        this.context = context;
        this.head=head;
        this.child = child;
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
        String childtitle = (String) getChild(groupPosition,childPosition);
         if(convertView == null)
         {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.child_list,parent,false);
         }
            TextView child = (TextView) convertView.findViewById(R.id.childname);
        child.setText(childtitle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
