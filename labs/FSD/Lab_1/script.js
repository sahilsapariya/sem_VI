var result = document.getElementById('result')


const sum = () => {
    var number1 = Number(document.getElementById("number1").value)
    var number2 = Number(document.getElementById("number2").value)

    result.style.fontSize = '2rem'
    const ans = number1 + number2
    result.innerText = `${ans}`
}

const sub = () => {
    var number1 = Number(document.getElementById("number1").value)
    var number2 = Number(document.getElementById("number2").value)

    result.style.fontSize = '2rem'
    result.innerHTML = `${number1 - number2}`
}

const mul = () => {
    var number1 = Number(document.getElementById("number1").value)
    var number2 = Number(document.getElementById("number2").value)

    result.style.fontSize = '2rem'
    result.innerHTML = `${number1 * number2}`
}

const div = () => {
    var number1 = Number(document.getElementById("number1").value)
    var number2 = Number(document.getElementById("number2").value)

    if (number2 !== 0) {
        result.innerHTML = `${number1 / number2}`
    } else {
        result.style.fontSize = '1rem'
        result.innerHTML = "Zero division error: cannot divide any number by 0"
    }  
}