# 데이터 베이스에 접속ㅎ여 select 해보자

import pymysql

try : 
    # dbConnection = pymysql.connect(host="gottclass6.cafe24.com", port=3306, user="gottclass6", password='GOTclass6@', db='gottclass6', charset='utf8') # 서버 DB
    dbConnection = pymysql.connect(host="127.0.0.1", port=3306,user="root",password="1234",db="lsj",charset='utf8') # 자바로 따지면 connection 객체

    # dbConnection = pymysql.connect(host="127.0.0.1", port=3306, user="root",password="1234", db="lsj", charset='utf8') 
    # dbConnection = pymysql.connect(host="127.0.0.1", port=3306, user="root",password="1234", db="lsj", charset='utf8') # 자바로 따지면 connection 객체
except :
    print('db 접속 에러')
else :
    print(dbConnection)
    cursor = dbConnection.cursor()
    sql = "select * from member where  userId = %s and userPwd= sha1(md5(%s))"
    cursor.execute(sql, ('gogi', '1234')) # 매개변수가 여러개 일때는 튜플로준다
    result = cursor.fetchone()
    print(result)

    sql2 = "select * from member"
    cursor.execute(sql2)
    result = cursor.fetchall()
    # fetchone() : row가 하나일 때
    # fetchall() : row가 여러개 일 때
    for rowdata in result :
        print(rowdata[0], end=", ")
        print(rowdata[1], end=", ")
        print(rowdata[2])
        print('-------------------------')
    dbConnection.close()
    

