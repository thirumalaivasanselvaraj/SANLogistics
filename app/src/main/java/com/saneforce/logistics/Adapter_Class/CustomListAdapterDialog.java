package com.saneforce.logistics.Adapter_Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saneforce.logistics.Common_Class.click_Listner;
import com.saneforce.logistics.Customer_Profile_Pojo.CustomerProfileNameOftheCompany;
import com.saneforce.logistics.R;

import java.util.ArrayList;

public class CustomListAdapterDialog extends BaseAdapter implements Filterable {

    ArrayList<CustomerProfileNameOftheCompany> _Contacts;

    private LayoutInflater layoutInflater;
    int Tickmark;
    private ArrayList<CustomerProfileNameOftheCompany> mStringFilterList;
    private LayoutInflater inflater;
    private ValueFilter valueFilter;
    click_Listner itemClickListener;

    int positionn;

    public CustomListAdapterDialog(Context context, ArrayList<CustomerProfileNameOftheCompany> listData, int a) {

        super();
        mStringFilterList = listData;
        layoutInflater = LayoutInflater.from(context);
        Tickmark = a;
        _Contacts = listData;

        itemClickListener = (click_Listner) context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return _Contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return _Contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        positionn = position;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.tickmark_listview, null);
            holder = new ViewHolder();
            holder.unitView = (TextView) convertView.findViewById(R.id.premium);
            holder.imagTick = (ImageView) convertView.findViewById(R.id.imagTick);
            if (position == Tickmark) {
                holder.imagTick.setVisibility(View.VISIBLE);
            }
            holder.linear_vehicle = (LinearLayout) convertView.findViewById(R.id.linearvehicle);

            holder.linear_vehicle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    itemClickListener.onClick(v, position, _Contacts.get(position).getNameoftheCompany());
                    System.out.println("ViewCLICKVALUES" + _Contacts.get(position).getNameoftheCompany());
                    //   listener.onContactSelected();
                }
            });


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.unitView.setText(_Contacts.get(position).getNameoftheCompany());


        return convertView;
    }

    @Override
    public Filter getFilter() {

        if (valueFilter == null) {

            valueFilter = new ValueFilter();
        }

        return valueFilter;
    }

    static class ViewHolder {
        TextView unitView;
        ImageView imagTick;
        LinearLayout linear_vehicle;
    }

    private class ValueFilter extends Filter {

        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<CustomerProfileNameOftheCompany> filterList = new ArrayList<CustomerProfileNameOftheCompany>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getNameoftheCompany().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        CustomerProfileNameOftheCompany contacts = new CustomerProfileNameOftheCompany(mStringFilterList.get(i).getEmpCode(),
                                mStringFilterList.get(i).getEmpID(), mStringFilterList.get(i).getDivisionCode(),
                                mStringFilterList.get(i).getCreatedDate(), mStringFilterList.get(i).getNameoftheCompany(),
                                mStringFilterList.get(i).getAddressoftheCompany(), mStringFilterList.get(i).getContactPersonName(), mStringFilterList.get(i).getDesignation(),
                                mStringFilterList.get(i).getMobileNumber(), mStringFilterList.get(i).getCustomerEmail(), mStringFilterList.get(i).getCustomerCategory());

                        contacts.setNameoftheCompany(mStringFilterList.get(i).getNameoftheCompany());

                        filterList.add(contacts);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }


        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            _Contacts = (ArrayList<CustomerProfileNameOftheCompany>) results.values;
            notifyDataSetChanged();
        }
    }


}