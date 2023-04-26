# 거북이 세마리가 임의로 화면을 돌아다니게 한다.
# 세마리중 서로 만나는 거북이가 있다면 움직임을 멈추고 모든 거북이의 크기를 세배로 키운다.

import turtle
import random
import math

turtle1, turtle2, turtle3 = [None] * 3 # null이 3개 있다 # 거북이 3마리의 객체
turtle1X, turtle1Y, turtle2X, turtle2Y, turtle3X, turtle3Y = [0] * 6 # 거북이의 x,y 좌표

screenWidth, screenHeight = 300 , 300 # 위도우 창 사이즈

# __name__ 이 해당 모듈의 이름을 가지고 온다
# if __name__ == "__main__" : # 현재코드가 main 모듈 이라면

turtle.title("거북이 만나기")
turtle.setup(width = screenWidth + 50, height=screenHeight + 50) # 50px은 여백을 준것이다
turtle.screensize(screenWidth, screenHeight)

turtle1 = turtle.Turtle("turtle") #Turtle() 생성자
turtle1.color("red")
turtle1.penup()

turtle2 = turtle.Turtle("turtle") 
turtle2.color("green")
turtle2.penup()

turtle3 = turtle.Turtle("turtle") 
turtle3.color("blue")
turtle3.penup()

# 각 거북이의 초기 위치
turtle1.goto(-100, -100)
turtle2.goto(+100, -100)
turtle3.goto(+100, +100)

while True :
    maxPos = screenWidth/2
    maxMinPos = -(screenWidth/2)

    angle1 = random.randrange(0, 60) # 0 <= 난수 < 359
    dist1 = random.randrange(maxPos,maxMinPos) # 1 <= 난수 < 99

    angle2 = random.randrange(0, 60) # 0 <= 난수 < 359
    dist2 = random.randrange(maxPos,maxMinPos) # 1 <= 난수 < 99

    angle3 = random.randrange(0, 60) # 0 <= 난수 < 359
    dist3 = random.randrange(maxPos,maxMinPos) # 1 <= 난수 < 99

    # 각 거북이들의 좌표를 구하자
    turtle1X = turtle1.xcor(); turtle1Y = turtle1.ycor()
    turtle2X = turtle2.xcor(); turtle2Y = turtle2.ycor()
    turtle3X = turtle3.xcor(); turtle3Y = turtle3.ycor()

   

    turtle1.left(angle1); turtle1.forward(dist1)
    turtle2.left(angle2); turtle2.forward(dist2)
    turtle3.left(angle3); turtle3.forward(dist3)

    if math.sqrt((turtle1X - turtle2X)**2 + (turtle1Y - turtle2Y)**2) <= 20 or math.sqrt((turtle1X - turtle3X)**2 + (turtle1Y - turtle3Y)**2) <= 20 or math.sqrt((turtle2X - turtle3X)**2 + (turtle2Y - turtle3Y)**2) <= 20 :
        turtle1.turtlesize(3); turtle2.turtlesize(3); turtle3.turtlesize(3)
        break

turtle.done()