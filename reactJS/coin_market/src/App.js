import {useEffect, useState} from "react";
import "./App.css";
// https://api.coinpaprika.com/v1/tickers?limit=15
// 환율 정보 제공
// http://quotation-api-cdn.dunamu.com/v1/forex/recent?codes=FRX.KRWUSD

function App() {
  const [loading, setLoading] = useState(true);
  const [coins, setCoins] = useState([])

  // App 컴포넌트가 매번 render되더라도 한번만 호출하여 api에서 json을 가져와 coins에 넣어준다
  useEffect( () => {
    fetch("https://api.coinpaprika.com/v1/tickers?limit=15")
    .then((response) => response.json())
    .then((data) => setCoins(data));
    setLoading(false);
    console.log(coins);
  }, []);

  return (
   <div className='container'>
    <h1>Coin Market</h1>
    <div>
      {loading ? <h4>Loading...</h4> : null}
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
