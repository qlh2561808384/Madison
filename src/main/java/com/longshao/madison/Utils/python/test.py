#_*_coding:utf-8_*_
import sys
import json

if __name__ == '__main__':
    print('------------------------begin---------------------------')
    #Get the parameter list
    parameterList=sys.argv[1]
    # parameterList1 = sys.argv[2]
    # parameterList = {"test":1,"test1":"qlh"}
    # json解析并按key排序
    print(parameterList)
    # json_str = json.dumps(parameterList,sort_keys=True)
    # print(json_str)
    params_json=json.loads(parameterList)
    print(params_json)
    items = params_json.items()
    print(items)
    ret = {}
    for key, value in items:
        print(str(key) + '=' + str(value))
    # 将 JSON 对象转换为 Python 字典
    # params_json = json.loads(json_str)
    # items = params_json.items()
    # for key, value in items:
    #     print(str(key) + '=' + str(value))
    # print(parameterList1)
    #     # print(parameterList)

    print('-------------------------end----------------------------')
