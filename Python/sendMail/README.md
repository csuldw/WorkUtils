
## 代码说明

send_mail.py主要用于在Linux平台中发送E-mail。


执行命令：

```
nohup python send_email.py "file1.data,file2.data" > log.out 2>&1 &
```

传入的arg[1]表示的是附件文件，多个附件以逗号分隔即可。