import sys

class Bag:
    def __init__(self):
        self.name = None
        self.count = 0
        self.contain = []

    def setName(self, name):
        self.name = name.replace("\n","").replace(" ","")

    def getCount(self):
        return self.count
 
    def setCount(self, count):
        self.count = count

    def getName(self):
        return self.name

    def getContain(self):
        return self.contain

def count_bag_that_contain_at_least_one_shiny_bag(path):
    file = open(path)
    bag_list = {}
    bag_result = []
    for line in file:
        imformation = line.split("contain")
        current_bag = Bag()
        current_bag.setName(imformation[0])
        contain_bag_list = imformation[1].split(",")
        print(current_bag.getName())
        for bag in contain_bag_list:
            bag_imformation = bag.split(" ")
            contained_bag = Bag()
            contained_bag.setName(bag_imformation[2] + bag_imformation[3] + "bags")
            current_bag.contain.append(contained_bag)
            if(contained_bag.getName() == "shinygoldbag" or contained_bag.getName() == "shinygoldbags" or contained_bag.getName() in bag_result):
                if(current_bag.getName() not in bag_result):
                    bag_result.append(current_bag.getName())
                    loop_check_prev(bag_list, bag_result, current_bag)
        bag_list[current_bag.getName()] = current_bag
    print(len(bag_result))
    
def loop_check_prev(bag_list, bag_result, current_bag):
    for prev_bag in bag_list:
        for a_bag_contain_in_prev in bag_list[prev_bag].contain :
            if(current_bag.getName() == a_bag_contain_in_prev.getName()):
                if(prev_bag not in bag_result):
                    bag_result.append(prev_bag)
                    loop_check_prev(bag_list, bag_result, bag_list[prev_bag])

def count_sum_bag_on_shiny_bag(path):
    file = open(path)
    bag_list = {}
    for line in file:
        imformation = line.split("contain")
        current_bag = Bag()
        current_bag.setCount(1)
        current_bag.setName(imformation[0])
        contain_bag_list = imformation[1].split(",")
        for bag in contain_bag_list:
            bag_imformation = bag.split(" ")
            contained_bag = Bag()
            contained_bag.setName(bag_imformation[2] + bag_imformation[3] + " bags")
            if(contained_bag.getName() != "otherbags.bags"):
                contained_bag.setCount(int(bag_imformation[1]))
                current_bag.contain.append(contained_bag)
        bag_list[current_bag.getName()] = current_bag
    shiny_bags = bag_list["shinygoldbags"]
    print(getSumBag("shinygoldbags", bag_list) - 1)


def getSumBag(bag_name, bag_list):
    sum_bag = 1
    for deep in bag_list[bag_name].getContain():
        sum_bag += ((getSumBag(deep.getName(), bag_list) * deep.getCount()))
    print(str(sum_bag) + " " + str(bag_name))
    return sum_bag


#count_bag_that_contain_at_least_one_shiny_bag(str(sys.argv[1]))
count_sum_bag_on_shiny_bag(str(sys.argv[1]))