# tuple 생성
tuple1= (10, 20, 30)
print (tuple1)

# tuple1.append(40) 튜플은 읽기 전용이므로 예외 발생
# tuple1  [0] = 100 튜플은 데이터 수정도 되지 않는다

print(tuple1[0])
# 데이터의 접근은 list에서 처럼 접근 가능하다
print(tuple1[1:3])

#tuple의 삭제
#del(tuple1[1])
#del(tuple1) # 튜플 자체를 삭제 할 수 는 있지만 튜플의 특정 원소만 삭제 할 수 없다

myList = list(tuple1) # 튜플을 list로 바꾸지
print (myList)

list2 = [100, 200, 300]
myTuple = tuple(list2) # list를 튜플로 바꾸어 새로운 tuple로 반환
print(myTuple)