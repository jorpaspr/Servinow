/**
 * 
 */
package com.servinow.android.dao;

import java.util.HashMap;
import java.util.List;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.servinow.android.domain.Categoria;
import com.servinow.android.domain.Pedido;
import com.servinow.android.domain.Restaurant;

import android.content.Context;

/**
 * @author KoxAlen
 *
 */
public class PedidoCache extends ServinowDAOBase {
  RuntimeExceptionDao<Pedido, Integer> pedidoDao;

  /**
   * @param context
   */
  public PedidoCache(Context context) {
    super(context);
    pedidoDao = servinowDatabase.getRuntimeExceptionDao(Pedido.class);
  }
  
  public List<Pedido> getPedidosNoPagados(int restaurantID) {
    HashMap<String, Object> sqlFieldsToMatch = new HashMap<String, Object>();
    sqlFieldsToMatch.put("restaurant_id", restaurantID);
    sqlFieldsToMatch.put("pagado", false);
    sqlFieldsToMatch.put("confirmado", true);
    
    List<Pedido> pedidosList = pedidoDao.queryForFieldValues(sqlFieldsToMatch);
    return pedidosList;
    
  }
}
