
import sys
import getopt

if __name__ == '__main__':
    print('------------------------begin---------------------------')
    #Get the parameter list
    #一般使用sys.argv[1:],这样可以过滤掉第一个参数（ps：第一个参数是脚本的名称，它不应该作为参数进行解析）
    parameterList=sys.argv[1:]
    #initialize all parameters
    businessType=''
    logType = {}
    timeInfo = {}
    opts, args = getopt.getopt(parameterList,"b:l:t:",['businessType=','logType=','timeInfo'])
    for opt, arg in opts:
        if opt in ("-b", "--businessType"):
            businessType = arg
        elif opt in ("-l", "--logType"):
            logType = dict(item.split('=') for item in arg.split(','))
        elif opt in ("-t", "--timeInfo"):
            timeInfo = dict(item.split('=') for item in arg.split(','))

    print('businessType:',businessType)
    print('logType:',logType)
    print('timeInfo:',timeInfo)

    print('-------------------------end----------------------------')
