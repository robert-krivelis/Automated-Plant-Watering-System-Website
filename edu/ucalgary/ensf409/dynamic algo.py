# Given a set of positive integers, and a target sum k, find the subset that sums to exactly k or least exceeds k. (i.e. equal or minimal overshoot) 
# def least_gt_subset_sum(items, k):
#     vals = [None]*(k+1)
#     vals[0] = ()
#     best_v = None
#     best_k = 0
#     for item in items:
#         for i in range(k-1, -1, -1):
#             if vals[i] is not None:
#                 if i + item <= k and vals[i+item] is None:
#                     vals[i+item] = (*vals[i], item)
#                 if i + item > k and (best_v is None or i + item < best_v):
#                     best_v = i + item
#                     best_k = (*vals[i], item)
#     if vals[k] is not None:
#         return vals[k]
#     else:
#         return best_k

# items = []
# least_gt_subset_sum(items, 2)
from traceback import print_list


item_list = [
('Tomato Sauce, jar', 0, 80, 10, 10, 120), 
('Tomato Sauce, jar', 0, 80, 10, 10, 120), 
('Tomato Sauce, jar', 0, 80, 10, 10, 120), 
('Apple, dozen', 0, 100, 0, 0, 624), 
('Apple, dozen', 0, 100, 0, 0, 624), 
('Granola Bar, box', 71, 0, 6, 23, 936), 
('Chicken broth, can', 0, 0, 14, 86, 95), 
('Baby carrots, pound', 0, 100, 0, 0, 159), 
('Broccoli, 3 bunches', 0, 92, 8, 0, 621), 
('Broccoli, 3 bunches', 0, 92, 8, 0, 621), 
('Wheat bread, loaf', 96, 0, 4, 0, 2192), 
('Wheat bread, loaf', 96, 0, 4, 0, 2192), 
('Wheat bread, loaf', 96, 0, 4, 0, 2192), 
('Orange, dozen', 0, 100, 0, 0, 864), 
('Orange, dozen', 0, 100, 0, 0, 864), 
('Eggs, dozen', 0, 0, 9, 91, 864), 
('Eggs, dozen', 0, 0, 9, 91, 864), 
('Chicken breast, pound', 0, 0, 30, 70, 730), 
('Pasta, dry, pound', 75, 0, 13, 12, 1683), 
('Tuna, six large cans', 0, 0, 19, 81, 1776), 
('Tuna, six small cans', 0, 0, 19, 81, 900), 
('Tuna, six large cans', 0, 0, 19, 81, 1776), 
('Tuna, six small cans', 0, 0, 19, 81, 900), 
('Cheddar cheese, pound', 0, 0, 23, 77, 1851), 
('Cheddar cheese, pound', 0, 0, 23, 77, 1851), 
('Quinoa, pound', 87, 0, 13, 0, 1397), 
('Lettuce, 3 heads', 0, 100, 0, 0, 225), 
('Bananas, bunch', 0, 100, 0, 0, 672), 
('Milk, 1%, 4 L', 0, 0, 3, 97, 1785), 
('Whole grain bread, loaf', 89, 0, 11, 0, 1904), 
('Ground beef, pound', 0, 0, 26, 74, 1179), 
('Ground beef, pound', 0, 0, 26, 74, 1179), 
('Ground beef, pound', 0, 0, 26, 74, 1179), 
('Avocado, dozen', 0, 100, 0, 0, 2880), 
('Avocado, dozen', 0, 100, 0, 0, 2880), 
('Corn tortillas, pound', 94, 0, 6, 0, 989), 
('Pasta, dry, two pounds', 75, 0, 13, 12, 3366), 
('Pasta, dry, two pounds', 75, 0, 13, 12, 3366), 
('Oatmeal cookies, dozen', 69, 0, 6, 25, 2436), 
('Peanut butter, 2 kg jar', 0, 0, 23, 78, 11940), 
('Bran flakes, 1 kg', 90, 0, 10, 0, 3520), 
('Nutella, 1 kg', 0, 0, 7, 93, 5330), 
('Ground pork, pound', 0, 0, 17, 83, 1193), 
('Meatballs, 1 kg', 0, 0, 14, 86, 2860), 
('Tomatoes, 1 kg', 0, 100, 0, 0, 180), 
('Tomatoes, 1 kg', 0, 100, 0, 0, 180), 
('Pancake mix, 1 kg', 74, 0, 12, 14, 3400), 
('Maple syrup, 1 L', 0, 0, 0, 100, 3276), 
('Cauliflower, 2 heads', 0, 100, 0, 0, 420), 
('Strawberries, 2 kg', 0, 100, 0, 0, 640), 
('Cantaloupe, dozen', 0, 100, 0, 0, 3324), 
('Cottage cheese', 0, 0, 11, 89, 840), 
('Trail mix, 1 kg', 21, 0, 20, 59, 6000), 
('Soy protein, 1 kg', 0, 0, 88, 12, 3350), 
('Turkey, whole', 0, 0, 23, 77, 5752), 
('Mixed nuts, 1 kg', 0, 0, 23, 77, 6000), 
('Lentils, 1 kg', 63, 0, 25, 12, 3520), 
('Greek yogurt, plain, 1 kg', 0, 0, 9, 91, 970), 
('Tofu, 1 kg', 0, 0, 19, 81, 2700), 
('Mayonnaise, 1 kg', 0, 0, 0, 100, 6800), 
('Kidney beans, dozen cans', 25, 0, 9, 66, 6840), 
('Bacon, 1 kg', 0, 0, 14, 86, 3930), 
('Quinoa, 1 kg', 70, 0, 24, 6, 925), 
('Chocolate chip cookies, 500 g', 71, 0, 1, 29, 2440), 
('Boba green tea, 4 cans', 88, 0, 0, 12, 924), 
('Saltines', 79, 0, 1, 20, 1960), 
('Instant ramen, package 20', 89, 0, 11, 0, 3080), 
('Frozen pizza, pepperoni', 56, 4, 18, 22, 1215), 
('Banana, bunch 5', 0, 97, 3, 0, 605), 
('Banana, bunch 6', 0, 97, 3, 0, 1210), 
('Banana, bunch 10', 0, 97, 3, 0, 726), 
('Sweet potato, bag', 0, 95, 5, 0, 1110), 
('Manioc, 1 pound', 100, 0, 0, 0, 635), 
('Zucchini, 5', 0, 75, 25, 0, 165), 
('Zucchini, 10', 0, 75, 25, 0, 330), 
('Celeriac, 1 kg', 0, 67, 33, 0, 250), 
('Turnip, 1 pound', 0, 100, 0, 0, 127), 
('Kidney beans, 1 pound', 72, 0, 28, 0, 385), 
('Beets, 1 pound', 0, 87, 13, 0, 193), 
('Chickpeas, 1 kg', 67, 0, 22, 11, 1640), 
('Hummus', 24, 0, 11, 65, 450), 
('Beets, 1 pound', 0, 87, 13, 0, 193), 
('Carrots', 0, 100, 0, 0, 264), 
('Wheaties, family size', 92, 0, 8, 0, 8000), 
('Whole wheat flour, 10 kg', 72, 0, 12, 16, 3660), 
('Eggs, 1 kg', 2, 0, 36, 62, 1430), 
('Avocado, 1 pound', 19, 76, 5, 0, 725), 
('Black olives, large tin', 23, 0, 0, 77, 2200), 
('Coconut milk, 1 liter', 4, 0, 0, 96, 1960), 
('Avocado, 1 pound', 19, 76, 5, 0, 725), 
('Parsnip, 1 kg', 0, 95, 5, 0, 750), 
('Yam, 1 kg', 0, 93, 0, 2, 1160), 
('Mushrooms, 800g', 0, 48, 44, 8, 160), 
('Canned corn, 1 tin', 73, 0, 14, 13, 210), 
('Lentils, 1 kg', 49, 0, 46, 5, 1160), 
('Almonds, large bag', 14, 0, 14, 72, 1600), 
('Protein shake, 10 cans', 12, 0, 72, 16, 1600), 
('Soy burger, 20', 21, 0, 41, 38, 4800), 
('Whole grain bread', 74, 0, 16, 10, 2000), 
('Everything bagel, 6', 79, 0, 13, 8, 1440), 
('Beyond Breakfast sausage', 14, 0, 25, 61, 900), 
('Black beans, 1 kg', 69, 0, 31, 0, 1290), 
('Horse gram, 1 kg', 72, 0, 28, 0, 3210), 
('Green peas, 1 pound', 0, 54, 46, 0, 430), 
('Teff flour, 2 kg', 83, 0, 12, 5, 6920), 
('Creamed spinach', 0, 40, 16, 44, 1000), 
('Creamed spinach', 0, 40, 16, 44, 1000), 
('Creamed spinach', 0, 40, 16, 44, 1000), 
('String cheese, pack of 20', 6, 0, 29, 65, 1400), 
('String cheese, pack of 20', 6, 0, 29, 65, 1400), 
('Brown rice, small bag', 86, 0, 8, 6, 1500), 
('Brown rice, large bag', 86, 0, 8, 6, 15000), 
('Butter, 4 sticks', 0, 0, 0, 100, 424), 
('Apple juice, 1 kg', 0, 87, 4, 9, 450), 
('Raisin bran, 1 box', 87, 1, 9, 3, 1710), 
('Oatmeal, 10 packets', 69, 0, 15, 16, 1000), 
('Frozen waffles, pack of 10', 64, 0, 9, 27, 850), 
('Frozen blueberries, 2100 g', 0, 95, 5, 0, 1200), 
('Cocktail fruits, tin', 0, 88, 4, 8, 51), 
('Olive oil, 1 kg', 0, 0, 0, 100, 8840), 
('Lima beans, 1 pound', 79, 0, 18, 3, 326), 
('Dried figs, 500 g', 0, 88, 0, 12, 838), 
('Dried figs, 500 g', 0, 88, 0, 12, 838), 
('Tinned sardines, pack of 5', 0, 0, 40, 60, 1060), 
('Tinned sardines, pack of 5', 0, 0, 40, 60, 1060), 
('Egg salad, 57 g', 19, 0, 19, 62, 94), 
('Grape jelly', 0, 5, 0, 95, 1500), 
('Onions, net of 10', 0, 93, 7, 0, 640), 
('Radish, large bunch', 0, 62, 23, 15, 84), 
('Kimchi, 400 g', 0, 95, 5, 0, 120), 
('Roasted seaweed, 250 g', 0, 26, 26, 48, 1500), 
('Honey, 300 g', 0, 0, 0, 100, 987), 
('Escargot, 500 g', 0, 0, 13, 87, 1565), 
('Salmon, 5 filets', 0, 0, 51, 49, 1400), 
('Frozen squid, 1 pound', 0, 0, 72, 28, 417), 
('Cocktail shrimp, package', 0, 0, 36, 64, 300), 
('Roasted mixed nuts, 56 oz', 13, 0, 13, 74, 9520), 
('Farro, medium bag', 81, 0, 14, 5, 1870), 
('Pearl barley, 1 pound', 0, 0, 44, 56, 1814), 
('Plaintain, 4', 0, 97, 3, 0, 872), 
('Frozen french fries, 500 g', 65, 0, 6, 29, 1155), 
('Whey powder, large jar', 16, 0, 77, 7, 3900), 
('Oranges, bag', 0, 96, 4, 0, 1740), 
('Tinned vegetable chilli', 52, 0, 18, 30, 600), 
('Raisins, 40 g', 0, 97, 3, 0, 1320), 
('Dates, container', 0, 97, 3, 0, 1200), 
('Lettuce, 1 head', 0, 68, 32, 0, 93), 
('Sauerkraut, 1 kg', 0, 100, 0, 0, 250), 
('Brioche', 67, 0, 12, 21, 768), 
('Bean sprouts, 1 kg', 0, 55, 45, 0, 52), 
('Peaches, crate', 0, 93, 7, 0, 1740), 
('Peaches, crate', 0, 93, 7, 0, 1740), 
('Pumpkin, very large', 0, 89, 11, 0, 1180), 
('Pumpkin, small', 0, 89, 11, 0, 118), 
('Snake gourd', 0, 73, 16, 11, 68), 
('Hominy, canned, 1 kg', 82, 0, 8, 10, 720), 
('Dried apricots, package', 0, 96, 4, 0, 1000), 
('Cake mix', 68, 0, 7, 25, 1300), 
('Adzuki bean, medium package', 75, 0, 24, 1, 3240), 
('Strawberry Skyr', 0, 0, 59, 41, 252), 
('Keffir', 0, 0, 22, 78, 600), 
('Cottage cheese', 0, 0, 56, 44, 480), 
('Bluegreen algae powder, 100 g', 9, 0, 70, 21, 177), 
('Chia seeds, 100 g', 29, 0, 16, 55, 510), 
('Flax oil, bottle', 0, 0, 0, 100, 1920), 
('Fish fingers, box of 30', 37, 0, 25, 38, 1530), 
('Cream of mushroom soup', 31, 0, 8, 61, 200), 
('Granola cereal', 60, 0, 19, 21, 1260), 
('Black fungus, 200 g', 0, 77, 23, 0, 564)]

