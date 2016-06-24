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

object TimeUtils {
  import java.text.SimpleDateFormat
  import java.util.Date
  import java.text.DecimalFormat

  def getDateTime(sep: String = "-"): String = {
    val now = new Date
    val fmt = new SimpleDateFormat(
      Seq("yyyy", "MM", "dd",
        "HH", "mm", "ss").mkString(sep))
    fmt.format(now)
  }

  def getTime: Date = {
    new Date
  }

  def getTimestamp: Long = {
    (new Date).getTime
  }

  def diffTime(begTime : Date, endTime : Date): String = {
    val df2 = new DecimalFormat("0.00")
    df2.format(endTime.getTime / 60000.0 - begTime.getTime / 60000.0)
  }

  def getDateOnly(sep: String = "-"): String = {
    val now = new Date
    val fmt = new SimpleDateFormat(
      Seq("yyyy", "MM", "dd").mkString(sep))
    fmt.format(now)
  }

  def getTimeOnly(sep: String = "-"): String = {
    val now = new Date
    val fmt = new SimpleDateFormat(
      Seq("HH", "mm", "ss").mkString(sep))
    fmt.format(now)
  }

  def subTime(sep: String = "-", days: Int): String = {
    val now = new Date
    val fmt = new SimpleDateFormat(
      Seq("yyyy", "MM", "dd").mkString(sep))
    val newDate = new Date(now.getTime - days * 24 * 60 * 60 * 1000)
    fmt.format(newDate)
  }

  def addTime(sep: String = "-", days: Int): String = {
    val now = new Date
    val fmt = new SimpleDateFormat(
      Seq("yyyy", "MM", "dd").mkString(sep))
    val newDate = new Date(now.getTime + days * 24 * 60 * 60 * 1000)
    fmt.format(newDate)
  }

  def timeFormat(time: String, format :String = "yyyyMMdd"): String={
    val sdf:SimpleDateFormat = new SimpleDateFormat(format)
    val date:String = sdf.format(new Date(time.length match {case 10 => time.toLong*1000l case 13 => time.toLong case _ => new Date().getTime}))
    date
  }

  def getFormatedTime(format : String): String = {
    val now = new Date
    val fmt = new SimpleDateFormat(format)
    fmt.format(now)
  }

  def getDateList(dayNumber : Int, sep : String ): Array[String] = {
    val dayArr = new ArrayBuffer[String]()
    for (i <- 0 to dayNumber) {
      dayArr += TimeUtils.subTime(sep, i).toString
    }
    dayArr.toArray
  }
}


import java.net.URI
import org.apache.commons.configuration.{PropertiesConfiguration => Config}
import org.apache.hadoop.fs.{FileSystem, Path}
import scala.collection.mutable.ArrayBuffer
//import scala.collection.mutable.{ArrayBuffer, Map}
import org.apache.hadoop.conf.Configuration
//import scala.sys.process._
class HDFSFuncs(config : Config) {
  def isExists(hdfs_prefix : String, hdfs_path : String) : Boolean = {
    val conf = new Configuration()
    val fs = FileSystem.get(URI.create(hdfs_prefix), conf)
    val java_path = new Path(hdfs_path)
    val ret = fs.exists(java_path)
    ret
    //val cmd = "hadoop fs -dus " + path
    //val ret = cmd.!
    //if (ret == 0) true else false
  }

  def mkdir(hdfs_prefix : String, hdfs_path : String) : Boolean = {
    try {
      val conf = new Configuration()
      val fs = FileSystem.get(URI.create(hdfs_prefix), conf)
      val java_path = new Path(hdfs_path)
      val ret = fs.mkdirs(java_path)
      ret
    } catch {
      case e : org.apache.commons.configuration.ConfigurationException =>
        println(e.getMessage)
        false
    }

    //val cmd = "hadoop fs -mkdir " + path
    //val ret = cmd.!
    //if (ret == 0) true else false
  }

  def list(hdfs_prefix : String, hdfs_path : String) : Array[String] = {
    val conf = new Configuration()
    val fs = FileSystem.get(URI.create(hdfs_prefix), conf)
    val java_path = new Path(hdfs_path)
    println("list hdfs_path = "+hdfs_prefix+java_path)
    val ab = new ArrayBuffer[String]()
    val statusList = fs.listStatus(java_path)
    if (statusList != null) {
      for (i <- statusList.indices) {
        ab += statusList(i).getPath.toString
      }
    }
    ab.toArray

    //val cmd = "hadoop fs -ls " + path
    //cmd.!
  }

  def delete(hdfs_prefix : String, hdfs_path : String) : Boolean = {
    try {
      val conf = new Configuration()
      val fs = FileSystem.get(URI.create(hdfs_prefix), conf)
      val java_path = new Path(hdfs_path)
      val ret = fs.delete(java_path, true)
      ret
    } catch {
      case e : org.apache.commons.configuration.ConfigurationException =>
        println(e.getMessage)
        false
    }

    //val cmd = "hadoop fs -rmr " + path
    //val ret = cmd.!
    //if (ret == 0) true else false
  }

}
