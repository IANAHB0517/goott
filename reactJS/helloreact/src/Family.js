function Family({name, image}){
    

    return (
        <div>
            <div>
                <img src={image} alt={name}/>
            </div>
            <div>
                <h4>{name}</h4>
            </div>
        </div>
    )
}

export default Family;

// 데이터를 넣어줄 빈 껍데기를 컴포넌트 화