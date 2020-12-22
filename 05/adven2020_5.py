import sys
import math

def find_highest_seat_id(path):
    file = open(path)
    highest_id = 0
    for line in file:
        current_line_value = int(find_id(str(line)))
        if(int(highest_id) < int(current_line_value)):
            highest_id = int(current_line_value)

    file.close()
    print(highest_id)
    return

# triple loop
def find_missing_seat_triple_loop(path):
    file = open(path)
    all_value = {}
    for line in file:
        current_line_value = int(find_id(str(line)))
        all_value[current_line_value] = current_line_value
    file.close()
    sorted_val = dict(sorted(all_value.items(), key=lambda item: item[1]))
    prev = None
    for i in sorted_val:
        if(prev is None):
            prev = int(i)
        else:
            if(prev+1 != int(sorted_val[i])):  
                print(i-1)    
                break
            else:
                prev = int(i)
    return

    
# double loop
def find_missing_seat_double_loop(path):
    file = open(path)
    all_value = {}
    highest_id = 0
    lowest_id = None 
    for line in file:
        current_line_value = int(find_id(str(line)))
        all_value[current_line_value] = current_line_value
        if(int(highest_id) < int(current_line_value)):
            highest_id = int(current_line_value)
        if(lowest_id is None):
            lowest_id = current_line_value
        else:
            if(int(current_line_value) < int(lowest_id)):
                lowest_id = int(current_line_value)
        
    file.close()
    for i in range(lowest_id, highest_id, 1):
        if(i not in all_value):
            print(i)
            return i 
    return

#find number that not found -1 +1 
def find_un_near_seat(path):
    file = open(path)
    found = {}
    not_found = {}
    for line in file:
        current_line_value = int(line)
        flag = False
        #  xyx z xyx --> result = z
        if(current_line_value + 1 in found or current_line_value - 1  in found):
            found[current_line_value] = current_line_value
            flag = True
        if(current_line_value + 1 in not_found):
            found[current_line_value + 1] = not_found.pop(current_line_value + 1)
            found[current_line_value] = current_line_value
            flag = True
        if(current_line_value - 1 in not_found ):
            found[current_line_value - 1] = not_found.pop(current_line_value - 1)
            found[current_line_value] = current_line_value
            flag = True
        if(not flag):
            not_found[current_line_value] = current_line_value
    file.close()
    print(not_found)
    return

    

def find_id(code):
    code = code.replace("\n", "")
    row_code_init = code[:-3]
    column_code_init = code[-3:]
    row_id = find_row(row_code_init, 0, 127)
    column_id = find_column(column_code_init, 0, 7)
    return int(row_id * 8) + int(column_id)


def find_row(column_code, min, max):
    if(len(column_code) > 1):
        if(column_code[0] == "F"):
            max = max - math.floor((max - min)/2) - 1 
        else:
            min = min + math.floor((max - min)/2) + 1
        return find_row(column_code[1:], min , max)
    else:
        if(column_code[0] == "F"):
            return min
        else:
            return max

def find_column(row_code, min, max):
    if(len(row_code) > 1):
        if(row_code[0] == "L"):
            max = max - math.floor((max - min)/2) - 1 
        else:
            min = min + math.floor((max - min)/2) + 1
        return find_column(row_code[1:], min , max)
    else:
        if(row_code[0] == "L"):
            return min
        else:
            return max

   



#find_highest_seat_id(str(sys.argv[1]))
find_missing_seat_double_loop(str(sys.argv[1]))
#find_missing_seat_triple_loop(str(sys.argv[1]))