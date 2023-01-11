// function getParameter(paraName) {
//   let url = location.href;
//   let flag = null;
//   let queryString = url.split("?")[1];
//   queryStringAr = queryString.split("&");

//   queryStringAr.forEach(function (item) {
//     console.log(item);
//     if (item.split("=")[0] == paraName) {
//       flag = true;
//       return (paraName = Number(item.split("=")[1]));
//     }
//   });
//   return flag;
// }

function getParameter(paramName) {
  // 쿼리스트링에서 넘겨받은 paramName을 찾아 그 변수의 값을 return 한다.
  //만약 쿼리스트링에 paramName이 없다면 null을 return 한다.
  let url = location.href;
  let flag = null;
  if (url.indexOf("?") != -1) {
    // 쿼리스트링이 있을때
    let queryString = url.split("?")[1];
    console.log(queryString);
    let tmpAr = queryString.split("&");
    for (let tmp of tmpAr) {
      if (tmp.split("=")[0] == paramName) {
        flag = tmp.split("=")[1];
        break;
      }
    }
  }

  return flag;
}

function saveCookie(cookName, cookieValue, expires) {
  // 넘겨 받은 cookName 이라는 이름으로,
  // 넘겨 받은 cookieValue 값을 쿠키에 저장하되,
  // 만료일은 넘겨 받은 현재 날짜 +expires일 로 저장하자.
  let now = new Date();

  now.setDate(now.getDate() + expires);

  let tmpCookie =
    cookName + "=" + cookieValue + ";expires=" + now.toUTCString();

  document.cookie = tmpCookie;
}

function readCookie(cookName) {
  //쿠키 이름을 매개변수로 받아
  //넘겨 받은 쿠키 이름의 값을 반환
  //만약 넘겨 받은 쿠키의 이름에 해당하는 쿠키가 존재하지 않으면, null 반환

  let returnVal = null;

  let cookies = document.cookie;
  if (cookies != "") {
    // 쿠키가 있다
    let cookAr = cookies.split(";");
    for (let item of cookAr) {
      if (item.split("=")[0].trim() == cookName) {
        returnVal = item.split("=")[1];
        return returnVal; // boolean이 아닌 값을 반환 해도 괜찮은가?
      }
    }
  }
  return returnVal;
}
