import sys

class Node():
    count=None
    lessCountNode=None
    moreCountNode=None

def find_sum_group_say_yes_count(path):
    file = open(path)
    result = 0
    text = str(file.read()).split("\n\n")
    for line in text:
        line = line.replace("\n", "")
        founded_char = {}
        for character in line:
            if(character not in founded_char):
                result += 1
                founded_char[character] = True
    file.close()
    print(result)
    return


def find_sum_all_person_in_group_say_yes_count(path):
    file = open(path)
    result = 0
    text = str(file.read()).split("\n\n")
    for lines in text:
        lines = lines.split("\n")
        person_count = 0
        summary_all_y_found = {}
        for line in lines:
            person_count += 1
            founded_char = {}
            for character in line:
                if(character not in founded_char):
                    founded_char[character] = True
            for uniq_found in founded_char:
                if(uniq_found not in summary_all_y_found):
                    summary_all_y_found[uniq_found] = 1
                else:
                    summary_all_y_found[uniq_found] = summary_all_y_found[uniq_found] + 1
        for answer_count in summary_all_y_found.values():
            if(answer_count == person_count):
                result += 1

    file.close()
    print(result)
    return    

find_sum_group_say_yes_count(str(sys.argv[1]))
find_sum_all_person_in_group_say_yes_count(str(sys.argv[1]))