#!/bin/bash  
# Name liudiwei
USER=root 
PASSWORD=huawei 
BACKUP_DIR=/data01/backup/database/                
LOGFILE=/data01/backup/database/data_backup.log    
DATE=`date +%Y-%m-%d-%H-%M -d -5minute`           
DATE2=`date +'%Y/%m/%d %H:%M' -d -5minute`           
DBLIST=("test1" "test2") #DB NAME
 
if [ ! -d $BACKUP_DIR ];                  
then  
    mkdir -p "$BACKUP_DIR" 
fi  
 
echo -e "\n" >> $LOGFILE   
echo "------------------------------------" >> $LOGFILE  
echo "BACKUP DATE:$DATE2">> $LOGFILE  
echo "------------------------------------" >> $LOGFILE  
 
for db in ${DBLIST[@]};
do
    echo "Start to backup database: $db" >> $LOGFILE 
    DUMPFILE=$db-${DATE}.sql                #数据库名称 
    ARCHIVE=$DUMPFILE.tar.gz              #压缩名称 
 
    cd $BACKUP_DIR                            
    mysqldump -u$USER -p$PASSWORD $db > $DUMPFILE    #使用mysqldump备份数据库 
    if [[ $? == 0 ]]; then         
        tar -czvf $ARCHIVE $DUMPFILE >> $LOGFILE 2>&1                               
        echo "$ARCHIVE BACKUP SUCCESSFUL!" >> $LOGFILE  
        rm -f $DUMPFILE 
    else  
        echo “$ARCHIVE Backup Fail!” >> $LOGFILE  
    fi
    echo -e "\n" >> $LOGFILE   
done
