package com.servinow.android;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.servinow.android.domain.PurchasedItem;
import com.servinow.android.widget.PurchasedItemAdapter;

import android.os.Bundle;

public class TicketActivity extends SherlockListActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    PurchasedItem[] purchasedItems = new PurchasedItem[] {
                                                          new PurchasedItem("Arroz a la cubana", 2, 20),
                                                          new PurchasedItem("Ensalada de esparragos", 1, 8),
                                                          new PurchasedItem("Cerbeza", 8, 2),
                                                          new PurchasedItem("Sandwich vegetal", 2, 6),
                                                          new PurchasedItem("CocaCola", 2, 6)
    };
    PurchasedItemAdapter adapter = new PurchasedItemAdapter(this, R.layout.ticket_list_item, purchasedItems);
    setListAdapter(adapter);
    setContentView(R.layout.activity_ticket);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getSupportMenuInflater().inflate(R.menu.activity_ticket, menu);
    return true;
  }
}
