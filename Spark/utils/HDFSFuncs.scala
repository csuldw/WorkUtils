import java.net.URI
import org.apache.commons.configuration.{PropertiesConfiguration => Config}
import org.apache.hadoop.fs.{FileSystem, Path}
import scala.collection.mutable.ArrayBuffer
import org.apache.hadoop.conf.Configuration

/**
  * Created by liudiwei on 2016/6/24.
  */
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
