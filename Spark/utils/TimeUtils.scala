import java.text.SimpleDateFormat
import java.util.Date
import java.text.DecimalFormat
import scala.collection.mutable.ArrayBuffer  
/**
  * Created by liudiwei on 2016/6/16.
  */

object TimeUtils {

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