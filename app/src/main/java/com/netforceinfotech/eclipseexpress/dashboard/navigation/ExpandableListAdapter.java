package com.netforceinfotech.eclipseexpress.dashboard.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.netforceinfotech.eclipseexpress.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Netforce on 7/12/2016.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private clickListner click;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        ToggleButton tg_button=(ToggleButton)convertView.findViewById(R.id.toggleButton);

        txtListChild.setText(childText);
        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.itemClicked(finalConvertView, groupPosition, childPosition);
            }
        });
       if(groupPosition==2  && childPosition==0)
       {
           tg_button.setVisibility(View.VISIBLE);
       }
        else {
           tg_button.setVisibility(View.GONE);
       }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.list_group, parent, false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        ImageView imageViewIcon = (ImageView) convertView.findViewById(R.id.imageViewIcon);
        switch (groupPosition) {
            case 0:
                imageViewIcon.setImageResource(R.drawable.ic_tag_grey);
                break;
            case 1:
                imageViewIcon.setImageResource(R.drawable.ic_account_grey);
                break;
            case 2:
                imageViewIcon.setImageResource(R.drawable.ic_settings);
                break;
            case 3:
                imageViewIcon.setImageResource(R.drawable.rate_usicon);
                break;
            case 4:
                imageViewIcon.setImageResource(R.drawable.share_app);
                break;
            case 5:
                imageViewIcon.setImageResource(R.drawable.help_centreicon);
                break;

        }
        if (_listDataChild.get(_listDataHeader.get(groupPosition)).size() == 0) {
            imageView.setVisibility(View.GONE);
            final View finalConvertView = convertView;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(_context, "clicked", Toast.LENGTH_SHORT).show();
                    click.itemClicked(finalConvertView, groupPosition, 0);
                }
            });
        } else {
            if (isExpanded) {
                imageView.setImageResource(R.drawable.ic_chevron_primary);
                notifyDataSetChanged();
            } else {
                imageView.setImageResource(R.drawable.ic_chevron_grey);
                notifyDataSetChanged();
            }
        }



        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface clickListner {
        void itemClicked(View view, int groupview, int childview);
    }

    public void setClickListner(clickListner click) {
        this.click = click;
    }
}