// notion 참조


// // 중간 값 생략

// const [a, b] = [1, 2, 3, 40, 60];
// console.log(a,b)


// // // , 의 개 수 만큼 건너 뛰어 할당한다
// // 프로그래머가 필요한 값만 할당 하고자 할 때(의도적)
// // const[d,,,e] = [1, 2, 3, 40, 60];
// // console.log(d,e);


// const[d,,,e, f] = [1, 2, 3, 40, 60];
// console.log(d,e,f);

// // 나머지 값 할당

// const arr = [1, 2, 3, 4, 5];
// const [a, b, ...c] = arr;
// console.log(a,b,c); // a= 1, b=2. c= = [3,4,5]


// // 변수 값의 swap

// let a = 3, b = 5;
// // let tmp = a;
// // a = b;
// // b = tmp; // 기존의 swap 방법
// console.log(a, b);

// [a, b] = [b, a];
// console.log(a, b);