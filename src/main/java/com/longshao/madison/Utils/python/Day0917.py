import sys
import json

if __name__ == '__main__':
    print('------------------------begin---------------------------')
    Json_data = "{\"name\":\"qlh\",\"age\":23}"
    print(Json_data)
    #将对象转换为字符串
    # strstr = json.dumps(Json_data)
    # print(strstr)
    #将字符串转化为对象
    json_Data = json.loads(Json_data)
    print(json_Data)
    items = json_Data.items()
    print(items)
    for key, value in items:
        print(str(key) + '=' + str(value))

    # data = sys.argv[1]
    # print(data)
    # jsonData = json.loads(data)
    # print(jsonData)
    print('-------------------------end----------------------------')