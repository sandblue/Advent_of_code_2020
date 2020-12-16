import sys
import re

def check_password_char_in_range(path):
    file = open(path)
    pass_count = 0
    for line in file:
        line = str(line).split(" ", 3)
        rule = line[0].split("-",2)
        min_value = int(rule[0])
        max_value = int(rule[1])
        target = line[1].split(":",1)[0]
        foundCount = int(len(re.findall(target, line[2])))
        if( foundCount >= min_value and foundCount <= max_value ):
            pass_count += 1
    file.close()
    print(pass_count)

def check_password_char_exits_once_in_index_set(path):
    file = open(path)
    pass_count = 0
    for line in file:
        line = str(line).split(" ", 3)
        rule = line[0].split("-",2)
        index_0 = int(rule[0]) - 1
        index_1 = int(rule[1]) - 1
        target = str(line[1].split(":",1)[0])
        if( (line[2][index_0] == target) != (line[2][index_1] == target) ):
            pass_count += 1
    file.close()
    print(pass_count)

check_password_char_in_range(str(sys.argv[1]))
check_password_char_exits_once_in_index_set(str(sys.argv[1]))