# Ok so the algorithm is pretty simple.
# Objective: Get the least wasteful (calorie wise) combination of food while still meeting the calorie requirements
# Brute force solution, broad: 
# 1. create a list of all combinations of food that calories_sum > calories_req
# 2. sort list by calories_sum
# 3. check if satisfies individual category requirements
# 
# sort all the combinations of food by total calories. starting at total_calories = calories_req, check if that combination  
# 
# 
# 
#  Use combinationSum2 solution (https://leetcode.com/problems/combination-sum-ii/) to find all unique combinations in a list where the list numbers sum to target 
# 1. Find out

# select any one combination if equally wasteful


l2 = [item[1:] for item in item_list]
for item in l2:
    print(str(list(item)) + ',')
# print('\n'.join(str(l2)))
# print(('",\n' + '"').join(l2))







# grains = [item[1] for item in item_list]
# # grains.sort()
# # print(grains)
# import itertools
# # grains = [grain for grain in grains if grain!=0]

# grains = grains[-10:]
# stuff = [1, 2, 3]
# vals = set()
# for L in range(0, len(grains)+1):
#     for subset in itertools.combinations(grains, L):
#         if sum(subset)>1:
#             vals.add(sum(subset))
# val_list = list(vals)
# val_list.sort()
# print(val_list)
# # print([val for val in vals if val > 50])

# def combinationSum2(candidates, target):
#     candidates.sort()
#     res = []
#     def backtrack(cur, pos, target):
#         if target == 0:
#             res.append(cur.copy())
#         if target <= 0:
#             return
        
#         prev = -1
#         for i in range(pos, len(candidates)):
#             if candidates[i] == prev:
#                 continue
#             cur.append(candidates[i])
#             backtrack(cur, i + 1, target - candidates[i])
#             cur.pop()
#             prev = candidates[i]
            
#     backtrack([], 0, target)
#     return res

# print(combinationSum2(grains, 60)) 


# a lot of the values equal zero. my algos are bad 
# but that's fine i think, we just keep the 0s in there
# this algorithm in python can run even though python is a slow language. 
# obviously we're looking for exactly one value but thats fine 


# import itertools

# numbers = [1, 2, 3, 7, 7, 9, 10]
# print(itertools.combinations(numbers, 3))
# target = 10

# result = [seq for i in range(len(numbers), 0, -1)
#           for seq in itertools.combinations(numbers, i)
#           if sum(seq) > target]

# print(result)