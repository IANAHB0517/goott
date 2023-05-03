import requests
from bs4 import BeautifulSoup
import pymysql

headers = {"User-Agent" : "Mozilla/5.0"}

pageno=1
baseUrl="https://www.boardgamemall.co.kr"

targetUrl = "https://www.boardgamemall.co.kr/goods/goods_list.php?page="+str(pageno)+"&cateCd=047001"

resp= requests.get(targetUrl, headers=headers)

resp.encoding='utf-8'
html=resp.text



if html is not None : #웹 문서를 로딩했다면... (로딩 됬을 때만 파싱되도록 설정)
    html = BeautifulSoup(html, "html.parser") #html로 파싱

    try: #아래의 코드를 수행해서
        products = html.find('div',class_='sub_content') 
				#클래스명을 검색할려면 class_="클래스명" 
        
    except: #예외가 발생 했다면
        print("파싱할 상품이 없습니다")
    else : #예외가 발생하지 않았다면
        products = products.select("div.goods_list_item > div.goods_list li")
        
        for item in products :     
            detailPage = baseUrl + item.find('a').attrs['href'].split('..')[1]

            productId = detailPage.split('goodsNo=')[1]

            productName = item.find('strong',class_='item_name').text.strip()
            
            productPrice = item.find('div', class_='item_money_box').find_next().text.split('%')[1].strip().replace(',','').replace('원','')

            thumbImgURL = item.find('div', class_='item_photo_box').find('img').attrs['src']

            if detailPage is not None : #웹 문서를 로딩했다면... (로딩 됬을 때만 파싱되도록 설정)
                resp1= requests.get(detailPage, headers=headers)
                resp1.encoding='utf-8'
                html2= resp1.text
                html2 = BeautifulSoup(html2, "html.parser") #html로 파싱
            
            try: #아래의 코드를 수행해서
                products2 = html2.find('div',class_='sub_content') #클래스명을 검색할려면 class_="클래스명" 
            except: #예외가 발생 했다면
                print("파싱할 상품이 없습니다")
            else :
                products2 = products2.find('div',class_='content_box') 
                
                mainImgURL = products2.find('div', class_='item_photo_big').find('img').attrs['src']
                
                # detailList = products2.find('div', class_='item_detail_list').find_all('dl')

                if products2.find('div', class_='item_detail_list').find_all('dl')[4].find('dt').text == '게임 인원' : 
                    players = products2.find('div', class_='item_detail_list').find_all('dl')[4].find('dd').text
                else : 
                    players = ' '

                if products2.find('div', class_='item_detail_list').find_all('dl')[5].find('dt').text == '게임 연령' : 
                    age = products2.find('div', class_='item_detail_list').find_all('dl')[5].find('dd').text
                else : 
                    age = ' ' 

                if (products2.find('div', class_='item_detail_list').find_all('dl')[6].find('dt').text) == '브랜드' : 
                    brandName = products2.find('div', class_='item_detail_list').find_all('dl')[6].find('dd').text

                elif (products2.find('div', class_='item_detail_list').find_all('dl')[4].find('dt').text) == '브랜드' : 
                    brandName = products2.find('div', class_='item_detail_list').find_all('dl')[4].find('dd').text

                else : 
                    brandName = ' '

                
                productModel = products2.find('table', class_='left_table_type').find_all('tr')[0].find('td').text
                productCompany = products2.find('table', class_='left_table_type').find_all('tr')[2].find('td').text
                productCountry = products2.find('table', class_='left_table_type').find_all('tr')[3].find('td').text
                productQuality = products2.find('table', class_='left_table_type').find_all('tr')[4].find('td').text
                productWarning = products2.find('table', class_='left_table_type').find_all('tr')[13].find('td').text

                descImgsId = productId + 'descImg'
                descImgs = []
                for descImg in products2.find('div', class_='detail_explain_box').find_all('img') :
                    descImgs.append(descImg.attrs['src'])

            try : 
                dbConnection = pymysql.connect(host="gottclass6.cafe24.com", port=3306, user="gottclass6", password='GOTclass6@', db='gottclass6', charset='utf8')
            
            except :
                print('db 접속 에러')
                
            else : 
                print(dbConnection)
                cursor = dbConnection.cursor() # Statement 객체와 같은 역할

                print('------------브랜드이름 영역-----------')
                isExist = 0
                selectNoQuery = "select count(*) from Brand where brandName = %s"
                cursor.execute(selectNoQuery, brandName)
                isExist = cursor.fetchone()[0]
                if isExist == 1 :
                    print(isExist, brandName + "없음")
                   
                else :
                    getBrandNoQuery = "select brandId from Brand order by brandId desc limit 1"
                    cursor.execute(getBrandNoQuery)
                    brandNo = cursor.fetchone()[0]
                    print(brandNo, brandName)
                    currentNo = int(brandNo[1:]) + 1
                    print(currentNo)
                    newBrandNo = "B" + str(currentNo).zfill(3)
                    insertQuery = "insert into Brand values (%s, %s)"

                    print(productName, newBrandNo, brandName)

                    cursor.execute(insertQuery, (newBrandNo, brandName))

                selectBrandId = "select brandId from Brand where brandName = %s" 
                cursor.execute(selectBrandId, brandName)
                getbrandId = (cursor.fetchone()[0]).decode('utf-8')

                print(type(getbrandId)) 
                print("brandName" + brandName) 
                print("getbrandId : " + getbrandId)

                sql = "insert into Product(productId, productName, productPrice, players, age, productModel, productCompany, productCountry, productQuality, productWarning, productStock, brandId) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
                result = cursor.execute(sql, (productId, productName, productPrice, players, age, productModel, productCompany, productCountry, productQuality, productWarning, '10', getbrandId)) 
                    
                print("result : " + str(result) )

                if result == 1 :
                        #dbConnection.commit()
                        sql2 = "insert into ThumbImg(thumbImgURL, mainImgURL, productId) values (%s, %s, %s)"
                        result2 = cursor.execute(sql, ((thumbImgURL, mainImgURL, productId)))
                        
                        sql3 = "insert into ThumbImg(descImgsId, descImgs, productId) values (%s, %s, %s)"
                        result3 = cursor.execute(sql, ((productId + 'Img', descImgs, productId)))

                        print("result2 : " + result2 + " result3 : " + result3)

                        if result2 and result3 == 1 :
                            dbConnection.commit()
                        else : 
                            dbConnection.rollback()

                else :
                    dbConnection.rollback()
        
            dbConnection.close()
        
            # print(productId, productName, productPrice)
        
            # print(thumbImgURL)
            # print(detailPage)
            # print(mainImgURL)
            print(players, age, brandName)
            # print(productModel, productCompany, productCountry, productQuality, productWarning)
            # print(descImgs)
            # print("------------------")


