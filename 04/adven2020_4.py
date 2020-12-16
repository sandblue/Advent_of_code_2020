import sys
import re

def check_count_valid_passport(path):
    file = open(path)
    passport_valid_count = 0
    text = str(file.read()).split("\n\n")
    file.close()
    key_require = ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid'] #, 'cid']
    for rawPassPort in text:
        rawPassPort = rawPassPort.replace("\n", " ").split(" ")
        key_list = []
        for passPort in rawPassPort:
            passPort = passPort.split(":")
            key_list.append(passPort[0])
        valid = True    
        for require_key in key_require:
            valid &= require_key in key_list 
        if(valid):
            passport_valid_count += 1
        else:
            print(key_list)
    print(passport_valid_count)

check_count_valid_passport(str(sys.argv[1]))

def check_count_valid_passport_more_detail(path):
    file = open(path)
    passport_valid_count = 0
    text = str(file.read()).split("\n\n")
    file.close()
    key_require = ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid'] #, 'cid']
    for rawPassPort in text:
        rawPassPort = rawPassPort.replace("\n", " ").split(" ")
        key_list = {}
        for passPort in rawPassPort:
            passPort = passPort.split(":")
            key_list[passPort[0]] =  passPort[1]
        valid = True    
        for require_key in key_require:
            valid &= require_key in key_list.keys()
        if(valid):
            passport_valid_count += 1
        else:
            print(key_list)
    print(passport_valid_count)

check_count_valid_passport_more_detail(str(sys.argv[1]))
