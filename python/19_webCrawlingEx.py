import requests # 다운 받은 리퀘스트 패키지
from bs4 import BeautifulSoup #
import pymysql # DB 연결 패키지 

pageno = 1
targetUrl = "https://lemite.com/product/list.html?cate_no=43&page=" + str(pageno)
baseUrl = 'https://lemite.com/'
headers = {'User-Agent' : 'Mozilla/5.0'}
resp = requests.get(targetUrl, headers = headers)

resp.encoding = 'charset=utf-8'
html = resp.text
# print(html) # 단순 문자열로 출력

if html is not None : # 웹 문서를 로딩 했다면
    html = BeautifulSoup(html, 'html.parser') # html로 파싱
    # print(html) # 태그 구조를 인식할 수 있다.
    try : # 아래의 코드를 수행해 봐서
        products = html.find('ul', class_='prdList column4') # 가지고온 html에서 태그 이름과 클래스 명으로 그 안의 내용만 검색
        # print(products) # 선택한 부분의 내용만 출력
    except : # 예외가 발생 했다면
        print('파싱할 상품이 없음')
    else :  # 예외가 발생하지 않았다면
        products = products.find_all('li', class_='item xans-record-') # 찾고자 하는 태그가 여러개 일경우 find_all을 쓴다
        for item in products :
            # print(item)
            # print(item.find('p', class_='name').text) # 태그를 무시하고 text만 가지고 온다
            productName = item.find('p', class_='name').text.split(':')[1].strip() # 가지고온 text를 : 을 기준으로 뒤의 것만 가지고 온다
            detailPage = baseUrl + item.find('p', class_='name').find('a').attrs['href']

            pkStartPos = detailPage.find('product_no=') + len('product_no=')
            pkEndPos = detailPage.find('&', pkStartPos) #pkStartPos 부터 나오는 첫번째 &를 찾아라
            productNo = detailPage[pkStartPos:pkEndPos]

            thumbNailImg = 'https:' + item.find('div', class_='prdimg').find('img').attrs['src']

            description = item.find('li', class_='xans-record-').text.split(':')[1].strip()

            productPrice = item.find('li', class_='xans-record-').find_next('li').text.split(':')[1].strip().replace(',','').replace('원','')

            print(productNo, productName, productPrice, thumbNailImg, description, detailPage)

            #print(thumbNailImg)
            print('-------------------------------------------------------')
            

# 크롤링도 웹의 구조를 이용해야 한다
# 리스트 페이지에서 꼭필요한 공통 데이터를 얻어오고 세부사항을 디테일 페이지 에서 한다
