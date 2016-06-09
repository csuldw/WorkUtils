#!/usr/bin/env python
# -*- coding: utf-8 -*-
import sys
import os
import smtplib
import random
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email import encoders
reload(sys)
sys.setdefaultencoding('utf8')
class Mail:
    def __init__(self,server):
        self._smtpServer = smtplib.SMTP(server)
    def send_mail(self,sender,to,subject,text,files):
        msg = MIMEMultipart()
        msg["From"] = sender
        msg["Subject"] = subject
        msg["To"] = ";".join(to)
        msg.attach(MIMEText(text))
        for file in files:
            part = MIMEBase('application', 'octet-stream')
            part.set_payload(open(file,"rb").read())
            filename = os.path.basename(file)
            part.add_header('Content-Disposition', 'attachment', filename=('utf-8', '', filename))
            print 'mail', filename
            encoders.encode_base64(part)
            msg.attach(part)
        self._smtpServer.sendmail(sender,to,msg.as_string())

#test
if __name__ == '__main__':
    filename=sys.argv[1]
    files = [filename]
    mail = Mail("localhost")
    to = ["xxxxxxxx@csu.edu.cn"] 
    subject = "Title: email test"
    if not isinstance(subject,unicode):
        subject = unicode(subject)
    mail.send_mail("localhost@xxx.cn", to, subject , "hi, \n\n  this is a test e-mail!", files)