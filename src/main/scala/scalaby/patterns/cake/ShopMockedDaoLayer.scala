/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.cake

import java.net.URI

object ShopMockedDaoLayer extends OrderDAO with ProductDAO with JdbcSupport {
  
    val dataSource = new JdbcDatasource(new URI("uri"), "user", "password")

    val productDao = new ProductDAOImpl
  
    val orderDao = new DAO[Order]{    
        def create(order: Order) = println("User attempted to create order " + order)    
    }
  
}
