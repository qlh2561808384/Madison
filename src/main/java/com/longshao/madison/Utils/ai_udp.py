# -*- coding: utf-8 -*-
#import logging
from socketserver import BaseRequestHandler, TCPServer, UDPServer, ThreadingUDPServer
import time, random, json

#logging.basicConfig(format='%(asctime)s %(levelname)s:%(message)s', level=logging.INFO)
log = Logger('logs/ai_udp.log',level='info')

class UDPHandler(BaseRequestHandler):
    def handle(self):
        #print('Got connection from', self.client_address)
        start = time.time()
        msg, sock = self.request
        if not msg:
            return
        msg = json.loads(msg.decode("utf-8"))
        log.logger.info('[INPUT_{0}] {1}'.format(msg['arg01'],msg))
        #num = int(random.uniform(1, 15)*10) / 10
        rst = {}
        ai_ret = 8.5
        if isinstance(ai_ret, float):
            rst['result'] = int(ai_ret * 10) / 10.0
            rst['error'] = ''
        else:
            log.logger.error(ai_ret)
            rst['result'] = ''
            rst['error'] = ai_ret
        rst['arg01'] = msg['arg01']
        rst['patient_id'] = msg['patient_id']
        rst['detect_id'] = msg['detect_id']
        rst['last'] = ''

        r = json.dumps(rst)
        #time.sleep(10)
        cost_time = time.time() - start
        log.logger.info('[TOTAL_COST_TIME]: {0}'.format(cost_time))
        sock.sendto(str.encode(r), self.client_address)


if __name__ == '__main__':
    log.logger.info('ai api(udp) starts:')
    serv = ThreadingUDPServer(('', 62000), UDPHandler)
    serv.serve_forever()
