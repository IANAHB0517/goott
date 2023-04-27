# 딕셔너리 생성
dict1 = {1 : 'a', 2 : 'b', 3: 'c'}
print(dict1)

student1 = {'학번' : 23042701, '이름' : '둘리', '나이' : 20}
print(student1)

# 생성된 딕셔너리 키와 값을 추가
student1['연락처'] = '010-1234-5678'
print(student1)

# 키로 값을 찾는 방법
print(student1['학번'])
print(student1.get('이름'))
# print(student1.get['주소']) w존재하지 않는 key에 접근하려 했으므로 예외 key Error

#student1 딕셔너리가 가지고 있는 모든 key를 반환(dict_keys[])
print(student1.keys())
keys = list(student1.keys())
print(keys)

# student1 딕셔너리가 가지고 있는 모든 values를 반환
print(student1.values())
values = list(student1.values())
print(values)

# student1 딕셔너리의 모든 값을 tuple 형태로 반환
print(student1.items())

#딕셔너리에 해당 키가 있는지 여부 검사
print('이름' in student1)
print('주소' in student1)

if '주소' in student1 :
    print(student1['주소'])
else :
    print('주소가 없습니다!')


# student1에 있는 모든 key와 values를 출력 (반복문 이용)
for i in student1.keys() : # keys의 리스트가 있는 동안 i를 반복
    print('%s : %s' %(i, student1[i]))

# 딕셔너리에 있는 데이터를 정렬
import operator
trainDict = {'Tomas' : '토마스', 'Edward' : '에드워드', 'Henry' : '헨리', 'Gothen' : '고든', 'James' : '제임스'}
# sortedByKeyList = sorted(trainDict.keys(), key=operator.itemgetter(0))
sortedTrainList =  sorted(trainDict.items(), key=operator.itemgetter(0)) # 튜플형태로 나옴 , 키 값을 기준으로 values 값을 가져옴

# print("keys()를 활용한 정렬 : " + sortedByKeyList) # List라 문자열이랑 + 가 안됨
print(sortedTrainList)