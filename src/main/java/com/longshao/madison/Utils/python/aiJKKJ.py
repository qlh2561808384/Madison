import sys,json
import random

if __name__ == "__main__":
	# print(sys.argv[1])
	msg = json.loads(sys.argv[1])
	rst = {}
	num = int(random.uniform(1, 15)*10) / 10
	if num > 8:
		ai_ret = num
	else:
		ai_ret = "error, something wrong!!!"
	if isinstance(ai_ret, float):
	    rst['result'] = int(ai_ret * 10) / 10.0
	    rst['error'] = ''
	else:
	    rst['result'] = ''
	    rst['error'] = ai_ret
	rst['arg01'] = msg['arg01']
	rst['patient_id'] = msg['patient_id']
	rst['detect_id'] = msg['detect_id']
	rst['last'] = ''

	r = json.dumps(ai_ret)
	#time.sleep(10)
	print(rst)
	# print(r)