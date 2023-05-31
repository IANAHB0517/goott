import {useEffect, useState} from "react";
import "./App.css";
// https://api.coinpaprika.com/v1/tickers?limit=15
// 환율 정보 제공
// http://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.KRWUSD

function App() {
  const [loading, setLoading] = useState(true);
  const [coins, setCoins] = useState([]);
  const [yourDallar, setYourDallar] = useState(0);
  const [coinPrice, setCoinPrice] = useState(0);
  const [selectedCoinIndex, setSelectedCoinIndex] = useState("");
  const [coinName, setCoinName] = useState("");

  // App 컴포넌트가 매번 render되더라도 한번만 호출하여 api에서 json을 가져와 coins에 넣어준다
  useEffect( () => {
    fetch("https://api.coinpaprika.com/v1/tickers?limit=15")
    .then((response) => response.json())
    .then((data) => setCoins(data));
    setLoading(false);
    console.log(coins);
  }, []);

  const inputDollar = (e) => {
    console.log(e.target.value);
    setYourDallar(e.target.value);
  }

  const changeCoin = (e) => {
    let selectTag = e.target;
    let selectedIndex = selectTag.selectedIndex -1; // 선택한 배열의 index 값
    console.log(coins[selectedIndex].name);
    setCoinName(coins[selectedIndex].name);
    
    if (e.target.value != -1){
      console.log("선택한 코인 가격 : " + e.target.value);
      setCoinPrice(e.target.value);
      console.log("선택한 코인 Index : " +  selectedIndex);
      setSelectedCoinIndex(selectedIndex);

    }
  }

  return (
   <div className='container'>
    <h1>Coin Market</h1>
    <div>
      {loading ? <h4>Loading...</h4> : null}
    </div>

    <div>
      <input type="number" className="form-control" placeholder="보유중인 달러" onChange={inputDollar}/>

    </div>
    
    <div>
      <select className="form-select form-select-lg" onChange={changeCoin}>
        <option key ={-1} value={-1}>코인을 선택하세요</option>
        {coins.map((coin) => (
          <option key={coin.id} value={coin.quotes.USD.price.toFixed(2)}>{coin.symbol}</option>
        ))}
      </select>
    </div>

    <hr />

    <div>
      {(yourDallar > 0 && coinPrice != -1) ? 
        <h3>보유중인 ${yourDallar}로 구매 가능한 {coinName} 코인의 갯수는 {(yourDallar / coinPrice).toFixed(2)} 개 입니다.</h3>
        // <h3>보유중인 ${yourDallar}로 구매 가능한 {coins[selectedCoinIndex].id} {coinName} 코인의 갯수는 {(yourDallar / coinPrice).toFixed(2)} 개 입니다.</h3>
      : null}
    </div>

    <div className="lastUpdate">
      
      {
       /* <h6><i className="fa-regular fa-clock"></i>{coins[0].last_updated}</h6> */
      }
    </div>

    <ul className="list-group coinList">
      {coins.map((coin, index) => (
        <li className="list-group-item coin" key={coin.id}>
          <div className="coinTitle">
          <i className="fa-solid fa-coins"></i>
            <span>{coin.name}</span>
            <span>{coin.symbol}</span>
          </div>
          <div className="coinPrice">
            ${coin.quotes.USD.price.toFixed(2)}
          </div>
          <div>
            {coin.quotes.USD.market_cap_change_24h > 0 ? 
            <div className="up24">{coin.quotes.USD.market_cap_change_24h}</div> : 
            <div className="dn24">{coin.quotes.USD.market_cap_change_24h}</div>

            }
          </div>
        </li>

      ))
      }
    </ul>  


   </div>
  );
}

export default App;
