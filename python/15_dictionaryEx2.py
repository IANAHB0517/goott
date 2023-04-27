# 물건을 수송하는 기차 여러대의 수송량을 합산하여 순위를 매기시오
# 기차의 이름과 수송향은 아리와 같습니다.
# 토마스 5, 헨리 8, 에드워드 9, 에밀리 5, 토마스 4, 헨리 7, 토마스 3, 에밀리 8, 퍼시 5, 고든 13

import operator

trainTupleList = [('토마스' , 5), ('헨리' , 8), ('에드워드' , 9), ('에밀리' , 5), ('토마스' , 4), ('헨리' , 7), ('토마스' , 3), ('에밀리' , 8), ('퍼시' , 5), ('고든' , 13)]
trainDict = {}

rank, curRank = 1, 1

for trainTuple in trainTupleList :
    trainName = trainTuple[0] # 기차이름
    trainWeight = trainTuple[1] #수송량
    if trainName in trainDict : # 기차 이름이 딕셔너리에 있다면
       trainDict[trainName] += trainWeight # 기차이름의 수송량에 수송량 누적
    else : 
        trainDict[trainName] = trainWeight # 딕셔너리에 새로운 기차이름과 수송량 대입
print(trainDict)


# deliveryDict = {'토마스' : 5, '헨리' : 8, '에드워드' : 9, '에밀리' : 5, '토마스' : 4, '헨리' : 7, '토마스' : 3, '에밀리' : 8, '퍼시' : 5, '고든' : 13}


# print(deliveryDict)

# for i in deliveryDict.keys():
   
# for i in deliveryDict.keys():
#      if i not in trainDict.keys() :
#         trainDict[i] = 0
    
   

# for i in deliveryDict.keys():
#     # print(deliveryDict[i])
#     # print(trainDict[i])

#     trainDict[i] += deliveryDict[i]

trainRankList = sorted(trainDict.items(), key=operator.itemgetter(1), reverse=True) #수송량으로 내림차순 정렬

print(trainRankList)

print('기차 \t 총수송량 \t 순위')
print('===================================================')
print(trainRankList[0][0], '\t', trainRankList[0][1], '\t\t', curRank)

for i in range(1, len(trainRankList)) :
    rank += 1
    if trainRankList[i][1] == trainRankList[i-1][1] : # 현재 기차의 수송량이 이전 기차의 수송량과 같다면
        pass # 바디 코드를 비워둔다
    else :
        curRank = rank
    
    print(trainRankList[i][0], '\t', trainRankList[i][1], '\t\t', curRank)
