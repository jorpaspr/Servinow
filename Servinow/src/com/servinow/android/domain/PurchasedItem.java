/**
 * 
 */
package com.servinow.android.domain;

/**
 * @author KoxAlen
 *
 */
public class PurchasedItem {
  private String name;
  private int quantity;
  private int unitPrice;
  /**
   * 
   */
  public PurchasedItem(String name, int quantity, int unitPrice) {
    this.name = name;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @return the quantity
   */
  public int getQuantity() {
    return quantity;
  }
  /**
   * @return the unitPrice
   */
  public int getUnitPrice() {
    return unitPrice;
  }
  public int getTotalPrice() {
    return unitPrice*quantity;
  }
}
