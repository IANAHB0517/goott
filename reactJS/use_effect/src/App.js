import './App.css';
import React, { useState, useEffect } from 'react';


function App() {
  
  const [counter, setCounter] = useState(0); 
  const [name, setName] = useState('');

  // useEffect((),[]) :
  // 2번째 매개변수에 있는 변수가 변경이 감지 되었을 때 실핼될 콜백 함수
  // 2 번째 매개변수의 배열은 변경을 감지할 변수 List (없다면 callback 함수는 최초 1 회만 실행된다)

  useEffect(() => {
      console.log('이 부분은 딱 한번만 실행 됩니다.');

  }, [])

  useEffect(() => {
    console.log('name 값이 변경 되었습니다 : ' + name);

}, [name])

useEffect(() => {
  console.log('counter 값이 변경 되었습니다 : ' + counter);

}, [counter])

  // 후크 매개변수에 있는 값이 변경되면 감지해서 보내줌
  
  const increase = ()=> {
      console.log("clicked");
      setCounter((cur) => cur + 1); 
  }
  
     
  const textChange = (evt) => {
      console.log(evt.target.value)
      setName(evt.target.value);
  }

  return (
      <div>
          <input tupe='text' onChange={textChange} value={name}/>
          <span>Total Clicks : {counter}</span>
          <button onClick={increase}>Click Me</button>
      </div>
  );
  
}

export default App;
