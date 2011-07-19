/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.cake

/*
 OrderDAO is a container that injects instance of order's DAO to the context and offers
 an implementation that can be used
 */

trait OrderDAO {self: PersistenseProvider =>

    val orderDao: DAO[Order]

    class OrderDAOImpl extends DAO[Order]{
        def create(order:Order) = dataSource persist order
    }

}

trait ProductDAO {self: PersistenseProvider =>

    val productDao: DAO[Product]

    class ProductDAOImpl extends DAO[Product]{    
        def create(product: Product) = dataSource persist product
    }

}