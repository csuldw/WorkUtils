import org.json4s.jackson.JsonMethods._

import scala.collection.mutable.ArrayBuffer
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._

/**
  * Created by liudiwei on 2016/6/16.
  */
object DevUtils {
  def parseJson(s: String): Map[String, String] = {
    try {
      val j = parse(s)
      j.values.asInstanceOf[Map[String, _]].map{
        case(key, value) => {
          (key, (j \ key).values match {
            case null => "null"
            case _ =>  (j \ key).values.toString
          })
        }
      }
    } catch {
      case _: Exception => {
        //println("json error")
        Map[String, String]()
      }
    }
  }
    
  def toJSONStr(logElem : Array[String], header : Array[String]) : String = {
    val jsonStr = logElem.length match {
      case 25 =>
        (header(0) -> logElem(0))~
        (header(1) -> logElem(1))~
        (header(2) -> logElem(2))~
        (header(3) -> logElem(3))~
        (header(4) -> logElem(4))~
        (header(5) -> logElem(5))~
        (header(6) -> logElem(6))~
        (header(7) -> logElem(7))~
        (header(8) -> logElem(8))~
        (header(9) -> logElem(9))~
        (header(10) -> logElem(10))~
        (header(11) -> logElem(11))~
        (header(12) -> logElem(12))~
        (header(13) -> logElem(13))~
        (header(14) -> logElem(14))~
        (header(15) -> logElem(15))~
        (header(16) -> logElem(16))~
        (header(17) -> logElem(17))~
        (header(18) -> logElem(18))~
        (header(19) -> logElem(19))~
        (header(20) -> logElem(20))~
        (header(21) -> logElem(21))~
        (header(22) -> logElem(22))~
        (header(23) -> logElem(23))~
        (header(24) -> logElem(24))
    }
    (compact(render(jsonStr)))
  }
}
