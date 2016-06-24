## 文件说明

- DevUtils.scala
	- `parseJson(s: String): Map[String, String]`：将string字符串转换成json；
	- `toJSONStr(logElem : Array[String], header : Array[String]) : String` ：将logElem：Array[String]作为value，header : Array[String]作为key，一个header指向一个logElem，合并成一个json；

- TimeUtils.scala
	- `getDateTime(sep: String = "-"): String`: 获取当前日志，包含有年月日时分秒，`sep`为分隔符，默认返回结果为：“2016-06-20-11-11-11”;
	- `getTime: Date` :直接返回Date，结果如"Fri Jun 24 21:23:29 GMT+08:00 2016";
	- `getTimestamp: Long`返回13位长度的时间戳；
	- `diffTime(begTime : Date, endTime : Date): String`:返回时间间隔，单位为秒；
	- `getDateOnly(sep: String = "-"): String`:获取日志，如"2016-06-11"；
	- `getTimeOnly(sep: String = "-"): String`：获取时间，包括“时分秒”，默认结果为"22-22-22";
	- `subTime(sep: String = "-", days: Int): String`:获取`days`天前的日期，当days=1时，表示"1天前"，即昨天的日期，返回为"2016-06-19"；
	- `addTime(sep: String = "-", days: Int): String`：获取`days`天后的日期，当days=1时，表示"1天后"，即明天的日期，返回为"2016-06-21"；
	- `timeFormat(time: String, format :String = "yyyyMMdd"): String`：将当前时间格式化输出；
	- `getFormatedTime(format : String): String`：获取格式化的时间，可自己指定格式，当format为"yyMMdd"时，返回结果为"20160620"；
	- `getDateList(dayNumber : Int, sep : String ): Array[String]`：获取今天到`dayNumber`天前组成的日期数组，sep指定日期的分隔符，当`dayNumber=2`时，`sep=""`时，返回结果为`Array("20160620", "20160619", "20160618")`；

- HDFSFuncs.scala
	- `isExists(hdfs_prefix : String, hdfs_path : String) : Boolean`: 判断文件HDFS是否存在；
	- `mkdir(hdfs_prefix : String, hdfs_path : String) : Boolean`：创建HDFS目录；
	- `list(hdfs_prefix : String, hdfs_path : String) : Array[String]`：列出HDFS目录下的文件，返回Array[String];
	- `delete(hdfs_prefix : String, hdfs_path : String) : Boolean`：删除HDFS下的文件；



