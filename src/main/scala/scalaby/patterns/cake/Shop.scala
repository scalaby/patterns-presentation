/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalaby.patterns.cake

import java.net.URI

trait Order
trait Product

trait DAO[T] { 
    def create(t:T)
}

trait Datasource {
    def persist[A](ojbect: A): Unit
}

trait PersistenseProvider {
    val dataSource: Datasource
}

trait JdbcSupport extends PersistenseProvider {  
    
    class JdbcDatasource(uri: URI, user: String, password: String) extends Datasource{
        def persist[A](ojbect: A) = {/*...*/}
    }
      
}