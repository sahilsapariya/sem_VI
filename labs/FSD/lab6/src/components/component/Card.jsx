import React from "react";

const Card = (props) => {
  return (
    <div className="card__container">
      <div className="card__header">
        <h1>{props.name}</h1>
      </div>

      <div className="card__logo">
        <img src={props.img} alt="logo" />
      </div>

      <div className="card__description">
        <p>{props.description}</p>
      </div>
    </div>
  );
};

export default Card;
