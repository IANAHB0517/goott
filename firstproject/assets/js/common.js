function dataCall(baseUrl) {
  $.ajax({
    url: baseUrl, // 데이터가 송수신될 서버의 주소
    type: "GET", // 통신 방식 (get, post, put , delete)
    dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
    success: function (data) {
      // 통신이 성공하면 콜백함수 호출
      parsing(data);
    },
    error: function () {
      // 통신이 제대로 수행 되지 않았을 때의 콜백 함수
    },
    complete: function () {
      //통신이 끝났을 때 콜백함수
    },
  });
}

function getParameter(paramName) {
  // 쿼리스트링에서 넘겨받은 paramName을 찾아 그 변수의 값을 return 한다.
  //만약 쿼리스트링에 paramName이 없다면 null을 return 한다.
  let url = location.href;
  let returnVal = null;
  if (url.indexOf("?") != -1) {
    // 쿼리스트링이 있을때
    let queryString = url.split("?")[1];
    console.log(queryString);
    let tmpAr = queryString.split("&");
    for (let tmp of tmpAr) {
      if (tmp.split("=")[0] == paramName) {
        returnVal = tmp.split("=")[1];
        break;
      }
    }
  }

  return returnVal;
}
