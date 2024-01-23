import React from "react";
import "../styles/Home.css";

import { cardData } from "../data/Data";
import Card from "../component/Card";

const Home = () => {
  return (
    <div className="home__container">
      {/* {cardData &&
        cardData.map((card, index) => {
          return <Card data={card} key={index} />;
        })} */}

        <Card {...cardData[0]} />
        <Card {...cardData[1]} />
        <Card {...cardData[2]} />
    </div>
  );
};

export default Home;
