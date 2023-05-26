import Family from "./Family";

function App() { // 함수형 컴포넌트

  // fetch, axios 모듈 -> json을 받아옴

  const data = [
    {id : 1, name : '둘리', image : 'https://i.namu.wiki/i/y2-xaQaABwUoP5m9LLHqBjti2H7vjf8X24qr_WhLzSZWAWiS_lG_OjrrMLHpIWT-WxkZpRjNv8E39cLYBbKvkw.webp' },
    {id : 2, name : '도우너', image : 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQs_UYTiGuKl8sNHYyLAQFWuBJSYbrrXZMyQSOKW3eP&s'},
    {id : 3, name : '고길동', image : 'https://mblogthumb-phinf.pstatic.net/20140511_27/swyl1220_1399775585829RmpCd_JPEG/%B0%ED%B1%E6%B5%BF1.jpg?type=w2'}
  ];

  return (
    <div>
      <h1>Hello, React</h1>
      {
        data.map((family, index) => 
          <Family key={family.id} name={family.name} image={family.image}/>
        )
      }
      
    </div>
  );
}

export default App; // 다른 js 파일에서 App()를 호출할 수 있도록 내보내기 한다

// 반복문 사용시 foreach 보다는 map을 더 많이 사용한다.

// 컴포넌트에 넣어줄 데이터를 받아오는 역할의 main js