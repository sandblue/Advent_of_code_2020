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
def find_missing_seat(path):
    file = open(path)
    all_value = {}
    for line in file:
        current_line_value = int(find_id(str(line)))
        all_value[current_line_value] = current_line_value
    file.close()
    x = dict(sorted(all_value.items(), key=lambda item: item[1]))
    prev = None
    for i in x:
        if(prev is None):
            prev = int(i)
        else:
            if(prev+1 != int(x[i])):  
                print(i-1)    
                break
            else:
                prev = int(i)
    return

# un know solution one loop [ un done ]
def find_missing_seat_not_done(path):
    def find_missing_seat(path):
    file = open(path)
    missing_seat = {}
    found = {}
    not_found = {}
    for line in file:
        current_line_value = int(line)
        flag = False
        
        if(current_line_value + 2 not in found):
            missing_seat[current_line_value + 2] = None
        if(current_line_value - 2 not in found):
            missing_seat[current_line_value - 2] = None

        #  xyx 
        if(current_line_value + 1 in found):
            found[current_line_value] = current_line_value
        if(current_line_value - 1  in found):
            found[current_line_value] = current_line_value 
        if(current_line_value + 1 in not_found):
            found[current_line_value + 1] = not_found.pop(current_line_value + 1)
            found[current_line_value] = current_line_value
            if(current_line_value + 1 in missing_seat):
                missing_seat.pop(current_line_value)
            flag = True
        if(current_line_value - 1 in not_found ):
            found[current_line_value - 1] = not_found.pop(current_line_value - 1)
            found[current_line_value] = current_line_value
            if(current_line_value - 1 in missing_seat):
                missing_seat.pop(current_line_value)
            flag = True
        if(not flag):
            not_found[current_line_value] = current_line_value
        if(current_line_value in missing_seat):
            missing_seat.pop(current_line_value)
            
    file.close()
    print(missing_seat)
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
find_missing_seat(str(sys.argv[1]))