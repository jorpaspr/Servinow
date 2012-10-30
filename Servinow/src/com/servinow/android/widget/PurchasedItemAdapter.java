/**
 * 
 */
package com.servinow.android.widget;

import com.servinow.android.R;
import com.servinow.android.domain.PurchasedItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author KoxAlen
 *
 */
public class PurchasedItemAdapter extends ArrayAdapter<PurchasedItem> {
  static class ViewHolder {
    TextView name;
    TextView unitPrice;
    TextView qty;
    TextView price;
  }
  
  private int layoutResourceId;

  public PurchasedItemAdapter(Context context, int layoutResourceId, PurchasedItem[] objects) {
    super(context, layoutResourceId, objects);
    this.layoutResourceId = layoutResourceId;
  }
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View row = convertView;
    ViewHolder holder = null;
    
    if(row == null) {
      LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
      row = inflater.inflate(layoutResourceId, parent, false);
      holder = new ViewHolder();
      holder.name = (TextView)row.findViewById(R.id.textViewName);
      holder.unitPrice = (TextView)row.findViewById(R.id.textViewUnitPrice);
      holder.qty = (TextView)row.findViewById(R.id.textViewQty);
      holder.price = (TextView)row.findViewById(R.id.textViewPrice);
      row.setTag(holder);
    } else {
      holder = (ViewHolder) row.getTag();
    }
    
    PurchasedItem purchasedItem = getItem(position);
    holder.name.setText(purchasedItem.getName());
    holder.unitPrice.setText(purchasedItem.getUnitPrice()+"€");
    holder.qty.setText(""+purchasedItem.getQuantity());
    holder.price.setText(purchasedItem.getTotalPrice()+"€");
    
    return row;
  }
}
