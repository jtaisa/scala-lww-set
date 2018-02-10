package crdt.services
import crdt.{Element, LWWSet}

import scala.concurrent.Future

class MockLWWSetServiceImpl extends LWWSetService {
  protected def save(key: String, elems: List[Element[String]]): Future[Long] = Future.successful {
    if(key == "exception") throw new RuntimeException("test")
    elems.size
  }

  override def add(key: String, elems: List[Element[String]]): Future[Long] = save(key, elems)
  override def remove(key: String, elems: List[Element[String]]): Future[Long] = save(key, elems)

  override def get(key: String): Future[Option[LWWSet[String]]] = Future.successful {
    key match {
      case "set" => Some(LWWSet().add(Element("test", 1)))
      case "empty" => Some(LWWSet())
      case _ => None
    }
  }
}
