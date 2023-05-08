# String

list1 = [10, 20, 30, 40, 50]
print (list1[2 : 4])
# python은 C언어를 기반으로 만들어졌기 때문에 char 타입 외에는 문자를 표시하는 데이터 타입이 없기 때문에
# 문자열을 문자 배열로 처리 한다
# 문자열 처리의 기본 : 리스트와 사용 방법이 비슷
str = "우리나라 대한민국"
print(str[1:3])
print(str + " 최고")
str = ' 최고 ' * 5
print(str)
print(len(str))

# 문자열 관련 함수
str = "Python is best programming Language"
print(str.upper()) # 대문자로
print(str.lower()) # 소문자로
print(str.swapcase()) # 대문자는 소문자로 소문자는 대문자로
print(str.title()) # 첫글자만 대문자로

str = "Python is very simple. 정말? Python이 그래?"
print(str.count("Python")) # 해당 문자열이 몇번 나왔는지 count 해줌
print(str.find("Python")) # indexof 와 같음 찾지 못하면 0 반환
print(str.rfind("Python")) # lastIndexOf() 와 동일
print(str.find("good")) # find()는 찾는 단어가 없으면 - 1반환
# print(str.index("goㅐd")) # index()는 찾는 단어가 없을 때 예외발생
print(str.startswith("Python"))  # 문자열이 해당 문자열로 시작하는지 bool 반환
print(str.endswith("정말")) # 문자열이 해당 문자열로 끝나는지 bool 반환

str = "   파 이   썬"
print(str.strip()) # 문자열의 앞뒤 공백 제거
print(str.rstrip()) # 뒤 공백 제거 (오른쪽)
print(str.lstrip()) # 앞 공백 제거 (왼쪽)
print(str.replace(" ", "")) # taget 값 , result 값

str = "하나 : 둘 : 셋 : 넷"
print(str.split(":")) # js의 split과 같은 함수. 리스트로 반환해 준다

# map : before의 모든 원소를 하나씩 꺼내 int() (생성자) 에 대입시켜 호출해 준다.
before = ['2023', '04', '28']
after =list(map(int, before)) # before 리스트에서 가지고온 요소를 int 타입으로 매핑 시킨다
print(after)

# 문자열이 모두 숫자인가?
print('1234'.isdigit()) # True
print('가나다라'.isdigit()) # False
print('1234가나다'.isdigit()) # 

# 영문자 형식인가?
print('1234'.isalpha())

# 모든 문자가 소문자인가?
print('abcd'.islower())

# 모든 문자가 대문자인가?
print('abcd'.isupper())

# 모두 공백인가?
print('    '.isspace())
print('  a  '.isspace())