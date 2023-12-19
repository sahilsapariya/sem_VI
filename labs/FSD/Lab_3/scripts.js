// Code for real time tracking
var time = document.getElementById("real-time")

setInterval(function () {
    time.innerHTML = new Date().toLocaleTimeString();
}, 1000)


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
