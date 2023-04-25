# 단순 for문
for i in range(1, 6) :
    # end=을 생략하면 print 수행후 \n 까지 출력해 줄 바꿈을 하게 된다.
    print("%d " %i, end=" ")

print()
# 1부터 100까지 합
sum = 0
for i in range (1, 101) :
    sum += i
print("1부터 100까지의 합 : %d" %sum)


# 2중 for문을 이용한 구구단

for i in range (1, 10) :
    print("%d 단" %i)
    for j in range (1,10):
        print("%d X %d = %d" %(i, j, i*j), end="   ")
    print()