import turtle # 중괄호는 class를 의미한다

turtle.shape("turtle") # 커서의 모양을 turtle로 변경  # 보라색 박스는 메서드이다
# turtle.forward(200)
# turtle.right(90)
# turtle.forward(200)
# turtle.right(90)
# turtle.forward(200)
# turtle.right(90)
# turtle.forward(200)

for i in range(0, 4) :  # i 가 가질수 있는 값이 0,1,2,3 까지 
    turtle.forward(200)
    turtle.right(90)

turtle.done()