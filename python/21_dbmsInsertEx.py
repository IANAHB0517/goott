import pymysql

try : 
    # dbConnection = pymysql.connect(host="gottclass6.cafe24.com", port=3306, user="gottclass6", password='GOTclass6@', db='gottclass6', charset='utf8') # 서버 DB
    dbConnection = pymysql.connect(host="127.0.0.1", port=3306,user="root",password="1234",db="lsj",charset='utf8') # 자바로 따지면 connection 객체
except :
    print('db 접속 에러')
else :
    print(dbConnection)
    cursor = dbConnection.cursor() #Statement객체와 같은 역할
    sql = "insert into board(writer, title, content) values(%s, %s, %s)"
    result = cursor.execute(sql, ('gogi', '파이썬 테스트', 'insert 문 테스트'))
    if result == 1 :
        dbConnection.commit()
    else :
        dbConnection.rollback()
    dbConnection.close()