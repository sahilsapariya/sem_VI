var n = [1, 2, 3, 4, 5, 6, 7, 8]

var subjects = {
    "101": "Python",
    "102": "DP",
    "103": "FSD",
    "104": "LT",
    "105": "AOS"
}

console.log(subjects[101])
console.log(subjects[102])


// categories in odd even numbers
var even = []
var odd = []

for (i in n) {
    if (n % 2 == 0) even.push(i)
    else odd.push(i)
}
console.log("even numbers: " + even)
console.log("odd numbers: " + odd)


// sum of the array
let sum = 0
console.log("using for each loop")
for (i of n) 
    sum += i
console.log("sum of the array n : " + sum)

// printing the elements of array
console.log("using for loop")
for (let index = 0; index < n.length; index++) {
    console.log(n[index])
}

// printing the values of subject object
console.log("using for each loop")
for(key in subjects) console.log(subjects[key])


