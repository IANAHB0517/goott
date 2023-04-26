# 거북이 100마리의 리스트를 아래와 같이 생성 후 거북이 100마리가 화면 중앙에서 임의의 위치로 차례대로 이동 하도록한다
# 거북이는 화ㅓ면 중앙(0,0)에서 지정된 위치(x,y) 까지 선을 그리며 이동하고, 크기과 색상의 모양은 임의의 값으로 한다

#[거북이모양, x위치 ,y위치, 거북이 크기, 거북이색상(R), 거북이색상(G), 거북이색상(B)]
# 거북이 100마리를 하나으 ㅣ리스트에 담아 고한리한다.
# 기본 화면 사이즈는 500, 500으로 한다
# 거북이의 사이즈는 1~3의 난수로 정의 한다

import turtle
import random
import math

turtles = []

myTurtle, xPos, yPos, tSize, tShape = [None] * 5
shapeList = []

sWidth, sHeight = 500, 500

turtle.title("리스트를 활용한 거북이 그림 그리기")
turtle.setup(sWidth + 50, sHeight + 50)
turtle.screensize(sWidth, sHeight)

shapeList = turtle.getshapes()

for i in range(10) :
    random.shuffle(shapeList)
    myTurtle = turtle.Turtle(shapeList[0]) # 거북이 모양
    xPos = random.randrange(-250, 250 ) # -250 ~ 250 이동할 x 좌표
    yPos = random.randrange(-250, 250 ) # -250 ~ 250 이동할 y 좌표
    r = random.random(); g = random.random(); b = random.random() # 색상

    tSize = random.randrange(1, 3) # 크기

    turtles.append([myTurtle, xPos, yPos, tSize, r, g, b])
print(turtles)

for tList in turtles :
    myTurtle = tList[0]
    myTurtle.color(tList[-1], tList[-2], tList[-3])
    myTurtle.pencolor(tList[-1], tList[-2], tList[-3])
    myTurtle.turtlesize(tList[3])
    myTurtle.goto(tList[1], tList[2])


while True :
    for tList in turtles :
        tList[1] = random.randrange(-250, 250)
        tList[2] = random.randrange(-250, 250)
        tList[0].goto(tList[1], tList[2])

turtle.done()

