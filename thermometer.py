import os
import time
#import requests, json


os.system('modprobe w1-gpio')
os.system('modprobe w1-therm')

base_dir = '/sys/bus/w1/devices/28-0000058644fd'
device_file = base_dir + '/w1_slave'

def read_temp_raw():
    f = open(device_file, 'r')
    lines = f.readlines()
    f.close()
    return lines

def read_temp():
    lines = read_temp_raw()
    while lines[0].strip()[-3:] != 'YES':
        time.sleep(0.2)
        lines = read_temp_raw()
    equals_pos = lines[1].find('t=')
    if equals_pos != -1:
        temp_string = lines[1][equals_pos+2:]
        temp_c = float(temp_string) / 1000.0
        temp_f = temp_c * 9.0 / 5.0 + 32.0
        #        url = "https://api.mlab.com/api/1/databases/temperature_raspberry/collections/data_rasp?apiKey=P1k7QvxL9EdwCVkpRJL6NqB-XX9peOlt"
        #headers = {'content-type': 'application/json'}
        #data = json.dumps({ "_id" : { "$oid" : "5710140ee4b06cd8db816809"}, 'celcius':temp_c, 'fahrenheit':temp_f})
        #r = requests.post(url, data, headers=headers)
        #print r.json
        comand = '{"celcius":"'+str(temp_c)+'","farenheit":"'+str(temp_f)+'"}'
        os.system('curl -i -X POST -H "Content-Type:application/json" -d '+comand+' https://api.mlab.com/api/1/databases/temperature_raspberry/collections/data_rasp?apiKey=P1k7QvxL9EdwCVkpRJL6NqB-XX9peOlt')
        return temp_c, temp_f

while True:
    print(read_temp())
    time.sleep(1)
