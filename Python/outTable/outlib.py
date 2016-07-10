# -*- coding: utf-8 -*-
"""
Created on Wed Jul 06 21:46:22 2016

@author: liudiwei
"""

import csv
echart_js = "./js/echarts.min.js"
import json
import sys
reload(sys)
sys.setdefaultencoding( "utf-8")

'''
输出字典数据
onedict = {“type”: [click, view, uid, click1, view1, ctr, ]}
'''
def outDict(onedict={}, filename=False, filetype=None, header=None, title=None, write_mode="w", isSort=True, sorted_reverse=False):
    content = ''
    if filetype == "csv":
        csvfile = file(filename, 'wb')
        csvfile.write('\xEF\xBB\xBF') # 解决乱码
        writer = csv.writer(csvfile)
        if header != None:
            writer.writerow(header)
        for key in sorted(onedict.keys()):
            onedict[key] = [str(i) for i in onedict[key]]
            oneline = [key]
            oneline.extend(onedict[key])
            writer.writerow(oneline)
        csvfile.close()
    elif filetype == 'table':
        fo = open(filename, write_mode)
        content += "<style type='text/css'>.table-c table{border-right:1px solid #186709;border-bottom:1px solid #186709} .table-c table td{border-left:1px solid #186709;border-top:1px solid #186709; height:30px;padding-left:5px;} .table-c table th{height: 35px;border-left:1px solid #186709;border-top:1px solid #186709; background: #B4F19B;color:#06631F}</style><meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"
        content += "<div class='table-c'>"
        if title != None:
            content += "<br><br><b>" + title + "<b></br></br>"        
        content += "<table width='80%' border='0' cellspacing='0' cellpadding='0'>"
        if header != None:
            content += "<tr><th>" + "</th><th>".join(header) + "</th></tr>"
        if isSort: 
            isSortedKey = sorted(onedict.keys(), reverse=sorted_reverse) 
        else:
            isSortedKey = onedict.keys()
        for key in isSortedKey:
            if type(onedict[key]) == list:
                onedict[key] = [str(i) for i in onedict[key]]
                content += "<tr><td>" + key + "</tb><td>" + "</tb><td>".join(onedict[key]) + "</td></tr>"
        content += '</table></div>'
        fo.write(content)
    else:
        for key in sorted(onedict.keys(), reverse=sorted_reverse):
            if type(onedict[key]) == list:
                onedict[key] = [str(i) for i in onedict[key]]
                content += key + '\t' + '\t'.join(onedict[key]) + '\n'
            else:
                content += '\n' + key + '\t' + str(onedict[key]) + '\t' + str("%.3f" %(onedict[key]/sum(onedict.values())))
        if filename:
            fw = open(filename, 'w')
            fw.write(content)
            fw.close()
    return content 

##################################################################################################

"""
json_data：json数据
key：用于拼凑的key
valueKey：用于生成value的key
"""
def jsonToFormatedDict(json_data, key, valueKey=[], outdict={}):
    jsonValue = json.loads(json_data)
    value = []
    for vkey in valueKey:
        value.append(jsonValue[vkey])
    outdict[jsonValue[key]] = value
    return outdict


def out_html(onedict, axisTitle, outfile, title=[], echart_js_file=echart_js):
    print title
    fo_echart = open(echart_js_file, 'r')
    fr_echart_js = '<script type="text/javascript">' + ''.join(fo_echart.readlines()) + '</script>'
    html_head = '<!DOCTYPE html><html><header><meta charset="utf-8">' + fr_echart_js + '\n</header>\n'
    html_body_head = '<body>\n'
    
    divContent = "<center>"
    for m in range(len(onedict)):
        divId = "main" + str(m) # '<HR align=center width=300 color=#987cb9 SIZE=1><font color="blue" size="5"> ' + title[m] + '趋势图</font><HR align=center width=300 color=#987cb9 SIZE=1>' +  
        div_part = '\n<br><div id="' + divId +'" style="width: 700px;height:350px;"></div>'
        divContent += div_part
        
    option_head = '''\n</center><script type="text/javascript">'''
    optionContent = divContent + option_head
    for i in range(len(onedict)):
        divId = "main" + str(i)
        optionName = "option" + str(i)
        option = combine_option(axisTitle, optionName, title[i], onedict[i])
        optionContent += option
        
    for i in range(len(onedict)):
        divId = "main" + str(i)
        optionName = "option" + str(i)
        print optionName
        optionContent += "\necharts.init(document.getElementById('" + divId +"')).setOption(" + optionName + ")"
        
    foot = "\n</script></body></html>"
    
    combine_all = html_head + html_body_head + optionContent + foot
    fw = open(outfile, "w")
    fw.write(combine_all)
    return combine_all
    

'''
axisX： [‘周一’, ‘周二’, ‘周三’, ‘周四’, ‘周五’, ‘周六’, ‘周日’]
data:  {
            'name1': [1, 1, 1, 1, 1, 1, 1],
            'name2': [2, 2, 2, 2, 2, 2, 2],
            'name3': [3, 3, 3, 3, 3, 3, 3],
            'name4': [4, 4, 4, 4, 4, 4, 4]
        }
'''
def combine_option(axis, optionName, titleName, data={}, ):
    axisX = ""
    title =  ""
    for elem in axis:
        axisX += "'" + elem + "',"
    series = ""
    for key in data.keys():
        series += """{name:'""" + key + """',type:'line',data:""" + str(data[key])+ """},"""
        title += "'" + key + "',"
    series = series.strip(",")
    option = """\nvar """ + optionName +""" = {
            title : {text: '"""+ titleName +"""趋势图',ubtext: ''},
		    tooltip : {trigger: 'axis'},
		    legend: {data:[""" + title.strip(",") + """]},
		    toolbox: {show : true,
                feature : {
                    mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [{type : 'category', boundaryGap : false, data : [""" + axisX.strip(",") + """]}],
		    yAxis : [{type : 'value'}],
		    series : [""" + series  + """]
		};"""
    return option
    

  

    
def test():
    axisX=['周一', '周二', '周三', '周四', '周五','周六', '周日']
    dataList=[
        {
            '心情指数': [9.1, 8.0, 7.1, 8.1, 9.5, 9.1, 9.9],
            '压力指数': [8.1, 9.3, 8.8, 8.4, 8.2, 8.1, 8.3],
            '情感指数': [6.1, 7.3, 7.1, 6.8, 6.9, 7.1, 7.9],
            '魅力指数': [8.1, 8.3, 8.8, 8.4, 7.5, 8.1, 8.6]
        },
        {
            '心情指数': [8.1, 8.8, 7.5, 8.1, 9.5, 9.1, 9.9],
            '压力指数': [8.1, 8.5, 8.4, 8.1, 8.5, 9.1, 8.9],
            '情感指数': [6.1, 7.2, 6.9, 6.1, 6.5, 6.1, 6.9],
            '魅力指数': [7.9, 8.1, 8.1, 8.3, 8.2, 7.1, 8.9]
        }]
    out_html(dataList, axisX, title=["张三 - 各项指标", "李四 - 各项指标"], outfile="./out.html")
    
##################################################################################################

if __name__ == "__main__":
    test()
    
