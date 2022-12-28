
function Account(owner, accNum, balance){ 
this.owner = owner; 
this.accNum = accNum;
this.balance = balance;
this.bankName = "우리은행";
this.name = "직장인 비과세 예금";


// 객체에 기능 부여
this.inquiry = function() {
  return this.balance;
}

this.deposit = function (money) { 
  this.balance += money; 
}

this.withDraw = function withDraw(money){
if(this.balance >= money){
   this.balance -= money;
} else {
   window.alert("잔액이 부족합니다!")
}
}

}