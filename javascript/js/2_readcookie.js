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
