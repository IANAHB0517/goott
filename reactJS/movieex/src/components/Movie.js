function Movie({id, title, coverImg, summary, genres}){
    return (
      <div>
          <div className="movieDiv">
            <h4>{title}</h4>
            <div className='coverImg'>
              <img src={coverImg} alt={title} />
              <p>{summary}</p>
              <ul>
                {genres.map((genre) => (
                  <li key={genre}>{genre}</li>
                ))}
              </ul>
            </div>
          </div>
      </div>
   
    );
}

export default Movie;