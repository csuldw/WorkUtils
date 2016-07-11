# -*- coding: utf-8 -*-
"""
Created on Fri Jul 08 19:23:47 2016

@author: liudiwei
"""
import outlib as olib
import json
import sys

def combineData(filelist, selectKey):
    axisX = []
    data = []
    for sk in selectKey:
        dicts = {}
        for onefile in filelist.split(";"):
            axisX.append(onefile.split("/")[-1].split(".")[0])
            fr = open(onefile, 'r')
            for eachline in fr:
                one_json = json.loads(eachline)
                key = str(one_json["type"])
                ctr = float(one_json[sk])
                if not dicts.has_key(key):
                    dicts[key] = [ctr]
                else:
                    dicts[key].append(ctr)
        data.append(dicts)
    return axisX, data

def test():
    filelist = "./data/20160706.data;./data/20160707.data"
    selectKey = ["s1", "s2", "s3", "s4", "s5", "s6", "s7"]
    axisX, data = combineData(filelist,selectKey)
    title = ["title1", "title2", "title3", "title4", "title5", "title6", "title7"]
    eachUnits = ["", "", "", "", "", "%", ""]
    olib.out_html(data, axisX, outfile="./out2.html", title=title, units=eachUnits)
    
if __name__ == "__main__":
    filelist = sys.argv[1].strip(";")
    outfile = sys.argv[2]
    selectKey = ["s1", "s2", "s3", "s4", "s5", "s6", "s7"]
    axisX, data = combineData(filelist,selectKey)
    titleName = ["title1", "title2", "title3", "title4", "title5", "title6", "title7"]
    eachUnits = ["", "", "", "", "", "%", ""]
    olib.out_html(data, axisX, outfile, title=titleName, units=eachUnits) 
    