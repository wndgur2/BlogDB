const x = {}
console.log(x.constructor)
// [Function: Object]

function Person(first, last, age, eyecolor) {
  this.firstName = first
  this.lastName = last
  this.age = age
  this.eyeColor = eyecolor
  this.nationality = 'English'
}

const myFather = new Person('John', 'Doe', 50, 'blue')
console.log(myFather)
// Person{...}

Person.area = 'London'
console.log(myFather.area)
// undefined

Person.prototype.area = 'London'
console.log(myFather.area)
// London
