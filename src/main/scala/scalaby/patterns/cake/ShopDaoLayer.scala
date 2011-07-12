/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.cake

import java.net.URI

object DAOLayer extends OrderDAO with ProductDAO with JdbcSupport {
  
  val dataSource = init(new URI("uri"), "user", "password")

  val productDao = new ProductDAOImpl
  val orderDao = new OrderDAOImpl
  
}

class ProductService {
  import DAOLayer._

  def createProduct(product: Product) = productDao.create(product)
  
  //...
}