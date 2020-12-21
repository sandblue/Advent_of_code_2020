import sys

def count_encounter_tree(path, slope_right, slope_down):
    file = open(path)
    current_x = 1 - slope_right
    current_y = 0
    count_encounter_tree = 0
    for line in file:
        max_x_pattern = int(len(line)) - 1
        current_y += 1

        if(current_y % slope_down != (slope_down - 1)): 
            continue

        current_x += slope_right
        if( current_x > max_x_pattern):
            current_x = current_x - max_x_pattern

        if( str(line[current_x - 1]) == "#" ):
            count_encounter_tree += 1

    file.close()
    return count_encounter_tree

def find_multiple_of_encounter_in_set_slope(path):
    slope_x_set = [1,3,5,7,1]
    slope_down_set = [1,1,1,1,2]
    multiple_encounter = count_encounter_tree(path, slope_x_set[0], slope_down_set[0])
    if(int(len(slope_x_set) < 2)):
        return multiple_encounter
    for i in range(1, int(len(slope_x_set))):
        multiple_encounter *= count_encounter_tree(path, slope_x_set[i], slope_down_set[i])
    return multiple_encounter

print(count_encounter_tree(str(sys.argv[1]), 1, 1))
print(count_encounter_tree(str(sys.argv[1]), 3, 1))
print(count_encounter_tree(str(sys.argv[1]), 5, 1))
print(count_encounter_tree(str(sys.argv[1]), 7, 1))
print(count_encounter_tree(str(sys.argv[1]), 1, 2))
print(find_multiple_of_encounter_in_set_slope(str(sys.argv[1])))