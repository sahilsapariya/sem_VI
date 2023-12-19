// Code for real time tracking
var time = document.getElementById("real-time");

setInterval(function () {
  time.innerHTML = new Date().toLocaleTimeString();
}, 1000);

// Fetching the data from the url using XMLHttpRequest
var xhr = new XMLHttpRequest();
const url = "https://jsonplaceholder.typicode.com/todos/";

xhr.open("GET", url, true);

xhr.onreadystatechange = function () {
  if (xhr.readyState === 4 && xhr.status === 200) {
    const data = JSON.parse(xhr.responseText);
    const ul = document.createElement("ul");

    data.forEach(function (todo) {
      const li = document.createElement("li");
      li.innerHTML = todo.title;
      ul.appendChild(li);
    });

    document.getElementById("data").appendChild(ul);
  }
};

xhr.send();

// collecting the data using Fetch (REST API)
const fetchData = async () => {
  try {
    const response = await fetch(
      "https://restcountries.com/v3.1/all?fields=name"
    );
    const data = await response.json();

    if (data) {
      const ul = document.createElement("ul");

      data.forEach(function (country) {
        const li = document.createElement("li");
        li.innerHTML = country.name.common;
        ul.appendChild(li);
      });

      document.getElementById("country_names").appendChild(ul);
    }
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
};
fetchData();
