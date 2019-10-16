import sys
import json

if __name__ == '__main__':
    # json.dumps是将一个Python数据类型列表进行json格式的编码解析，
    list1 = sys.argv[1]
    list = sys.argv[2]
    print(list)
    print(list1)
    json_list = json.dumps(list,ensure_ascii=False)
    print(json_list)
    load_data = json.loads(json_list)
    print(load_data)
    # data = ['iplaypython',[1,2,3], {'name':'qlh'}] #创建一个l列表
    # print(repr(data))
    # jsondata = json.dumps(data)
    # print(jsondata)
    # print(json.dumps('中国',ensure_ascii=False))
    # 这样我们就将一个list列表对象，进行了json格式的编码转换。
    # json.dumps()函数有多个参数:indent参数用于表示美化格式输出时的缩进占位个数,ensure_ascii参数默认为True,
    # 这样utf-8格式的非ASCII编码内容会被翻译成ASCII编码输出，要想得到字符的真实表示,需要将这个参数设置为False。
    # 其他参数还有:skipkeys,separators,sort_keys等。
