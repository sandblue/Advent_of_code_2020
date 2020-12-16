import sys

def multiple_of_pair_sum_equal_2020(path):
    file = open(path)
    list = []
    for line in file:
        list.append(int(line))
    file.close()
    target_sum = 2020
    for i in range(len(list)):
        for j in range(i, len(list)):
            if(target_sum == list[i] + list[j]):
                print("pair_sum_equal_2020 are " + str(list[i]) + " and " + str(list[j]))
                print("multiple_of_pair_sum_equal_2020 are " + str(list[i] * list[j]))
                return list[i] * list[j]
    print("not exits pair that sum equal " + str(target_sum))
    return None

def multiple_of_triple_sum_equal_2020(path):
    file = open(path)
    list = []
    for line in file:
        list.append(int(line))
    file.close()
    target_sum = 2020
    for i in range(len(list)):
        for j in range(i, len(list)):
            for k in range(j, len(list)):
                if(target_sum == list[i] + list[j] + list[k]):
                    print("triple_sum_equal_2020 are " + str(list[i]) + " and " + str(list[j]) + " and " + str(list[k]))
                    print("multiple_of_pair_sum_equal_2020 are " + str(list[i] * list[j] * list[k]))
                    return list[i] * list[j] * list[k]
    print("not exits pair that sum equal " + str(target_sum))
    return None

multiple_of_pair_sum_equal_2020(str(sys.argv[1]))
multiple_of_triple_sum_equal_2020(str(sys.argv[1]))