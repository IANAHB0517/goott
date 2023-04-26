# 리스트 데이터 타입

list1 = [0, 0, 0, 0]
sum = 0
list1[0] = 45; list1[1]=33; list1[2] = 55; list1[3]= 100

sum = list1[0] + list1[1] + list1[2] + list1[3]
print(sum)

list1.append(100)

sum = list1[0] + list1[1] + list1[2] + list1[3] + list1[4]
print(sum)

list2 = [] # 빈 배열 생성
list2.append(2)
list2.append(3)
print(list2)

list3 = []
for i in range (1, 6) :
    list3.append(i)
print(list3)

# 파이썬의 list는 모든 데이터타입을 다 원소로 가질 수 있다
list4 = [10, 20, 30, '파이썬', False, 3.14]
print(list4)

# list의 원소에 접근하는 방법
list5 = [10, 20, 30, 40, 50]
print(list5[-1]) # index 값에 음수를 붙이면 리스트의 끝에서 부터 탐색하여 접근한다\
print(list5[1:3]) # 1번째 index 부터 3 -1 번째 index값 까지
print(list5[0:4]) # 0번쨰 index 부터 4 -1 번째 index값 까지
print(list5[:3]) # 처음부터  3 -1 번째 index값 까지
print(list5[3:]) # 3번째 index 부터 끝까지
print(list5[-2 :-4 ]) # index는 오른쪽에서 부터 세더라도 탐색 자체는 왼쪽에서 오른쪽으로 하기 때문에 시작과 끝을 바꾸어주어야 한다
print(list5[-4 :-2 ])

# 2차원 배열 처럼 사용하기
list6 = [100,200]
list6[1] = [20,30]
print(list6)
del(list6[1]) # 리스트의 n 번째 요소 삭제
print(list6)

# list 조작 함수
myList = [30, 20, 10]
print("현재 리스트 : %s" %myList)

myList.append(40)
print("myList에서 pop()으로 꺼내기 : %s" %myList.pop()) # 끝에서 하나 추출
print("현재 리스트 : %s" %myList)

myList.sort()
print("sort() 후 리스트 : %s" %myList)

print("myList에서 20의 위치 : %d" %myList.index(20)) # 20 값의 인덱스값 반환
# print("myList에서 100의 위치 : %d" %myList.index(100)) # 해당 값이 없으므로 예외 발생

myList.reverse()
print("reverse 후 리스트 : %s" %myList)

myList.insert(2, 200) # 리스트의 2번쨰에 200값 끼워넣기
print("insert후 리스트 : %s" %myList)

myList.remove(20) # 리스트에 있는 20 값을 삭제
print("remove후 리스트 : %s" %myList)

myList.extend([1000, 2000, 3000])
print("extend후 리스트 : %s" %myList)

print("myList의 요소 개수 : %d" %len(myList)) # 리스트에 있는 요소의 개수

myList.append(10)
print("myList에서 10의 개수 : %s" %myList.count(10) )

newList = sorted(myList) # myList를 정렬 후 새로운 list로 반환
print("sorted전의 myList : %s" %myList )
print("sorted후의 newList : %s" %newList ) 