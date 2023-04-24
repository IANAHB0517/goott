import turtle
import random

# 함수 선언부
def screenLeftClick(x, y) :
    global r, g, b # 함수 외부에서 선언한 r, g, b 변수를 함수 내부에서도 사용하겠다
    turtle.pencolor((r, g, b)) # 튜플 = 읽기 전용 형식
    turtle.pendown()
    turtle.goto(x,y) # x,y 좌표로 거북이를 이동 시킴


def screenRightClick(x,y) :
    # 주어진 x,y 좌표로 거북이 이동시키기 (그림이 그려지면 안됨)

    turtle.penup()
    turtle.goto(x,y)

def screenMidClick(x,y) :
    # 랜덤하게 색상 변경
    global r, g, b, pSize
    r = random.random()
    g = random.random()
    b = random.random()
    pSize = random.randrange(1, 10) #randrange 는 지정해준 범위 내의 값을 발생시킨다
    turtle.pensize(pSize)


pSize = 10 # 펜의 크리

r, g, b = 0.0, 0.0, 0.0 # 여러개의 변수를 한꺼번에 선언 할 수 있고 초기화 할 수도 있다

turtle.title("꼬북이로 그림 그리기")
turtle.shape("turtle")
turtle.pensize(pSize)

turtle.onscreenclick(screenLeftClick, 1) # 왼쪽 버튼이 클릭되면 screenLeftClick()를 실행한다, 2 = 마우스 휠 , 3 = 우클릭
turtle.onscreenclick(screenRightClick, 3) #  오른쪽 버튼이 클릭되면 그려지지 않는 상태로 커서가 이동함
turtle.onscreenclick(screenMidClick, 2) # 마우스 휠 버튼이 클릭되면 screenMidClick() 함수가 호출된다

turtle.done()