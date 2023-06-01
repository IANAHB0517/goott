import { useEffect, useState } from 'react';
import './App.css';
import Movie from './components/Movie';

// https://yts.mx/api/v2/list_movies.json?minimum_rating=8&sort_by=year

function App() {
  const [loading, setLoading] = useState(true);
  const [movies, setMovies] = useState([]);

  const getMovies = async () => {
    const response = await fetch('https://yts.mx/api/v2/list_movies.json?minimum_rating=8&sort_by=year');
    const json = await response.json();
    setMovies(json.data.movies);
    setLoading(false);

  }

  useEffect(() => {
    // fetch('https://yts.mx/api/v2/list_movies.json?minimum_rating=8&sort_by=year')
    // .then((response) => response.json())
    // // .then((data) => console.log(data.data.movies))
    // .then((data) => {
    //   setMovies(data.data.movies)
    //   setLoading(false);
    // });

    getMovies()

  }, []);
  // 배열에 빈값을 주면 한번만 실행된다

  console.log(movies)

  return (
   <div>
    {loading ? <h4>Loading</h4> : (
    <div>
      {movies.map((movie) => 
        <Movie
        key={movie.id} 
        id={movie.id}
        title={movie.title}
        coverImg={movie.medium_cover_image}
        summary={movie.summary}
        genres={movie.genres} />)}
    </div>
    )}
   </div>
  );
}

export default App;
